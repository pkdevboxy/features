# Build author declares installed JDKs

Story: https://github.com/gradle/langos/issues/153

## Summary

This will allow users to define the installed Java toolchains. We should **not** reuse the `toolChains` container that exists in the native ecosystem because it mixes the definition of tools and their resolution. Instead, we're introducing a `jdks` container that will let the user define installed JDKs. An independent resolver will use this information later. This story will setup the initial infrastructure required to support multiple JDKs in the Java software model. The user will be able to declare JDKs, but Gradle will not yet automatically pick them. Instead, the user would have to choose one explicitly in a rule to use it.

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
            tool chain: JDK 8 (1.8)
            classes dir: build/classes/myLib/java6Jar
            resources dir: build/resources/myLib/java6Jar
            API Jar file: build/jars/myLib/java6Jar/api/myLib.jar
            Jar file: build/jars/myLib/java6Jar/myLib.jar
        Jar 'myLib:java7Jar'
            build using task: :myLibJava7Jar
            target platform: Java SE 7
            tool chain: JDK 8 (1.8)
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
            tool chain: JDK 8 (1.8)
            classes dir: build/classes/myTest/myLibJava6JarBinary
            resources dir: build/resources/myTest/myLibJava6JarBinary
        Test suite 'myTest:myLibJava7JarBinary'
            build using task: :myTestMyLibJava7JarBinary
            run using task: :myTestMyLibJava7JarBinaryTest
            target platform: Java SE 7
            JUnit version: 4.12
            component under test: JVM library 'myLib'
            binary under test: Jar 'myLib:java7Jar'
            tool chain: JDK 8 (1.8)
            classes dir: build/classes/myTest/myLibJava7JarBinary
            resources dir: build/resources/myTest/myLibJava7JarBinary

    Note: currently not all plugins register their components, so some components may not be visible here.

    BUILD SUCCESSFUL


## Implementation Goals

 - [ ] Add mechanism to declare location of JDKs
 - [ ] Add resolver that uses an specified install dir to locate toolchain.
 - [ ] Resolver probes the version of the installed toolchain (reusing existing logic to do this). This **must** be done only once per build (not per project per build). Introduce caching.
 - [ ] If possible try to determine the name of the toolchain from the vendor (OpenJDK vs OracleJDK vs IBM JDK vs ...)

## Test cases

 - Can avoid a `jdks` section altogether
 - Reasonable error message if the path to the JDK is not valid
 - Reasonable error message if the path is not a path to a JDK
 - Reasonable error message if the JDK cannot be probed
 - Cannot declare the same JDK twice (deduplicated by resolved absolute path)
 - JDK versions are probed at most once per build

