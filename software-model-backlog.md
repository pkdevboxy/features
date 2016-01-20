# Debt

## Docs and approachability

- Document how to implement an extension to the JVM software model
- DSL reference
- Tackle complexity of task names
- Improve modelling of binaries for libraries and test suites to make the model clearer 

## Performance

- Performance tests use dependencies on external and local libraries
- Configure only those binaries that are required for the build
- Tackle some hot spots
- Performance tests detect regressions in configuration and build time for JVM software model builds

## Rule implementation

AKA simplify how we write the rules that we need to implement all the other features

- Rule that targets all elements of a given type
- Element has immutable properties defined early
- Can define tasks for a binary
- Can define tasks for a build item
- Component `binaries` and `sources`, and binary `sources` should be immutable when used as input
- More of the model should be managed, dependency declarations in particular
- Fix existing rules to use correct subject
- Lots more debt here, for later milestones

## JVM components (_including_ tests)

- `repositories` declared in model
- Compile using correct JDK
- Dependency report shows compile and runtime dependencies of each binary

## Test 

- Play test support declares a test suite
- Can define multiple test suites
- Need lifecycle task to build test suite binary
- Need lifecycle task to run test suite variant
- Tests should run against runtime classpath of external library
- Performance tests use test suite
- Consistency with native
    - Standalone native test suite
    - Convention to add unit and functional tests
- Run tests using correct JDK
- Can build against multiple Java versions
- Can run on multiple Java versions
- Can have custom variants
- TestNG

## DSL 

- Specify value of type `File` from `String` and other types
- Nested rules for structs and `ModelMap`
- Apply rules to all projects
- Deprecate and remove access to `project` from DSL rules

## Extensibility

- Lots of stuff here. Not a candidate for next milestone.

# Next

- IDE import
- Library publish and resolve
- Java application
- JEE web and ejb application
- Code quality
- Another pass over the DSL
- Groovy lang
- Build init templates
