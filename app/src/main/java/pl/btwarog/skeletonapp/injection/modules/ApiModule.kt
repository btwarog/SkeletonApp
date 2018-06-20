package pl.btwarog.skeletonapp.injection.modules

import android.content.Context
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.btwarog.skeletonapp.BuildConfig
import pl.btwarog.skeletonapp.R
import pl.btwarog.skeletonapp.data.remote.GithubApiService
import pl.btwarog.skeletonapp.domain.utils.TLSSocketFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.security.GeneralSecurityException
import java.security.KeyStore
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

/**
 * Created by bartlomiej.twarog on 2018-06-20
 */
@Module
open class ApiModule {

    @Provides
    @Singleton
    @Named("safeClient")
    internal fun provideOkHttpClient(): OkHttpClient {
        val builder = getDefaultOkHttpBuilder()
        builder.apply {
            sslSocketFactory(TLSSocketFactory(), defaultTrustManager())
        }
        return builder.build()
    }

    @Throws(GeneralSecurityException::class)
    private fun defaultTrustManager(): X509TrustManager {
        val trustManagerFactory = TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm())
        trustManagerFactory.init(null as KeyStore?)
        val trustManagers = trustManagerFactory.trustManagers
        if (trustManagers.size != 1 || trustManagers[0] !is X509TrustManager) {
            throw IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers))
        }
        return trustManagers[0] as X509TrustManager
    }

    private fun getDefaultOkHttpBuilder(): OkHttpClient.Builder {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return OkHttpClient.Builder().apply {
            addInterceptor(logging)

            connectTimeout(10, TimeUnit.SECONDS)
            readTimeout(10, TimeUnit.SECONDS)
            writeTimeout(10, TimeUnit.SECONDS)
        }
    }

    @Provides
    @Singleton
    fun provideObjectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()
        objectMapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true)
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        objectMapper.registerKotlinModule()
        return objectMapper
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(context: Context, @Named("safeClient") client: OkHttpClient, mapper: ObjectMapper): Retrofit {
        return Retrofit.Builder()
                .baseUrl(getBaseUrl(context))
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    protected open fun getBaseUrl(context: Context): String {
        return context.getString(R.string.API_ENDPOINT)
    }

    @Provides
    @Singleton
    internal fun provideAPIService(retrofit: Retrofit): GithubApiService {
        return retrofit.create(GithubApiService::class.java)
    }
}