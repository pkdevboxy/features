# Android library sources depend on JVM API provided by Android library

## User stories

- [ ] Android library variant has a JVM API
  - Model the variant API as a first class buildable thing
  - Include artifact and dependencies
  - Show API 'jar' in the component report
  - Can be a target for dependency management
- [ ] Android library gets correct variant of Android library when resolving
- [ ] Jvm library gets correct variant of Android library when resolving
- [ ] JVM API variant jar is automatically built based on presence of `JvmAssembly` and `JvmApiSpec` on Android library

## Related work

### Prerequisite features

- [Android library sources depend on JVM library](../2-depends-on-jvm-library)

### Dependent features

## Not in scope

- Different APIs per variant
- Public instantiation of JvmApi instance

## Usage

### Step 1. Build and fail

    # ../../gradlew myAndroidJava8

    BUILD FAILED

### Step 2. Add android library dependency

    $ perl -p -i -e 's|//library|library|' build.gradle

which results in the following change:

```diff
$ git diff
         myAndroidLib(MyAndroidLibrarySpec) {
             dependencies {
-                //library 'myOtherAndroidLib'
+                library 'myOtherAndroidLib'
             }
         }
     }

```

### Step 3. Build and succeed

    # ../../gradlew myAndroidJava8

    BUILD SUCCESSFUL

### Step 4. View the components report

### Step 5. Build another variant of the Android library

    # ../../gradlew myAndroidJava6

    BUILD SUCCESSFUL

### Step 6. Observe the correct jvm library variant being used

    ????

### Step 7. Clean up

    $ git checkout build.gradle
