# Build user can declare a standalone JUnit test suite and execute it with `gradle mySuiteTest`

## Summary

As a build user, I should be able to create a project that consists of nothing more than a test suite with a single JUnit test. I should be able to execute that test using `gradle mySuiteTest`, and should be able to skip executing that test if all inputs are `UP-TO-DATE`.

## Usage

### Clean, build, run mySuiteTest and succeed

    $ ../../../gradlew clean mySuiteTest
    Build file '$FEATURES_HOME/jvm/test-execution/standalone-junit-test-suite/build.gradle': line 100
    The plus(Iterable<FileCollection>) method and using the '+' operator in conjunction with an Iterable<FileCollection> object have been deprecated and are scheduled to be removed in 3.0.  Please use the plus(FileCollection) method or the '+' operator with a FileCollection object instead.
    :jvm:test-execution:standalone-junit-test-suite:clean
    :jvm:test-execution:standalone-junit-test-suite:compileMySuiteJarMySuiteJava
    :jvm:test-execution:standalone-junit-test-suite:mySuiteTest

    BUILD SUCCESSFUL


### Build incrementally, run mySuiteTest and succeed

    $ ../../../gradlew mySuiteTest
    Build file '$FEATURES_HOME/jvm/test-execution/standalone-junit-test-suite/build.gradle': line 100
    The plus(Iterable<FileCollection>) method and using the '+' operator in conjunction with an Iterable<FileCollection> object have been deprecated and are scheduled to be removed in 3.0.  Please use the plus(FileCollection) method or the '+' operator with a FileCollection object instead.
    :jvm:test-execution:standalone-junit-test-suite:compileMySuiteJarMySuiteJava UP-TO-DATE
    :jvm:test-execution:standalone-junit-test-suite:mySuiteTest UP-TO-DATE

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

### Build incrementally, run mySuiteTest and fail

    $ ../../../gradlew mySuiteTest
    Build file '$FEATURES_HOME/jvm/test-execution/standalone-junit-test-suite/build.gradle': line 100
    The plus(Iterable<FileCollection>) method and using the '+' operator in conjunction with an Iterable<FileCollection> object have been deprecated and are scheduled to be removed in 3.0.  Please use the plus(FileCollection) method or the '+' operator with a FileCollection object instead.
    :jvm:test-execution:standalone-junit-test-suite:compileMySuiteJarMySuiteJava
    :jvm:test-execution:standalone-junit-test-suite:mySuiteTest

    MyTest > test FAILED
        java.lang.AssertionError at MyTest.java:9

    1 test completed, 1 failed
    :jvm:test-execution:standalone-junit-test-suite:mySuiteTest FAILED

    FAILURE: Build failed with an exception.

    * What went wrong:
    Execution failed for task ':jvm:test-execution:standalone-junit-test-suite:mySuiteTest'.
    > There were failing tests. See the report at: file://$FEATURES_HOME/jvm/test-execution/standalone-junit-test-suite/build/reports/mySuite/index.html

    * Try:
    Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output.

    BUILD FAILED


### Clean up

    $ git checkout src

## Test cases

- Should fail if no junitVersion is specified
- Should fail if no version of JUnit is specified, even if junit is found in the dependencies
- Test suite containing a true assertion should pass : makes sure compilation passes, but doesn't make sure that tests are actually executed
- Test suite containing a false assertion should fail, and build fails as a consequence : makes sure that tests are really executed
- Build log should include the link to the report : makes sure that we reuse the `Test` task and configure it with reasonable defaults
- Test suite should not have a jar
- test can use resources: makes sure that test resources are found on classpath
- Test component can depend on a local library : makes sure that a test suite is also a regular JVM library
- Test component can depend on an external library
- Test cannot use (aka test) a non-API class of a dependency : makes sure that we make the difference between a dependency and a component under test. A dependency is used only to build and execute tests, but has nothing to do with a component under test, for which we will want to test non-API classes too.
- Should fall back to a conventional source location if no source dirs are explicitly declared
- Running `gradle assemble` does not build or run the test suite
- Decent error message when another JVM component declares a dependency on a test suite.
- Decent error message when another JVM component declares a dependency a project that contains only a test suite.


## Implementation notes

- Use existing test fixtures when querying test results
