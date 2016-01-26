# Java compile tasks select "best" compatible JDK

Story: https://github.com/gradle/langos/issues/154

## Summary

This story will make Gradle use the declared JDK and choose the most appropriate version to compile Java classes, while being lenient: if a strict match is found, the toolchain will be used. If a superior version of the JDK is found, then we would select the *closest* version (no failure).

## Usage

    # ../../../gradlew components
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
        Jar 'myLib:java6Jar'
            build using task: :myLibJava6Jar
            target platform: Java SE 6
            tool chain: OpenJDK 6 (1.6)
            classes dir: build/classes/myLib/java6Jar
            resources dir: build/resources/myLib/java6Jar
            API Jar file: build/jars/myLib/java6Jar/api/myLib.jar
            Jar file: build/jars/myLib/java6Jar/myLib.jar
        Jar 'myLib:java7Jar'
            build using task: :myLibJava7Jar
            target platform: Java SE 7
            tool chain: OracleJDK 7 (1.7)
            classes dir: build/classes/myLib/java7Jar
            resources dir: build/resources/myLib/java7Jar
            API Jar file: build/jars/myLib/java7Jar/api/myLib.jar
            Jar file: build/jars/myLib/java7Jar/myLib.jar

    JUnit test suite 'myTest'
    -------------------------

    Source sets
        Java source 'myTest:java'
            srcDir: src/myTest/java
        JVM resources 'myTest:resources'
            srcDir: src/myTest/resources

    Binaries
        Test suite 'myTest:myLibJava6JarBinary'
            build using task: :myTestMyLibJava6JarBinary
            run using task: :myTestMyLibJava6JarBinaryTest
            target platform: Java SE 6
            JUnit version: 4.12
            component under test: JVM library 'myLib'
            binary under test: Jar 'myLib:java6Jar'
            tool chain: OpenJDK 6 (1.6)
            classes dir: build/classes/myTest/myLibJava6JarBinary
            resources dir: build/resources/myTest/myLibJava6JarBinary
        Test suite 'myTest:myLibJava7JarBinary'
            build using task: :myTestMyLibJava7JarBinary
            run using task: :myTestMyLibJava7JarBinaryTest
            target platform: Java SE 7
            JUnit version: 4.12
            component under test: JVM library 'myLib'
            binary under test: Jar 'myLib:java7Jar'
            tool chain: OracleJDK 7 (1.7)
            classes dir: build/classes/myTest/myLibJava7JarBinary
            resources dir: build/resources/myTest/myLibJava7JarBinary

    Note: currently not all plugins register their components, so some components may not be visible here.

    BUILD SUCCESSFUL



## Implementation Goals

In this story we assume that we are lenient, like the current implementation, we regards to choosing a compatible JDK: strictly speaking, when compiling, we should choose the exact version of the JDK that is required by a binary and fail if no such JDK is declared. However, for practical reasons we might just want to choose the closest compatible version and possibly warn about it (the Java compiler will emit a warning in any case).

 - [ ] Implementation forks javac, if the chosen JDK is not the one that Gradle runs with.
 - [ ] To compile Java source, select the closest compatible toolchain to compile the variant
 - [ ] The version of Java that Gradle runs on has to be included in the selection process (meaning that it should be preferred over declared installed JDKs if it matches the target platform)

 It is a non-goal to support JDK 9 specific flags, that are there to support backwards compilation enforcing the API level (see http://openjdk.java.net/jeps/247), but if the implementation makes it easy to support that in the future, it's a plus.

## Test cases

 - [ ] Reasonable error message if no compatible toolchain is found (for example, trying to compile Java 9 with JDK 8 declared)
 - [ ] If a binary targets Java X and a JDK X is installed, then this version is used to compile
 - [ ] If a binary targets Java X and JDK X+1 and JDK X+2 is installed, JDK X+1 is used


