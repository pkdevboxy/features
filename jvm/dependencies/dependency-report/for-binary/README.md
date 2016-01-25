# Dependency report for binary

## Summary

As a build user, I can request a report of all dependencies for a particular binary in my project by executing the associated `report<<binary>>Dependencies` binary task.

## Test cases

  - [ ] Dependency report shows the resolved compile time dependency graph of all inputs to the binary (`BinarySpec#getInputs()`), including:  
    - [ ] direct dependency from component source-set to local library
    - [ ] direct dependency from binary source-set to local library
    - [ ] transitive dependencies from referenced local library
    - [ ] direct dependency from component source-set to external library
    - [ ] direct dependency from binary source-set to external library
    - [ ] transitive dependencies from referenced external library
    - [ ] direct component API dependencies
    - [ ] transitive component API dependencies
    - [ ] correctly resolved dependencies against multiple variant coordinates
  - [ ] Transitive dependencies of duplicate libraries in the dependency graph are shown only once
  - [ ] Dependency report shows the resolved link time dependency graph for native binaries
  - [ ] Dependency report shows the resolved runtime dependency graph for executable binaries including:
    - [ ] JVM test suites
    - [ ] native test suites
    - [ ] JVM applications
    - [ ] native executables
  - [ ] Dependency report shows all _variant coordinates_ that influenced dependency resolution including _target platform_, _flavor_, etc

## Implementation notes

Report should have parity with the legacy report, showing substitutions, rendering in the presence of resolution failures, dealing with cycles and as much implementation as possible should be shared.
