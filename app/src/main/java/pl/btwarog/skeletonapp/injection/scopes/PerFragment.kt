package pl.btwarog.skeletonapp.injection.scopes

import javax.inject.Scope

/**
 * Singletons for fragment runtime
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerFragment