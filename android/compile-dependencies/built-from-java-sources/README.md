# Android library variant is built from Java sources

## User stories

- Android library can include a `JavaSourceSet` and it appears in the 'components' report
    - DONE
- ~~JarBinarySpec~~`JvmVariantSpec` has a `ClassesDirectory` and 'java-lang' plugin configures the java compilation
    - Refactor existing JVM library to have a `ClassesDirectory` build item
    - Configure `ClassesDirectory` with target `JavaPlatform` for compilation
    - Change 'java-lang' plugin so that it will apply transform for every `JavaSourceSet` for a component with a `ClassesDirectory`
    - Change `JvmComponentPlugin` to create a `ProcessResources` task for every `JvmResourceSet` for a component with a `ClassDirectory`

```
~~JarBinarySpec~~JvmVariantSpec {
    sources
    classesDirectory
    jarFile
}

AndroidVariantSpec {
    sources
    classesDirectory
    aarFile
}
```

- Play application has a `ClassesDirectory` and 'scala-lang' plugin configures the scala compilation
    - Replace what's current `JvmClasses` in the play plugin by the Jvm equivalent `ClassDirectory`
    - Modify `scala-lang` plugin to be aware of `ClassDirectory`
    - Need the ability to joint compile multiple scala and java source sets
- Android library can define a `ClassesDirectory` and 'java-lang' plugin configures the java compilation
    - Fail build when 'java-lang' plugin is applied, and component has a `JavaSourceSet` but no `ClassesDirectory`
- Android library variant has an associated Android platform, which appears in the 'components' report
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
