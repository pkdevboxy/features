# Dependency report for source-set

As a build user, I can request a dependency report for a specific source-set in my project by executing the `report<<source-set>>Dependencies` task.

The report will list all binaries for which the source-set serves as input along with the resolved compile time dependency graph of the targeted source-set against the variant coordinates implied by the binary.
