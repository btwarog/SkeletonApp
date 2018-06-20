package pl.btwarog.skeletonapp.injection

import java.lang.reflect.Method
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * This class allows to inject into objects through a base class,
 * so we don't have to repeat injection code everywhere.
 *
 *
 * The performance drawback is about 0.013 ms per injection on a very slow device,
 * which is negligible in most cases.
 *
 *
 * Example:
 * <pre>`Component {
 * void inject(B b);
 * }
 *
 * class A {
 * void onCreate() {
 * componentReflectionInjector.inject(this);
 * }
 * }
 *
 * class B extends A {
 * MyDependency dependency;
 * }
 *
 * new B().onCreate() // dependency will be injected at this point
 *
 * class C extends B {
 *
 * }
 *
 * new C().onCreate() // dependency will be injected at this point as well
`</pre> *
 *
 * @param <T> a type of dagger 2 component.
 *
 *
 * Source https://gist.github.com/konmik/6ac725fa7134402539c4
</T> */
class ComponentReflectionInjector<T>(private val componentClass: Class<T>, val component: T) : Injector {
    private val methods: HashMap<Class<*>, Method>?
    private val cache = ConcurrentHashMap<Class<*>, HashMap<Class<*>, Method>>()

    init {
        this.methods = getMethods(componentClass)
    }

    override fun inject(target: Any) {

        var targetClass: Class<*>? = target.javaClass
        if(methods == null)
            throw RuntimeException(String.format("No %s injecting method exists in %s component", target.javaClass, componentClass))

        var method: Method? = methods[targetClass]
        while (method == null && targetClass != null) {
            targetClass = targetClass.superclass
            method = methods[targetClass]
        }

        if (method == null)
            throw RuntimeException(String.format("No %s injecting method exists in %s component", target.javaClass, componentClass))

        try {
            method.invoke(component, target)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

    private fun getMethods(componentClass: Class<*>): HashMap<Class<*>, Method>? {
        var methods: HashMap<Class<*>, Method>? = cache[componentClass]
        if (methods == null) {
            synchronized(cache) {
                methods = cache[componentClass]
                if (methods == null) {
                    methods = HashMap()
                    for (method in componentClass.methods) {
                        val params = method.parameterTypes
                        if (params.size == 1)
                            methods!![params[0]] = method
                    }
                    cache[componentClass] = methods!!
                }
            }
        }
        return methods
    }
}