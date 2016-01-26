# Dependency report for native binary

## Summary

As a build user, I can request a report of all dependencies for a particular native binary in my project by executing the associated `report<<binary>>Dependencies` binary task.

## Test cases

  - [ ] Dependency report shows the resolved link time files for native binaries
  - [ ] Dependency report shows the resolved runtime files for native binaries
  - [ ] Dependency report shows all _variant coordinates_ that influenced dependency resolution including _flavor_, _target platform_ and _build type_
