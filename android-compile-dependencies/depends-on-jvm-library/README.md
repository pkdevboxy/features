# Android library sources depend on JVM library

## User stories

- [ ] Android library can have a source-set dependency on a JVM library API, which is used for compiling Java sources
- [ ] Android library can have a component dependency on a JVM library API, which is used for compiling Java sources
- [ ] Android library sources are compiled with the JVM variant corresponding to the Android platform of a consuming variant

## Related work

### Prerequisite features

### Dependent features

## Executable spec

### Step 1. Build and fail

    $ ../../gradlew myAndroidJava8

    BUILD FAILED

### Step 2. Add library dependency

    $ perl -p -i -e 's|//||' build.gradle

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

    $ ../../gradlew myAndroidJava8

    BUILD SUCCESSFUL

### Step 4. View the components report

### Step 5. Build another variant of the Android library

    $ ../../gradlew myAndroidJava6

    BUILD SUCCESSFUL


### Step 6. Observe the correct jvm library variant being used

    ????

