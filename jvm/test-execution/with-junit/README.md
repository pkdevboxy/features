# Build author can declare a JUnit test suite

## Summary

As a build user, I should be able to create a project that consists of a standalone JUnit test suite. I should be able to execute that test suite using `gradle mySuiteTest`, and should be able to skip executing if all inputs are `UP-TO-DATE`.

To be clear, there is no component under test in this story. Just a test suite.

## Usage

### Clean, build, run mySuiteTest and succeed

    $ ../../../gradlew clean mySuiteTest
    :jvm:test-execution:with-junit:clean
    :jvm:test-execution:with-junit:compileMySuiteMySuiteMySuiteJava
    :jvm:test-execution:with-junit:mySuiteTest

    BUILD SUCCESSFUL


### Build incrementally, run mySuiteTest and succeed

    $ ../../../gradlew mySuiteTest
    :jvm:test-execution:with-junit:compileMySuiteMySuiteMySuiteJava UP-TO-DATE
    :jvm:test-execution:with-junit:mySuiteTest UP-TO-DATE

    BUILD SUCCESSFUL


### Modify test so that it will fail

    $ perl -p -i -e 's/true/false/' src/MyTest.java

and notice it results in the following diff:

    $ git diff .
    @@ -6,6 +6,6 @@ public class MyTest {

         @Test
         public void test() {
    -        assertEquals(true, true);
    +        assertEquals(false, true);
         }
     }

### Build incrementally, run mySuiteTest and fail

    $ ../../../gradlew mySuiteTest
    :jvm:test-execution:with-junit:compileMySuiteMySuiteMySuiteJava
    :jvm:test-execution:with-junit:mySuiteTest

    MyTest > test FAILED
        java.lang.AssertionError at MyTest.java:9

    1 test completed, 1 failed
    :jvm:test-execution:with-junit:mySuiteTest FAILED

    FAILURE: Build failed with an exception.

    * What went wrong:
    Execution failed for task ':jvm:test-execution:with-junit:mySuiteTest'.
    > There were failing tests. See the report at: file://$FEATURES_HOME/jvm/test-execution/with-junit/build/reports/tests/index.html

    * Try:
    Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output.

    BUILD FAILED


### Clean up

    $ git checkout src

## Test cases

 - ~~Should fail if no junitVersion is specified~~
 - ~~Should fail if no version of JUnit is specified, even if junit is found in the dependencies~~
 - ~~Test suite containing a true assertion should pass~~
   - ~~makes sure compilation passes, but doesn't make sure that tests are actually executed~~
 - ~~Test suite containing a false assertion should fail, and build fails as a consequence~~
   - ~~makes sure that tests are really executed~~
 - ~~Build log should include the link to the report~~
   - ~~makes sure that we reuse the `Test` task and configure it with reasonable defaults~~
 - ~~Test suite should not have a jar~~
 - ~~Should fall back to a conventional source location if no source dirs are explicitly declared~~
 - ~~Decent error message when another JVM component declares a dependency on a test suite.~~
 - ~~Decent error message when another JVM component declares a dependency a project that contains only a test suite.~~
 - Running `gradle assemble` does not build or run the test suite


## Implementation notes

 - Use existing test fixtures when querying test results
