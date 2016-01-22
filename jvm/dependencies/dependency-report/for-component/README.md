# Dependency report for component

## Summary

As a build user, I can request a dependency report for a particular component in my project by executing the `report<<component>>Dependencies` task.

The report will list all binaries of the targeted component along with their resolved compile time dependency graph. In other words, the dependency report for a component is the aggregation of the dependency reports for all of its binaries.

## Test cases

  - [ ] Dependency report shows all binaries for the targeted component
  - [ ] Transitive dependencies of duplicate libraries in the report are shown only once
