# Build author can declare a test suite with resources

## Summary
As a build author, I should be able to place resources (properties files, etc) in a location that ensures they end up on the classpath and that individual tests can consume / depend on them.

## Usage

### Attempt to execute tests and succeed

    $ ../../../gradlew clean mySuiteBinary
    :jvm:test-execution:with-resources:clean
    :jvm:test-execution:with-resources:compileMySuiteBinaryMySuiteJava
    :jvm:test-execution:with-resources:processMySuiteBinaryMySuiteResources
    :jvm:test-execution:with-resources:mySuiteBinaryTest
    :jvm:test-execution:with-resources:mySuiteBinary

    BUILD SUCCESSFUL


### Remove a resource

    $ rm src/test/resources/test.properties

### Attempt to execute tests and fail

    $ ../../../gradlew clean mySuiteBinary
    :jvm:test-execution:with-resources:clean
    :jvm:test-execution:with-resources:compileMySuiteBinaryMySuiteJava
    :jvm:test-execution:with-resources:mySuiteBinaryTest

    MyTest > test FAILED
        java.lang.AssertionError at MyTest.java:14

    1 test completed, 1 failed
    :jvm:test-execution:with-resources:mySuiteBinaryTest FAILED

    FAILURE: Build failed with an exception.

    * What went wrong:
    Execution failed for task ':jvm:test-execution:with-resources:mySuiteBinaryTest'.
    > There were failing tests. See the report at: file://$FEATURES_HOME/jvm/test-execution/with-resources/build/reports/tests/index.html

    * Try:
    Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output.

    BUILD FAILED


### Clean up

    $ git checkout src

----

## Test cases
 - [x] Test can consume resources
   - this ensures that test resources are present on the classpath
 - [x] Test resources can be found in a conventional location if not explicitly specified
 - [x] Test resources can be found in a non-conventional location if explicitly specified
 - [x] Resources supplied to test execution are processed via the `ProcessResources` task

## New test cases
- [x] Extend incremental build test case from previous stories to include some test resources
