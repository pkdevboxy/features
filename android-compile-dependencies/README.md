# Android library compilation dependencies

This will enable the Android Ecosystem Foundation developers (Google Android team) to fully utilize the existing functionality in the core JVM and dependency management infrastructure. The goal is to allow an Android library to be built from Java sources, which have compile-time dependencies on both Jvm libraries and other Android libraries.

## User roles

 - Android ecosystem foundation developer : A developer building the core plugins that support the Android ecosystem
 - Android application developer : A developer using the core plugins developed by the Android ecosystem foundation developers

## Features

In all cases, the feature in _Gradle_ is to enable an **Android Ecosystem Foundation developer** to produce plugins and types that will allow an **Android application developer** to use the specified feature.

### [Android library variant is built from Java sources](./built-from-java-sources)

An Android library can be built from Java sources for a specified Android platform, and compilation is automatically enabled and configured based on the "java-lang" plugin being applied.

### [Android library sources depend on JVM library](./depends-on-jvm-library)

An Android library is built from Java sources which have a compilation dependency on a JVM library, and this dependency is satisfied with a variant matching the Android platform.

### [Android library sources depend on Android library JVM API](./depends-on-android-library)

An Android library produces a JVM API, that can be used as a dependency for Java source compilation, with the dependency being satisfied with a variant having a compatible Android platform.


----

## brainstorming

Ecosystem plugin

Ecosystem plugin provider

 - e.g. (Google) Android (ecosystem) ~~plugin~~ provider
 - e.g. (Gradle) JVM (ecosystem) component plugin provider


Ecosystem consists of

 - Plugins
 - Conventions
 - Repositories


The most basic "thing" that everything builds on top of

 - Platform
 - Fabric
 - Framework
 - Foundation


 - Providing plugins on top
 - Building applications

e.g. The Roboelectric test runner plugin is built atop the Android (ecosystem) foundation

e.g. The Cobertura coverage plugin is built atop the JVM (ecosystem) foundation ((set of, bundle of) plugin(s))

----

 - Plugin author

---

