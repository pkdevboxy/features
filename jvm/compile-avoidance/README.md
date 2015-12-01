# Recompilation Avoidance for Library Consumers

## Summary

This feature improves incremental build performance by avoiding the need to recompile component sources when certain kinds of changes are made to libraries that component depends on. By "certain kinds of changes", we mean changes to a library's sources that do not result in a change to the library's [binary signature](TODO). When the binary signature of a library does not change, components that depend on that library have no need to recompile.

For a component with a large number of source files, the incremental build performance improvement can be dramatic—even more so for large multiproject builds.

## Motivation

## Status


## Terminology

## History

## Related work

### Prerequisites

 - _None_

### Dependants

 - [Strong Encapsulation for Library Producers](TODO) depends on the work that will be done in this feature to generate API jars.

## Cards

See: [`feature:compile-avoidance`](https://github.com/gradle/langos/labels/feature:%3Acompile-avoidance)

### In Scope

 - [ ] Generate `ApiJar` JVM libraries and compile dependent JVM components against it
 - [ ] Fail helpfully if API jar members are consumed at runtime
 - [ ] Test performance of recompilation avoidance in a large multiproject build
 - [ ] Document recompilation avoidance

### Not in Scope

 - Eliminate the need for intermediate `ApiJar` by compiling directly against API classes

----

## Usage

The following steps use the code in this directory to demonstrate _the simplest possible usage of compile avoidance_.

### 1. Generate a large number of application classes

The performance benefits of compile avoidance are most dramatic in situations where there are a large number of classes that would otherwise take a considerable amount of time to compile.

    $ for i in $(seq 1 5000); do echo "package com.myco.gen; class C$i {}" > src/app/com/myco/gen/C$i.java; done

### 2. Clean, build and run

    $ ../../gradlew clean build run
    :jvm:compile-avoidance:clean
    :jvm:compile-avoidance:compileMyLibJarMyLibJava
    :jvm:compile-avoidance:myLibApiJar
    :jvm:compile-avoidance:compileMyAppJarMyAppJava
    :jvm:compile-avoidance:createMyAppJar
    :jvm:compile-avoidance:myAppApiJar
    :jvm:compile-avoidance:myAppJar
    :jvm:compile-avoidance:createMyLibJar
    :jvm:compile-avoidance:myLibJar
    :jvm:compile-avoidance:assemble
    :jvm:compile-avoidance:check UP-TO-DATE
    :jvm:compile-avoidance:build
    :jvm:compile-avoidance:run
    Hello, World!

    BUILD SUCCESSFUL

    Total time: 10.0 secs

Notice the long build time, most of which is spent in compiling thousands of application classes, i.e. this task:

    :compileAvoidance:compileMyAppJarMyAppJava


### 3. Build incrementally and run

    $ ../../gradlew build run
    :jvm:compile-avoidance:compileMyLibJarMyLibJava UP-TO-DATE
    :jvm:compile-avoidance:myLibApiJar UP-TO-DATE
    :jvm:compile-avoidance:compileMyAppJarMyAppJava UP-TO-DATE
    :jvm:compile-avoidance:createMyAppJar UP-TO-DATE
    :jvm:compile-avoidance:myAppApiJar UP-TO-DATE
    :jvm:compile-avoidance:myAppJar UP-TO-DATE
    :jvm:compile-avoidance:createMyLibJar UP-TO-DATE
    :jvm:compile-avoidance:myLibJar UP-TO-DATE
    :jvm:compile-avoidance:assemble UP-TO-DATE
    :jvm:compile-avoidance:check UP-TO-DATE
    :jvm:compile-avoidance:build UP-TO-DATE
    :jvm:compile-avoidance:run
    Hello, World!

    BUILD SUCCESSFUL

    Total time: 1.546 secs

Notice the build is faster given that all inputs are already up to date. Nothing new here yet.


### Step 4: modify library sources in a non-ABI changing way

In this step **we'll see compile avoidance in action**.

First, make a change to a private member of the application's `Main` class:

    $ find . -name World.java | xargs perl -p -i -e 's/"World"/"Earth"/'

and notice it results in the following diff:

```diff
$ git diff

 public class World {

-    private final String name = "World";
+    private final String name = "Earth";

     @Override
     public String toString() {
```

Because this modification **will not** result in a change to the library's ABI, the application **will not** need to recompile against it in the next incremental build:

    $ ../../gradlew build run
    :jvm:compile-avoidance:compileMyLibJarMyLibJava
    :jvm:compile-avoidance:myLibApiJar
    :jvm:compile-avoidance:compileMyAppJarMyAppJava UP-TO-DATE
    :jvm:compile-avoidance:createMyAppJar UP-TO-DATE
    :jvm:compile-avoidance:myAppApiJar UP-TO-DATE
    :jvm:compile-avoidance:myAppJar UP-TO-DATE
    :jvm:compile-avoidance:createMyLibJar
    :jvm:compile-avoidance:myLibJar
    :jvm:compile-avoidance:assemble
    :jvm:compile-avoidance:check UP-TO-DATE
    :jvm:compile-avoidance:build
    :jvm:compile-avoidance:run
    Hello, Earth!

    BUILD SUCCESSFUL

    Total time: 1.147 secs

Notice how:

 1. the build remained very fast
 2. recompilation of the app was not required, i.e. `compileHelloAppJarHelloAppJava` remained `UP-TO-DATE`
 3. yet the runtime output still changed as expected from "Hello, World" to "Hello, Earth"

Altogether, we've made a change to the library while **avoiding expensive recompilation of the application** and dramatically reduced incremental build times as a result.


### Step 5: modify library sources in an ABI-changing way

    $ find . -name World.java | xargs perl -p -i -e 's/private/public/'

and notice it results in the following diff:

```diff
$ git diff
 public class World {

-    private final String name = "Earth";
+    public final String name = "Earth";

     @Override
     public String toString() {
```

Because this modification **will** result in a change to the library's ABI, the application **will** need to recompile against it in the next incremental build:

    $ ../../gradlew build run
    :jvm:compile-avoidance:compileMyLibJarMyLibJava
    :jvm:compile-avoidance:myLibApiJar
    :jvm:compile-avoidance:compileMyAppJarMyAppJava
    :jvm:compile-avoidance:createMyAppJar UP-TO-DATE
    :jvm:compile-avoidance:myAppApiJar UP-TO-DATE
    :jvm:compile-avoidance:myAppJar UP-TO-DATE
    :jvm:compile-avoidance:createMyLibJar
    :jvm:compile-avoidance:myLibJar
    :jvm:compile-avoidance:assemble
    :jvm:compile-avoidance:check UP-TO-DATE
    :jvm:compile-avoidance:build
    :jvm:compile-avoidance:run
    Hello, Earth!

    BUILD SUCCESSFUL

    Total time: 7.794 secs

### Step 6: clean up

    $ git checkout src && git clean -fxq src
