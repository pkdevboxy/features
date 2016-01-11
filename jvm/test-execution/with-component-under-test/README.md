# Build author can declare a component under test

Story: [#113](https://github.com/gradle/langos/issues/113)

## Summary
As a build author, I can declare a component under test for a test suite, making it effectively testable. In other words, this story enabled test driven development.

## Usage

### Attempt to execute tests and fail to compile

    # $ ../../../gradlew myTestBinaryTest
    :compileMyLibJarMyLibJava
    :processMyLibJarMyLibResources
    :compileMyTestBinaryMyTestJava
    /home/cchampeau/DEV/PROJECTS/GITHUB/gradle-features/jvm/test-execution/with-component-under-test/src/myTest/java/com/acme/BlackBoxTest.java:9: error: cannot find symbol
            assertEquals(42, BlackBox.getAnswerToTheUltimateQuestionOfLifeTheUniverseAndEverything());
                             ^
      symbol:   variable BlackBox
      location: class BlackBoxTest
    1 error
    :compileMyTestBinaryMyTestJava FAILED

    FAILURE: Build failed with an exception.

    * What went wrong:
    Execution failed for task ':compileMyTestBinaryMyTestJava'.
    > Compilation failed; see the compiler error output for details.

    * Try:
    Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output.

    BUILD FAILED

### Add component under test from build script

    # $ sed -ri 's,// ,,' build.gradle

### Attempt to execute tests and see test failing

    # $ ../../../gradlew myTestMyLibJarBinaryTest
    :compileMyLibJarMyLibJava UP-TO-DATE
    :processMyLibJarMyLibResources UP-TO-DATE
    :compileMyTestMyLibJarBinaryMyTestJava UP-TO-DATE
    :myTestMyLibJarBinaryTest

    com.acme.BlackBoxTest > shouldFindTheAnswerToTheUltimateQuestionOfLifeTheUniverseAndEverything FAILED
        java.lang.AssertionError at BlackBoxTest.java:9

    1 test completed, 1 failed
    :myTestMyLibJarBinaryTest FAILED

    FAILURE: Build failed with an exception.

    * What went wrong:
    Execution failed for task ':myTestMyLibJarBinaryTest'.
    > There were failing tests. See the report at: file:///home/cchampeau/DEV/PROJECTS/GITHUB/gradle-features/jvm/test-execution/with-component-under-test/build/reports/tests/index.html

    * Try:
    Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output.

    BUILD FAILED

### Fix the code of the component under test

    # $ echo "answerToTheUltimateQuestionOfLifeTheUniverseAndEverything=42" > src/myLib/resources/answers.properties

### Attempt to execute tests and see them passing

    # $ ../../../gradlew myTestMyLibJarBinaryTest
    :compileMyLibJarMyLibJava UP-TO-DATE
    :processMyLibJarMyLibResources
    :compileMyTestMyLibJarBinaryMyTestJava
    :myTestMyLibJarBinaryTest

    BUILD SUCCESSFUL

    Total time: 1.091 secs

### Clean up

    # $ git reset --hard

----

## Test cases

 - [x] Test suite can use an API class defined in the component under test
 - [x] Test suite can use a non-API class defined in the component under test
 - [x] Test suite is executed against the runtime classes of the component under test
 - [x] Test suite can see (use) resources of the component under test
 - [x] API jar and runtime jar of the component under test are not built when test suite is executed
 - [ ] Updating sources of the component under test should trigger execution of the tests
 - [ ] Tests should not be executed if sources of component under test haven't changed
 - [x] Reasonable error message if component under test doesn't exist

