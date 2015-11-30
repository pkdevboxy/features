# JVM Test Execution

## Summary

## Usage

### Clean build with tests passing

    # ../gradlew clean check
    :jvm-test-execution:compileMySuiteJava
    :jvm-test-execution:mySuiteTest

    BUILD SUCCESSFUL

    Total time: 0.0 secs

### Incremental build with tests passing

    # ../gradlew check
    :jvm-test-execution:compileMySuiteJava: UP-TO-DATE
    :jvm-test-execution:mySuiteTest: UP-TO-DATE

    BUILD SUCCESSFUL

    Total time: 0.0 secs

### Incremental build with tests failing

    # change assertion in test from true to false

    # ../gradlew check
    :jvm-test-execution:compileMySuiteJava
    :jvm-test-execution:mySuiteTest

    BUILD FAILED

    Total time: 0.0 secs
