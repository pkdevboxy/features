# Build author can declare test suite-wide dependency on external library

Story: [#126](https://github.com/gradle/langos/issues/126)

## Summary
As a build author, I can declare that an entire test suite depends on an external library so that I don't have to duplicate dependency declarations across multiple source sets.

## Usage

### Attempt to execute tests and succeed

    # ../../../../../gradlew clean mySuiteBinary
    :jvm:test-execution:with-dependencies:on-external-lib:scoped-to-source-set:clean
    :jvm:test-execution:with-dependencies:on-external-lib:scoped-to-source-set:compileMySuiteBinaryMySuiteMySrc1
    :jvm:test-execution:with-dependencies:on-external-lib:scoped-to-source-set:compileMySuiteBinaryMySuiteMySrc2
    :jvm:test-execution:with-dependencies:on-external-lib:scoped-to-source-set:mySuiteBinaryTest
    :jvm:test-execution:with-dependencies:on-external-lib:scoped-to-source-set:mySuiteBinary

    BUILD SUCCESSFUL

### Remove dependency from build script
TODO

### Attempt to execute tests and fail to compile
TODO

## Test cases

 - [ ] Test suite can depend on an external library and all source sets can compile against external library's API
