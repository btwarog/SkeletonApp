package pl.btwarog.skeletonapp.injection.scopes

import javax.inject.Scope

/**
 * Singletons for activity runtime
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity