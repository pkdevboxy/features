# Dependency report for project

As a build author, I can request a dependency report for my entire project by executing the `dependencies` task.

The report will list all components along with all of their binaries and the respective resolved compile time dependency graphs.

## Test cases

  - [ ] Dependency report shows all binaries for all components in the project
  - [ ] Transitive dependencies of duplicate libraries in the report are shown only once
