# Build author can declare test suite source set dependency on external library

Story: [#125](https://github.com/gradle/langos/issues/125)

## Summary
As a build author, I can declare that a specific source set of a test suite depends on an external library so that build users can compile and execute tests successfully.

## Usage

### Attempt to execute tests and succeed

    $ ../../../../../gradlew mySuiteBinary
    Download https://jcenter.bintray.com/org/hamcrest/hamcrest-all/1.3/hamcrest-all-1.3.pom
    Download https://jcenter.bintray.com/org/hamcrest/hamcrest-parent/1.3/hamcrest-parent-1.3.pom
    Download https://jcenter.bintray.com/junit/junit/4.12/junit-4.12.pom
    Download https://jcenter.bintray.com/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.pom
    :jvm:test-execution:with-dependencies:on-external-lib:scoped-to-source-set:compileMySuiteBinaryMySuiteJava
    Download https://jcenter.bintray.com/org/hamcrest/hamcrest-all/1.3/hamcrest-all-1.3.jar
    Download https://jcenter.bintray.com/junit/junit/4.12/junit-4.12.jar
    Download https://jcenter.bintray.com/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar
    :jvm:test-execution:with-dependencies:on-external-lib:scoped-to-source-set:mySuiteBinaryTest
    :jvm:test-execution:with-dependencies:on-external-lib:scoped-to-source-set:mySuiteBinary

    BUILD SUCCESSFUL


### Remove dependency from build script
Create a new build script that does not contain the hamcrest dependency.

    $ grep -v hamcrest build.gradle > build.nodep.gradle

### Attempt to execute tests and fail to compile
Now run against the new build script and fail to compile as expected.

    $ ../../../../../gradlew -b build.nodep.gradle mySuiteBinary
    $FEATURES_HOME/jvm/test-execution/with-dependencies/on-external-lib/scoped-to-source-set/src/mySuite/java/MyTest.java:3: error: cannot find symbol
    import static org.hamcrest.Matchers.is;
                              ^
      symbol:   class Matchers
      location: package org.hamcrest
    $FEATURES_HOME/jvm/test-execution/with-dependencies/on-external-lib/scoped-to-source-set/src/mySuite/java/MyTest.java:3: error: static import only from classes and interfaces
    import static org.hamcrest.Matchers.is;
    ^
    $FEATURES_HOME/jvm/test-execution/with-dependencies/on-external-lib/scoped-to-source-set/src/mySuite/java/MyTest.java:10: error: cannot find symbol
            assertThat(2 + 2, is(4));
                              ^
      symbol:   method is(int)
      location: class MyTest
    3 errors
    :compileMySuiteBinaryMySuiteJava FAILED

    FAILURE: Build failed with an exception.

    * What went wrong:
    Execution failed for task ':compileMySuiteBinaryMySuiteJava'.
    > Compilation failed; see the compiler error output for details.

    * Try:
    Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output.

    BUILD FAILED


### Clean up

    $ rm build.nodep.gradle

----

## Test cases

 - [x] Test suite can depend on an external library
