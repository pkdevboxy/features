# Dependency report shows resolved dependency graph of binaries

Feature: [#69](https://github.com/gradle/langos/issues/69)

## Summary

As a build user, I can request a dependency report for a project, for a component, for a particular binary or even for a specific source set. The requested report will be displayed and will include the resolved compile time, link time and/or runtime dependency graphs for each relevant binary depending on the targeted element.

## Stories

_The stories below are listed in the order they should be implemented._

 - [ ] [Dependency report for JVM binary](./for-jvm-binary)
 - [ ] [Dependency report for JVM test suite](./for-jvm-test-suite)
 - [ ] [Dependency report for native binary](./for-native-binary)
 - [ ] [Dependency report for component](./for-component)
 - [ ] [Dependency report for project](./for-project)
 - [ ] [Dependency report for source-set](./for-source-set)
 - [ ] [Dependency report for custom ecosystem](./for-custom-ecosystem)
 - [ ] [Dependency report as model element](./as-model-element)

## Implementation notes

- The implementation should keep the rendering of the report separate from the computation of the data structure representing the resolved report so as to allow interested parties (e.g. dotcom) to consume the data directly and without ceremony.
- The implementation should *not* have any knowledge of the Java ecosystem. It may understand the abstract software model.
- Ideally any model element with support for dependencies (`WithDependencies`?) could be the target of a report request.

## Setup

### Publish the required modules to `artifacts`

    ../../../gradlew -p modules uploadArchives
    :uploadArchives
    :collections:uploadArchives
    :core:uploadArchives
    :utils:uploadArchives

    BUILD SUCCESSFUL
