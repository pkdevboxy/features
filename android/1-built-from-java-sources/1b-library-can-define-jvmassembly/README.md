# Android library can define a `JvmAssembly` and `java-lang` plugin configures the java compilation

`JvmAssembly` becomes a constructible type and `WithJvmAssembly` becomes a public managed type.

## Test cases

- [ ] User can create managed type with read-only `JvmAssembly` property
- [ ] User can create managed type that extends `WithJvmAssembly` and the `JvmAssembly` returned by `WithJvmAssembly#getAssembly()` is automatically constructed
- [ ] User can create managed variant spec type that extends `WithJvmAssembly` and:
    - [ ] all Java source sets are compiled
    - [ ] all JVM resources are processed
- [ ] Fail build when `java-lang` plugin is applied, and component has a `JavaSourceSet` but no `JvmAssembly`
    - From Adam: Please do this generally: fail when a build item has a source set as input that cannot be transformed. Also fix the int test for this that we already have that no longer tests this (the behaviour is broken but the test is not telling us this).
    - See org.gradle.language.base.internal.model.BinarySourceTransformations#createTasksFor
