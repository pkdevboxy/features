# Build user can declare a standalone JUnit test suite and execute it with gradle check

## Summary

As a build user, I should be able to create a project that consists of nothing more than a test suite with a single JUnit test. I should be able to execute that test using gradle check, and should be able to skip executing that test if all inputs are `UP-TO-DATE`.

## Usage

### Clean, build, run check and suceed

    # ../gradlew clean check
    :jvm:test-execution:clean UP-TO-DATE
    :jvm:test-execution:compileMySuiteJava
    :jvm:test-execution:mySuiteTest
    :jvm:test-execution:check

    BUILD SUCCESSFUL


### Build incrementally, run check and succeed

    # ../gradlew check
    :jvm:test-execution:compileMySuiteJava: UP-TO-DATE
    :jvm:test-execution:mySuiteTest: UP-TO-DATE
    :jvm:test-execution:check UP-TO-DATE

    BUILD SUCCESSFUL


### Build incrementally, run check and fail

    # change assertion in test from true to false

    # ../gradlew check
    :jvm:test-execution:compileMySuiteJava
    :jvm:test-execution:mySuiteTest

    BUILD FAILED
