# Android library variant is built from Java sources

## User stories

- [ ] Rule can target 'sources' container of all components of a particular type
- [x] Android library can include a `JavaSourceSet` and it appears in the 'components' report
- [ ] Jvm library has a `ClassesDirectory` and 'java-lang' plugin configures the java compilation
- [ ] Play application has a `ClassesDirectory` and 'scala-lang' plugin configures the scala compilation
- [ ] Android library can define a `ClassesDirectory` and 'java-lang' plugin configures the java compilation
- [ ] Android library variant has an associated Android platform, which appears in the 'components' report
- [ ] Android library sources are compiled for the Java platform corresponding to the Java platform of a variant
- [ ] Java ToolChain that will be used to build Android variant is displayed in the 'components' report

## Related work

### Prerequisite features

### Dependent features


## Executable spec

### Step 1. Run the 'components' report

### Step 2. Compile Android library classes for Java 6

    $ ../../gradlew myAndroidJava6

    BUILD SUCCESSFUL

### Step 3. Compile Android library classes for Java 8

    $ ../../gradlew myAndroidJava8

    BUILD SUCCESSFUL

### Step 4. Report class file versions to see the difference
