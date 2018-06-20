package pl.btwarog.skeletonapp.test.injection.modules

import android.content.Context
import dagger.Module
import io.appflate.restmock.RESTMockServer
import pl.btwarog.skeletonapp.injection.modules.ApiModule

/**
 * Module providing api functionality
 */
@Module
open class TestApiModule : ApiModule() {

    override fun getBaseUrl(context: Context): String {
        return RESTMockServer.getUrl()
    }

}