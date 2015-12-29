# Reuse the existing `testSuites` container in the JVM software model

Story: [#117](https://github.com/gradle/langos/issues/117) (`debt`)

## Summary
This will allow us a proper way to avoid building and executing tests when `gradle assemble` is run and when this is complete, bug #118 should be fixed as a side effect.

## Usage

### Assemble and succeed with no test suite being compiled or executed

    $ ../../../gradlew assemble
    :createMyLibJar
    :myLibApiJar UP-TO-DATE
    :myLibJar
    :assemble

    BUILD SUCCESSFUL

    Total time: 3.451 secs


### Print components report and notice test suite details

    $ ../../../gradlew components
    :components

    ------------------------------------------------------------
    Root project
    ------------------------------------------------------------

    JVM library 'myLib'
    -------------------

    Source sets
        Java source 'myLib:java'
            srcDir: src/myLib/java
        JVM resources 'myLib:resources'
            srcDir: src/myLib/resources

    Binaries
        Jar 'myLib:jar'
            build using task: :myLibJar
            targetPlatform: Java SE 7
            tool chain: JDK 7 (1.7)
            classes dir: build/classes/myLib/jar
            resources dir: build/resources/myLib/jar
            API Jar file: build/jars/myLib/jar/api/myLib.jar
            Jar file: build/jars/myLib/jar/myLib.jar

    JUnitTestSuiteSpec 'mySuite'
    ----------------------------

    Source sets
        Java source 'mySuite:java'
            srcDir: src/mySuite/java
        JVM resources 'mySuite:resources'
            srcDir: src/mySuite/resources

    Binaries
        Test 'mySuite:binary'
            build using task: :mySuiteBinary
            targetPlatform: Java SE 7

    Note: currently not all plugins register their components, so some components may not be visible here.

    BUILD SUCCESSFUL

    Total time: 3.583 secs


## Implementation Goals

 - [x] Must extract common infrastructure from 'testing-native', and share a single `TestSuiteContainer` instance

## Test cases

 - [x] running `gradle assemble` does not build or run the test suite
 - [x] `testSuites` container should accept multiple test suites of different types (`CUnit`, `JUnit`)
 - [x] test suites should appear in components report (see `TestingNativeComponentReportIntegrationTest`)
