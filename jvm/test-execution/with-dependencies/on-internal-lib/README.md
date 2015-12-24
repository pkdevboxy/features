# Build author can declare test suite dependency on internal library

Story: [#127](https://github.com/gradle/langos/issues/127)

## Summary
As a build author, I can declare that a test suite depends on a internal library so that build users can compile and execute tests against that library including compiling against its API-level dependencies and executing against its runtime dependencies.

Note that this story assumes an internal library dependency scoped at the source set level, but it should be possible to scope the dependency to the entire test suite as well. Support for these two scopes is already handled by the [on-external-lib](../on-external-lib) stories, so we are forgoing duplicate coverage here.

## Usage

### Attempt to execute tests and succeed

Note: the following fails with `UnsupportedOperationException` due to running against API jar (see corresponding test case below).

    # ../../../../gradlew clean mySuiteBinary
    :jvm:test-execution:with-dependencies:on-internal-lib:clean UP-TO-DATE
    :jvm:test-execution:with-dependencies:on-internal-lib:compileMyTestingUtilsJarMyTestingUtilsJava
    :jvm:test-execution:with-dependencies:on-internal-lib:myTestingUtilsApiJar
    :jvm:test-execution:with-dependencies:on-internal-lib:compileMyTestingLibJarMyTestingLibJava
    :jvm:test-execution:with-dependencies:on-internal-lib:myTestingLibApiJar
    :jvm:test-execution:with-dependencies:on-internal-lib:compileMySuiteBinaryMySuiteJava
    :jvm:test-execution:with-dependencies:on-internal-lib:mySuiteBinaryTest
    :jvm:test-execution:with-dependencies:on-internal-lib:mySuiteBinary

    BUILD SUCCESSFUL

### Remove dependency from build script

    $ grep -v "library 'myTestingLib'" build.gradle > build.nodep.gradle

### Attempt to execute tests and fail to compile

    $ ../../../../gradlew -b build.nodep.gradle clean mySuiteBinary
    :clean
    :compileMySuiteBinaryMySuiteJava
    /home/cchampeau/DEV/PROJECTS/GITHUB/gradle-features/jvm/test-execution/with-dependencies/on-internal-lib/src/mySuite/java/MyTest.java:4: error: package my.testing does not exist
    import static my.testing.Utils.*;
                            ^
    /home/cchampeau/DEV/PROJECTS/GITHUB/gradle-features/jvm/test-execution/with-dependencies/on-internal-lib/src/mySuite/java/MyTest.java:5: error: package my.testing does not exist
    import static my.testing.Lib.*;
                            ^
    /home/cchampeau/DEV/PROJECTS/GITHUB/gradle-features/jvm/test-execution/with-dependencies/on-internal-lib/src/mySuite/java/MyTest.java:11: error: cannot find symbol
            assertThat(FORTY_TWO, isTheAnswerToTheUltimateQuestionOfLifeTheUniverseAndEverything());
                       ^
      symbol:   variable FORTY_TWO
      location: class MyTest
    /home/cchampeau/DEV/PROJECTS/GITHUB/gradle-features/jvm/test-execution/with-dependencies/on-internal-lib/src/mySuite/java/MyTest.java:11: error: cannot find symbol
            assertThat(FORTY_TWO, isTheAnswerToTheUltimateQuestionOfLifeTheUniverseAndEverything());
                                  ^
      symbol:   method isTheAnswerToTheUltimateQuestionOfLifeTheUniverseAndEverything()
      location: class MyTest
    4 errors
    :compileMySuiteBinaryMySuiteJava FAILED

    FAILURE: Build failed with an exception.

    * What went wrong:
    Execution failed for task ':compileMySuiteBinaryMySuiteJava'.
    > Compilation failed; see the compiler error output for details.

    * Try:
    Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output.

    BUILD FAILED

## Cleanup

    $ rm build.nodep.gradle

## Test cases

 - [x] Test suite can depend on a internal library
   - this makes sure that a test suite is also a regular JVM library
 - [x] Test classes cannot consume (aka test) a non-API class of a dependency
   - this makes sure that we make the difference between a dependency and a component under test. A dependency is used only to build and execute tests, but has nothing to do with a component under test, for which we will want to test non-API classes too.
 - [ ] Test suite should execute against runtime jar of internal library and its transitive dependencies
 - [ ] Extend incremental build test case from previous story to include a dependency on internal library
 - [ ] Verify runtime dependencies of external library are available at test execution time
   - even though this story is fundamentally about test suite dependencies on an internal library, it currently includes dependencies on internal *and* external libraries, where the latter brings in transitive 'runtime' dependencies, so it is a good candidate for dealing with this test case.
