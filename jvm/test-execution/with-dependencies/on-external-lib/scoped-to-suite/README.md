# Build author can declare test suite-wide dependency on external library

Story: [#126](https://github.com/gradle/langos/issues/126)

## Summary
As a build author, I can declare that an entire test suite depends on an external library so that I don't have to duplicate dependency declarations across multiple source sets.

## Usage

### Attempt to execute tests and succeed

    ../../../../../gradlew clean mySuiteBinary
    :jvm:test-execution:with-dependencies:on-external-lib:scoped-to-source-set:clean
    :jvm:test-execution:with-dependencies:on-external-lib:scoped-to-source-set:compileMySuiteBinaryMySuiteMySrc1
    :jvm:test-execution:with-dependencies:on-external-lib:scoped-to-source-set:compileMySuiteBinaryMySuiteMySrc2
    :jvm:test-execution:with-dependencies:on-external-lib:scoped-to-source-set:mySuiteBinaryTest
    :jvm:test-execution:with-dependencies:on-external-lib:scoped-to-source-set:mySuiteBinary

    BUILD SUCCESSFUL

### Remove dependency from build script
Create a new build script that does not contain the hamcrest dependency.

    $ grep -v hamcrest build.gradle > build.nodep.gradle

### Attempt to execute tests and fail to compile

    $ ../../../../../gradlew -b build.nodep.gradle clean mySuiteBinary
    Now run against the new build script and fail to compile as expected.
    :clean
    :compileMySuiteBinaryMySuiteMySrc1
    $FEATURES_HOME/jvm/test-execution/with-dependencies/on-external-lib/scoped-to-suite/src/mySuite/mySrc1/MyTest1.java:3: error: cannot find symbol
    import static org.hamcrest.Matchers.is;
                              ^
      symbol:   class Matchers
      location: package org.hamcrest
    $FEATURES_HOME/jvm/test-execution/with-dependencies/on-external-lib/scoped-to-suite/src/mySuite/mySrc1/MyTest1.java:3: error: static import only from classes and interfaces
    import static org.hamcrest.Matchers.is;
    ^
    $FEATURES_HOME/jvm/test-execution/with-dependencies/on-external-lib/scoped-to-suite/src/mySuite/mySrc1/MyTest1.java:10: error: cannot find symbol
            assertThat(2 + 2, is(4));
                              ^
      symbol:   method is(int)
      location: class MyTest1
    3 errors
    :compileMySuiteBinaryMySuiteMySrc1 FAILED

    FAILURE: Build failed with an exception.

    * What went wrong:
    Execution failed for task ':compileMySuiteBinaryMySuiteMySrc1'.
    > Compilation failed; see the compiler error output for details.

    * Try:
    Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output.

    BUILD FAILED


### Clean up

    $ rm build.nodep.gradle

----

## Test cases

 - [x] Test suite can depend on an external library and all source sets can compile against external library's API
