# Build user can declare a standalone JUnit test suite and execute it with gradle check

## Summary

As a build user, I should be able to create a project that consists of nothing more than a test suite with a single JUnit test. I should be able to execute that test using gradle check, and should be able to skip executing that test if all inputs are `UP-TO-DATE`.

## Usage

### Clean, build, run check and succeed

    $ ../../../gradlew clean check
    Build file '$FEATURES_HOME/jvm/test-execution/standalone-junit-test-suite/build.gradle': line 100
    The plus(Iterable<FileCollection>) method and using the '+' operator in conjunction with an Iterable<FileCollection> object have been deprecated and are scheduled to be removed in 3.0.  Please use the plus(FileCollection) method or the '+' operator with a FileCollection object instead.
    :jvm:test-execution:standalone-junit-test-suite:clean
    :jvm:test-execution:standalone-junit-test-suite:compileMySuiteJarMySuiteJava
    :jvm:test-execution:standalone-junit-test-suite:testMySuiteJar
    :jvm:test-execution:standalone-junit-test-suite:check

    BUILD SUCCESSFUL


### Build incrementally, run check and succeed

    $ ../../../gradlew check
    Build file '$FEATURES_HOME/jvm/test-execution/standalone-junit-test-suite/build.gradle': line 100
    The plus(Iterable<FileCollection>) method and using the '+' operator in conjunction with an Iterable<FileCollection> object have been deprecated and are scheduled to be removed in 3.0.  Please use the plus(FileCollection) method or the '+' operator with a FileCollection object instead.
    :jvm:test-execution:standalone-junit-test-suite:compileMySuiteJarMySuiteJava UP-TO-DATE
    :jvm:test-execution:standalone-junit-test-suite:testMySuiteJar UP-TO-DATE
    :jvm:test-execution:standalone-junit-test-suite:check UP-TO-DATE

    BUILD SUCCESSFUL


### Modify test so that it will fail

    $ perl -p -i -e 's/true/false/' src/MyTest.java

and notice it results in the following diff:

```diff
$ git diff
diff --git jvm/test-execution/standalone-junit-test-suite/src/MyTest.java jvm/test-execution/standalone-junit-test-suite/src/MyTest.java
index 6528c6a..91f3528 100644
--- jvm/test-execution/standalone-junit-test-suite/src/MyTest.java
+++ jvm/test-execution/standalone-junit-test-suite/src/MyTest.java
@@ -6,6 +6,6 @@ public class MyTest {

     @Test
     public void test() {
-        assertEquals(true, true);
+        assertEquals(false, true);
     }
 }
```

### Build incrementally, run check and fail

    $ ../../../gradlew check
    Build file '$FEATURES_HOME/jvm/test-execution/standalone-junit-test-suite/build.gradle': line 100
    The plus(Iterable<FileCollection>) method and using the '+' operator in conjunction with an Iterable<FileCollection> object have been deprecated and are scheduled to be removed in 3.0.  Please use the plus(FileCollection) method or the '+' operator with a FileCollection object instead.
    :jvm:test-execution:standalone-junit-test-suite:compileMySuiteJarMySuiteJava
    :jvm:test-execution:standalone-junit-test-suite:testMySuiteJar

    MyTest > test FAILED
        java.lang.AssertionError at MyTest.java:9

    1 test completed, 1 failed
    :jvm:test-execution:standalone-junit-test-suite:testMySuiteJar FAILED

    FAILURE: Build failed with an exception.

    * What went wrong:
    Execution failed for task ':jvm:test-execution:standalone-junit-test-suite:testMySuiteJar'.
    > There were failing tests. See the report at: file://$FEATURES_HOME/jvm/test-execution/standalone-junit-test-suite/build/reports/mySuite/index.html

    * Try:
    Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output.

    BUILD FAILED


### Clean up

    $ git checkout src
