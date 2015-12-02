# Android library variant is built from Java sources

## User stories

- Android library can include a `JavaSourceSet` and it appears in the 'components' report
    - DONE
- ~~JarBinarySpec~~`JvmVariantSpec` has a `JvmAssembly` and the `java-lang` plugin configures Java compilation based on it
    - Refactor existing JVM library to have a `JvmAssembly` build item
    - Configure `JvmAssembly` with target `JavaPlatform` for compilation
    - Change `java-lang` plugin so that it will apply a transform for every `JavaSourceSet` of a component with a `JvmAssembly`
    - Change `JvmComponentPlugin` to create a `ProcessResources` task for every `JvmResourceSet` of a component with a `JvmAssembly`

```
~~JarBinarySpec~~JvmVariantSpec {
    sources
    assembly
    jarFile
}

AndroidVariantSpec {
    sources
    assembly
    aarFile
}
```

- Play application has a `JvmAssembly` and `scala-lang` plugin configures the scala compilation
    - Replace what's currently `JvmClasses` in the play plugin by the JVM equivalent `JvmAssembly`
    - Modify `scala-lang` plugin to be aware of `JvmAssembly`
    - Need the ability to joint compile multiple scala and java source sets
- Android library can define a `JvmAssembly` and `java-lang` plugin configures the java compilation
    - Fail build when `java-lang` plugin is applied, and component has a `JavaSourceSet` but no `JvmAssembly`
- Android library variant has an associated Android platform, which appears in the `components` report
- Android library sources are compiled for the Java platform corresponding to the Java platform of a variant
- Java ToolChain that will be used to build Android variant is displayed in the 'components' report
- _?OPTIONAL?_ Rule can target 'sources' container of all components of a particular type
- _?OPTIONAL?_ Managed subtypes of `BuildableModelElement`

## Related work

### Prerequisite features

### Dependent features

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
