# Android library sources depend on JVM API provided by Android library


## Executable spec

### Step 1. Build and fail

    $ ../../gradlew myAndroidJava8

    BUILD FAILED

### Step 2. Add android library dependency

    $ perl -p -i -e 's|//||' build.gradle

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

    $ ../../gradlew myAndroidJava8

    BUILD SUCCESSFUL

### Step 4. View the components report

### Step 5. Build another variant of the Android library

    $ ../../gradlew myAndroidJava6

    BUILD SUCCESSFUL


### Step 6. Observe the correct jvm library variant being used

    ????

