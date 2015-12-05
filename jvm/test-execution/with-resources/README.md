# Build author can declare a test suite with resources

## Summary
As a build author, I should be able to place resources (properties files, etc) in a location that ensures they end up on the classpath and that individual tests can consume / depend on them.

_**Note**: The build script and presence of files in `src/test/resources` in this project currently cause the build to fail at configuration time with the following error_:

    $ ../../../gradlew

    FAILURE: Build failed with an exception.

    * What went wrong:
    A problem occurred configuring root project 'with-resources'.
    > Exception thrown while executing model rule: ComponentModelBasePlugin.Rules#createSourceTransformTasks
       > java.lang.NullPointerException (no error message)

       * Try:
       Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output.

       BUILD FAILED

_For this reason, this project is currently excluded from the root `settings.gradle` file. Re-enable it when the issue above is resolved._



## Usage

### Attempt to execute tests and succeed

    $ ../../../gradlew clean mySuiteTest
    :jvm:test-execution:with-resources:clean
    :jvm:test-execution:with-resources:compileMySuiteMySuiteMySuiteJava
    :jvm:test-execution:with-resources:mySuiteTest

    BUILD SUCCESSFUL

### Remove a resource

    $ rm src/test/resources/test.properties

### Attempt to execute tests and fail

    $ ../../../gradlew clean mySuiteTest

    BUILD FAILED

----

## Test cases
 - [ ] Test can consume resources
   - this ensures that test resources are present on the classpath
 - [ ] Test resources can be found in a conventional location if not explicitly specified
 - [ ] Test resources can be found in a non-conventional location if explicitly specified
