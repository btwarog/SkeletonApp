package pl.btwarog.skeletonapp.injection.scopes

import javax.inject.Scope

/**
 * Singletons for child fragment runtime
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerChildFragment