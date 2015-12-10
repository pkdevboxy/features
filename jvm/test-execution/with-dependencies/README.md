# Build author can declare dependencies for test suite sources

## Summary
As a build author, I should be able to declare which dependencies (internal or external) my test suite sources depend on.

The JUnit test class in this project depends on an external library (hamcrest). It should be possible to declare that dependency in the build script, then compile and execute the test successfully. If the dependency is missing from the build script, the test suite should fail to compile.

## Usage

### Attempt to execute tests and succeed

    $ ../../../gradlew clean mySuiteSuite
    :jvm:test-execution:with-dependencies:clean
    :jvm:test-execution:with-dependencies:compileMySuiteSuiteMySuiteJava
    :jvm:test-execution:with-dependencies:mySuiteSuiteTest
    :jvm:test-execution:with-dependencies:mySuiteSuite

    BUILD SUCCESSFUL


### Remove dependency from build script
Create a new build script that does not contain the hamcrest dependency.

    $ grep -v hamcrest build.gradle > build.nodep.gradle

### Attempt to execute tests and fail to compile
Now run against the new build script and fail to compile as expected.

    $ ../../../gradlew -b build.nodep.gradle clean mySuiteSuite
    :clean
    $FEATURES_HOME/jvm/test-execution/with-dependencies/src/test/java/MyTest.java:3: error: cannot find symbol
    import static org.hamcrest.Matchers.is;
                              ^
      symbol:   class Matchers
      location: package org.hamcrest
    $FEATURES_HOME/jvm/test-execution/with-dependencies/src/test/java/MyTest.java:3: error: static import only from classes and interfaces
    import static org.hamcrest.Matchers.is;
    ^
    $FEATURES_HOME/jvm/test-execution/with-dependencies/src/test/java/MyTest.java:10: error: cannot find symbol
            assertThat(2 + 2, is(4));
                              ^
      symbol:   method is(int)
      location: class MyTest
    3 errors
    :compileMySuiteSuiteMySuiteJava FAILED

    FAILURE: Build failed with an exception.

    * What went wrong:
    Execution failed for task ':compileMySuiteSuiteMySuiteJava'.
    > Compilation failed; see the compiler error output for details.

    * Try:
    Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output.

    BUILD FAILED


### Clean up

    $ rm build.nodep.gradle

----

## Test cases
 - [x] Test suite can depend on an external library
 - [x] Test suite can depend on a local library
   - this makes sure that a test suite is also a regular JVM library
 - [x] Test classes cannot consume (aka test) a non-API class of a dependency
   - this makes sure that we make the difference between a dependency and a component under test. A dependency is used only to build and execute tests, but has nothing to do with a component under test, for which we will want to test non-API classes too.
   
## New test cases

- [ ] Verify runtime variant of local library is used at test execution time, and that its runtime dependencies are available
- [ ] Verify runtime dependencies of external library are available at test execution time
- [ ] Extend incremental build test from previous story to include a dependency on local library
