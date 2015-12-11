# Build author can declare a JUnit test suite

## Summary

As a build user, I should be able to create a project that consists of a standalone JUnit test suite. I should be able to execute that test suite using `gradle mySuiteBinary`, and should be able to skip executing if all inputs are `UP-TO-DATE`.

To be clear, there is no component under test in this story. Just a test suite.

## Usage

### Clean, build, run mySuiteBinary and succeed

    $ ../../../gradlew clean mySuiteBinary
    :jvm:test-execution:with-junit:clean
    :jvm:test-execution:with-junit:compileMySuiteBinaryMySuiteJava
    :jvm:test-execution:with-junit:mySuiteBinaryTest
    :jvm:test-execution:with-junit:mySuiteBinary

    BUILD SUCCESSFUL


### Build incrementally, run mySuiteBinary and succeed

    $ ../../../gradlew mySuiteBinary
    :jvm:test-execution:with-junit:compileMySuiteBinaryMySuiteJava UP-TO-DATE
    :jvm:test-execution:with-junit:mySuiteBinaryTest UP-TO-DATE
    :jvm:test-execution:with-junit:mySuiteBinary UP-TO-DATE

    BUILD SUCCESSFUL


### Modify test so that it will fail

    $ perl -p -i -e 's/true/false/' src/MyTest.java

and notice it results in the following diff:

```diff
$ git diff .
@@ -6,6 +6,6 @@ public class MyTest {

     @Test
     public void test() {
-        assertEquals(true, true);
+        assertEquals(false, true);
     }
 }
```

### Build incrementally, run mySuiteBinary and fail

    $ ../../../gradlew mySuiteBinary
    :jvm:test-execution:with-junit:compileMySuiteBinaryMySuiteJava
    :jvm:test-execution:with-junit:mySuiteBinaryTest

    MyTest > test FAILED
        java.lang.AssertionError at MyTest.java:9

    1 test completed, 1 failed
    :jvm:test-execution:with-junit:mySuiteBinaryTest FAILED

    FAILURE: Build failed with an exception.

    * What went wrong:
    Execution failed for task ':jvm:test-execution:with-junit:mySuiteBinaryTest'.
    > There were failing tests. See the report at: file://$FEATURES_HOME/jvm/test-execution/with-junit/build/reports/tests/index.html

    * Try:
    Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output.

    BUILD FAILED


### Clean up

    $ git checkout src

## Test cases

 - [x] Should fail if no junitVersion is specified
 - [x] Should fail if no version of JUnit is specified, even if junit is found in the dependencies
 - [x] Test suite containing a true assertion should pass
   - this ensures compilation passes, but doesn't make sure that tests are actually executed
 - [x] Test suite containing a false assertion should fail, and build fails as a consequence
   - this ensures that tests are really executed
 - [x] Build log should include the link to the report
   - this ensures that we reuse the `Test` task and configure it with reasonable defaults
 - [x] Test suite should not have a jar
 - [x] Should fall back to a conventional source location if no source dirs are explicitly declared
 - [x] Decent error message when another JVM component declares a dependency on a test suite.
 - [x] Decent error message when another JVM component declares a dependency a project that contains only a test suite.
 - [x] Conventional location is used for test sources if none declared
 - [x] Decent error message when no repositories declared
 - [x] Should re-execute test when source file changes
 - [x] Should *not* re-execute test when no source file changes

## Implementation notes

 - Use existing test fixtures when querying test results
