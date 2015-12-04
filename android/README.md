# Android Software Model development

This will enable the developers of the Android Software Model (Google Android team) to fully utilize the existing functionality in the core JVM and dependency management infrastructure. The goal is to allow an Android library to be built from Java sources, which have compile-time dependencies on both JVM libraries and other Android libraries.

## User roles

 - **Android Software Model developer** : A team of developers building the core plugins that provide the Android Software Model and support the Android ecosystem
 - **Android application developer** : A developer using the core plugins developed by the Android Software Model foundation developers

## Terminology

A **Software Ecosystem** consists of

 - Software Model (foundation)
 - Plugins (extending the foundation)
 - Conventions (making the foundation easier to use)
 - Repositories

The **Software Model** is the foundation that everything builds on top of.

e.g. The Roboelectric test runner plugin is built atop the Android Software Model

e.g. The Cobertura coverage plugin is built atop the JVM Software Model

## Features

In all cases, the feature in _Gradle_ is to enable the **Android Software Model developer** to develop plugins and types that will allow an **Android application developer** to use the specified feature.

### [Android library variant is built from Java sources](./1-built-from-java-sources)

An Android library can be built from Java sources for a specified Android platform, and compilation is automatically enabled and configured based on the "java-lang" plugin being applied.

### [Android library sources depend on JVM library](./2-depends-on-jvm-library)

An Android library is built from Java sources which have a compilation dependency on a JVM library, and this dependency is satisfied with a variant matching the Android platform.

### [Android library sources depend on Android library JVM API](./3-depends-on-android-library)

An Android library produces a JVM API, that can be used as a dependency for Java source compilation, with the dependency being satisfied with a variant having a compatible Android platform.

