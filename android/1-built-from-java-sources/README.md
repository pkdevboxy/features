# Feature: Android library variant is built from Java sources

## User stories

- [x] Android library can include a `JavaSourceSet` and it appears in the 'components' report
- [ ] [Build author can define task that depends on the compiled classes of a JVM variant](./introduce-jvmassembly)
- [ ] Update PlayApplicationPlugin to use `JvmAssembly`
    - Play application has a `JvmAssembly` and `scala-lang` plugin configures the scala compilation
    - Update `scala-lang` plugin to transform `ScalaSourceSet` to `JvmAssembly`
    - Will need a way to specify that multiple source-sets for a component should be joint-compiled
- [ ] Android library can define a `JvmAssembly` and `java-lang` plugin configures the java compilation
    - Fail build when `java-lang` plugin is applied, and component has a `JavaSourceSet` but no `JvmAssembly`
        - From Adam: Please do this generally: fail when a build item has a source set as input that cannot be transformed. Also fix the int test for this that we already have that no longer tests this (the behaviour is broken but the test is not telling us this).
    - `JvmAssembly` instance should be automatically constructed for a read-only `@Unmanaged` property on a `@Managed` type
- [ ] Android library variant can have an associated Android platform
    - Android platform appears in the `components` report
    - Android library sources are compiled for the Java platform corresponding to the Java platform of a variant
    - Java ToolChain that will be used to build Android variant is displayed in the 'components' report
- [?] Update Native component model and language plugins to use a `NativeAssembly` to represent the intermediate files
- [?] Rule can target 'sources' container of all components of a particular type

## Out of scope

- Resolution of compile dependencies for Android: see Dependent features.

## Related work

### Prerequisite features

### Dependent features

- [Android library sources depend on JVM library](../depends-on-jvm-library/README.md)

----

## Usage

### Step 1. Run the 'components' report

### Step 2. Compile Android library classes for Java 6

    # ../../gradlew myAndroidJava6

    BUILD SUCCESSFUL

### Step 3. Compile Android library classes for Java 8

    # ../../gradlew myAndroidJava8

    BUILD SUCCESSFUL

### Step 4. Report class file versions to see the difference

### Step 5. Clean up
