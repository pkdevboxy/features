# Build author can define task that depends on the compiled classes of a JVM variant

This story introduces a `JvmAssembly` build item that represents the compiled classes and processed resources for a Jvm variant. The `java-lang` plugin will configure compilation for components that have both a `JavaSourceSet` and a `JvmAssembly`.

This change will have a number of benefits:
- The `JvmResourcesPlugin` will process resources for any variant with a `JvmResourceSet` and a `JvmAssembly`
- The `JavaLanguagePlugin` will compile sources for any variant with a `JavaSourceSet` and a `JvmAssembly`
- Additional processed resources and compiled sources can be included in the `Jar` produced by the `JvmComponentPlugin`
- A task can depend on compiled sources and processed resources, without requiring that the `Jar` file be produced.

## Usage

### Run task that depends on compiled classes and processed resources

    $ ../../../gradlew clean printClassesForMainJar
    :compileMainJarMainJava
    :printClassesForMainJar
    myorg
    Main.class
    answer.txt

    BUILD SUCCESSFUL

Notice no jar was built.

## Test cases

- [ ] User can create task that depends on the assembly and the jar is *not* built
    - [ ] classes are generated
    - [ ] resources are processed
- [ ] User can specify additional resource directory with preprocessed resources and it will end up in the jar
- [ ] User can specify additional class directory with precompiled classes and:
    - [ ] it will end up in the runtime jar
    - [ ] it will end up in the API jar

## Implementation plan

- [ ] Add `JvmAssembly` and implementation
- [ ] Use `JvmAssembly` as a backing store for configuration of `JarBinarySpec`: will have `classesDir`, `resourcesDir`, `targetPlatform` and `toolChain` from `JarBinarySpec`
- [ ] Change `java-lang` plugin so that it will apply a transform for every `JavaSourceSet` of a component with a `JvmAssembly`
- [ ] Change `JvmResourcesPlugin` to create a `ProcessResources` task for every `JvmResourceSet` of a component with a `JvmAssembly`
- [ ] Replace usages of `JvmBinarySpec#getClassDir` by `JvmAssembly#getClassDirectories`
- [ ] Replace usages of `JvmBinarySpec#getResourceDir` by `JvmAssembly#getResourceDirectories`
- [ ] Consider updating the components report to display `JvmAssembly`

### Breaking changes and renames

- [ ] Rename `JarBinarySpec` to `JvmVariantSpec` (consider removing the `Spec`, too)
- [ ] Remove or deprecate `JarBinarySpec#getClassDir` and `JarBinarySpec#getResourceDir`
- [ ] Rename `LibaryBinaryIdentifier` to `VariantIdentifier` (or similar)

## Out of scope (later stories)

- Public construction of `JvmAssembly`: this will be an internal implementation.
    - A later story will allow this to be used in the Android plugin
- Make `scala-lang` take advantage of `JvmAssembly`
