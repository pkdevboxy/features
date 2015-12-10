# Feature: Android library variant is built from Java sources

## User stories

- [x] Android library can include a `JavaSourceSet` and it appears in the 'components' report
- [ ] [Build author can define task that depends on the compiled classes of a JVM variant](./1a-introduce-jvmassembly)
- [ ] [Android library can define a `JvmAssembly` and `java-lang` plugin configures the java compilation](./1b-library-can-define-jvmassembly)
- [ ] [Android library variant can have an associated Android platform](./1c-variant-can-have-platform)
- [ ] (`debt`) Use separate output directory for each compile task
    - Model each output directory as a build item
- [?] Update Native component model and language plugins to use a `NativeAssembly` to represent the intermediate files
- [?] Rule can target 'sources' container of all components of a particular type

## Debt

- A source set for each supported JVM language should, by convention, be added to the sources of the library component, as it is for a JVM library.

## Out of scope

- Resolution of compile dependencies for Android: see Dependent features.

## Related work

### Prerequisite features

### Dependent features

- [Android library sources depend on JVM library](../2-depends-on-jvm-library)

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
