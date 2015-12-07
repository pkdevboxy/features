# Android library sources depend on JVM library

## User stories

- [ ] Android library can have a source-set dependency on a JVM library API, which is used for compiling Java sources
- [ ] Android library can have a component dependency on a JVM library API, which is used for compiling Java sources
    - `DependencySpecContainer` instance should be automatically constructed for a read-only property on a `@Managed` type
    - Use rules to merge dependency sets defined for component/variant/source-set
    - Test coverage for local library and external dependencies, defined at the component and source-set level
- [ ] Android library sources are compiled with the JVM variant corresponding to the Android platform of a consuming variant
  - Will need a way to either:
    - Have the android plugin provide a variant compatibility rule for matching `AndroidPlatform` to `JavaPlatform`
    - OR Have the android library expose the `JavaPlatform` directly as a variant axis
- [ ] Allow language transform to apply directly to `JvmAssembly` instead of applying to `BinarySpec` `WithJvmAssembly`
    - This will require that all context be provided by the `LanguageSourceSet` or `JvmAssembly`

## Related work

### Prerequisite features

- [Android library variant is built from Java sources](../1-built-from-java-sources)

### Dependent features

- [Android library sources depend on JVM API provided by Android library](../3-depends-on-android-library)

----

## Usage

### Step 1. Build and fail

    # ../../gradlew myAndroidJava8

    BUILD FAILED

### Step 2. Add library dependency

    # perl -p -i -e 's|//library|library|' build.gradle

which results in the following change:

```diff
$ git diff
         myAndroidLib(MyAndroidLibrarySpec) {
             dependencies {
-                //library 'myJvmLib'
+                library 'myJvmLib'
             }
         }
     }

```

### Step 3. Build and succeed

    # ../../gradlew myAndroidJava8

    BUILD SUCCESSFUL

### Step 4. View the components report

    TODO

### Step 5. Build another variant of the Android library

    # ../../gradlew myAndroidJava6

    BUILD SUCCESSFUL


### Step 6. Observe the correct jvm library variant being used

    ????

### Step 7. Clean up

    # git checkout build.gradle
