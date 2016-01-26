# Dependency report for JVM test suite binary

## Summary

As a build user, I can request a report of all dependencies for a particular JVM test suite binary in my project by executing the associated `report<<binary>>Dependencies` binary task.

## Test cases

  - [ ] Dependency report shows the resolved *runtime* dependency graph for the binary
  - [ ] Transitive dependencies of duplicate libraries in the dependency graph are shown only once
  - [ ] Dependency report shows all _variant coordinates_ that influenced dependency resolution including _target platform_

## Implementation notes

Same as the ones [for JVM binary](../for-jvm-binary#implementation-notes).

Additionally consider if we can generalize the reporting of `runtime dependency graph` by introducing the concept of _runtime binaries_ to also accommodate the upcoming _JvmApplication_.
