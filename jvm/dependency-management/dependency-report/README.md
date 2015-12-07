# Dependencies report shows compile time dependency graph of a Java library

## Summary

- Dependency report shows all JVM components for the project, and the resolved compile time graphs for each variant.
- Dependency report implementation should not have any knowledge of the Java ecosystem. It may understand the abstract software model.

## Status

Not in scope.

## Cards

See: [langos/#51](https://github.com/gradle/langos/issues/51)

## Test cases

## Usage

### Publish the required modules to `artifacts`

    ../../../gradlew -p modules uploadArchives
    :uploadArchives
    :collections:uploadArchives
    :core:uploadArchives
    :utils:uploadArchives

    BUILD SUCCESSFUL

### Ask for dependencies report

    ../../../gradlew dependencies
    ?
