# Cobalt

**Cobalt** is a *multiplatform* utilities library for Kotlin. It has *JVM* and *JS* targets
and contains modules for *data binding*, *logging*, *eventing* and *networking*. **Note that**
Cobalt is only intended to be used from **Kotlin** projects and it is not optimized for *JS* nor
*Java* projects.

## Modules at a glance:

- [cobalt.core](cobalt.core) contains features which are used by all other *modules*.
- [cobalt.databinding](cobalt.databinding) is a data binding library with supports
  both uni-directional and bi-directional binding.
- [cobalt.events](cobalt.events) contains an *event bus* implementation which lets you
  send events with pub/sub semantics.
- [cobalt.examples](cobalt.examples) contains usage examples for all *Cobalt* modules.
- [cobalt.html](cobalt.html) enhances the W3C DOM API with useful utility functions and
  also lets you use data binding with HTML elements.
- [cobalt.logging](cobalt.logging) enables you to do logging in a *very* similar way as SLF4J works.
- [cobalt.networking](cobalt.networking) can be used as an easy-to-use multiplatform alternative to Retrofit.