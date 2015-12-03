# Allow tasks to depend on the compiled classes of a JVM variant

## Usage

### Run task that depends on compiled classes

    $ ../../../gradlew clean printClassesForMainJar
    :compileMainJarMainJava
    :printClassesForMainJar
    myorg
    Main.class

    BUILD SUCCESSFUL

Notice no jar was built.

## Test cases

- User can create task that depends on the assembly and the jar is *not* built
    - classes are generated
    - resources are processed
- User can specify additional resource directory with preprocessed resources and it will end up in the jar
- User can specify additional class directory with precompiled classes and:
    - it will end up in the runtime jar
    - it will end up in the API jar

## TODO

- Replace usages of `JvmBinarySpec#getClassDir` by `JvmAssembly#getClassDirectories`
- Replace usages of `JvmBinarySpec#getResourceDir` by `JvmAssembly#getResourceDirectories`
- Make `scala-lang` take advantage of `JvmAssembly`
- Make `JvmResourcesPlugin` aware of `JvmAssembly`
- Make `JvmAssembly` public and constructible so it can be attached to the model wherever it makes sense
- Consider updating the components report
