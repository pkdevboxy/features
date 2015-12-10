# Build author can define task that depends on the compiled classes of a JVM variant

This story introduces a `JvmAssembly` build item that represents the compiled classes and processed resources for a JVM variant. The `java-lang` plugin will configure compilation for components that have both a `JavaSourceSet` and a `JvmAssembly`.

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

- [x] User can create task that depends on the assembly and the jar is *not* built
    - [x] classes are generated
    - [x] resources are processed
- [x] User can specify additional resource directory with preprocessed resources and it will end up in the jar
- [ ] User can specify additional class directory with precompiled classes and:
    - [ ] it will end up in the runtime jar
    - [ ] it will end up in the API jar

## Implementation plan

- [x] Add `JvmAssembly` and implementation
- [x] Use `JvmAssembly` as a backing store for configuration of `JarBinarySpec`: will have `classesDir`, `resourcesDir`, `targetPlatform` and `toolChain` from `JarBinarySpec`
- [x] Change `java-lang` plugin so that it will apply a transform for every `JavaSourceSet` of a component with a `JvmAssembly`
- [x] Change `JvmResourcesPlugin` to create a `ProcessResources` task for every `JvmResourceSet` of a component with a `JvmAssembly`
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

# Should be included

## Implementation plan

- [ ] User guide and samples
    - Good point: we should copy the sample from the feature spec into the codebase
- [ ] Public API to query the `JvmAssembly` for a `JarBinarySpec`
    - We were hoping to keep `JvmAssembly` internal for this feature, but clearly we need a public API that can be
      depended on by a task. The public API can be simply `extends BuildableModelElement` for now.
