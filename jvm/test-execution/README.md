# JVM Test Execution

Feature: [#102](https://github.com/gradle/langos/issues/102)

## Summary
This feature brings test execution as a first class citizen to the JVM software model. Build authors can declare test suites as components, just like JVM libraries are components. Doing this, test suites benefit from the same advantages as everything done in the software model, with additional capabilities, like defining a test framework as a variant, or runtime environment requirements.

## Motivation
This feature aims at completing the minimal amount of feature set that is required to be able to create JVM components with the JVM software model. Without such a feature, a build author can create components, but doesn't have any mean to test the application, without hardcoding tasks and wiring them by hand in the build lifecycle.

## Related Work
 - Similar test component modeling and execution work has already been done in the Native Software Model for CUnit and Google Test. That work should be generalized where possible.
 - This feature will reuse the existing `Test` tasks as the low-level infrastructure for executing tests, but will make sure that tests are components of their own in the software model.

## Stories
_The stories (and other cards) below are listed according to the order in which they should be implemented._

 - [ ] [Build author can declare a JUnit test suite](with-junit)
 - [ ] [Build author can declare dependencies for test suite sources](with-dependencies)
 - [ ] [Build author can declare a test suite with resources](with-resources)
 - [ ] [Reuse the existing `testSuites` container in the JVM software model](test-suites-container) (`debt`)
 - [ ] Build user can execute tests using `gradle check`
   - up to this point, users have been required to run tests with `<<suitename>>BinaryTest`, e.g. `mySuiteBinaryTest`. With the `testSuites` container in place, it should now be possible to tie test execution into the conventional `check` task lifecycle.
   - Will need to extract a common concept of a 'run' task for all test suite binaries, so that [this rule](https://github.com/gradle/gradle/blob/229d8c7ef9995277e06362675606a0dfb90b9d5e/subprojects/platform-native/src/main/groovy/org/gradle/nativeplatform/test/plugins/NativeBinariesTestPlugin.java#L94-L94) can be pulled up into common infrastructure.
 - [ ] Build author can declare a test suite with a component under test [#113](https://github.com/gradle/langos/issues/113)

## Not in Scope
 - Fix "the Binary problem"
   - All test execution-related tasks currently include `Binary` in the name. This reflects the reality that we "deal in binaries" in the Gradle software model, but in fact the concept of a "binary" makes little sense in the context of executing a test suite. We need to find a better way to capture this concept and then let it bubble up to the level of task naming, so that users have a better experience.
 - Use `run<<Suitename>>` vs `<<suitename>>(Binary|Whatever)Test`
   - This is a cross-cutting naming concern, but the assertion is that it is more natural for a user to type `gradle runMySuite`, or `gradle runIntegTests` than it is to type `gradle mySuiteTest` or `gradle integTestsTest`. Basically, tasks should lead with a verb.
   - `gradle runMySuite` should run all buildable variants of `mySuite`.
   - `gradle mySuite` should assemble, but not run, all buildable variants of `mySuite`.
   - See pull request https://github.com/gradle/features/pull/3
 - User defined variants (eg 'run for these different database types and versions')
 - Targeting multiple platforms
 - TestNG suites
 - Declare a standalone CUnit or Google test suite
 - Additional runtime dependencies (ie only the runtime variants of those libraries used at compile time will be available)
 - Reporting
   - should have some 'generic reporting' already by virtue of being a component
   - need to think about what testing-specific reporting we want to do
   - per https://github.com/gradle/features/commit/2078f2#commitcomment-14750784, `gradle components` should have something useful for the test suite
   - dependencies
 - User can express whether tests are parallelizable
 - Should the test order be preserved?
 - Can we support just running tests that previously failed?
 - Are different failure modes supported?
 - Can (some of) the tests be run in parallel?
 - Should a subset of tests be run using filters/groups/categories?
 - Can we better describe declaratively what we are testing? All main src classes? An application? A web app? (Opens up potentially powerful additional testing plugins even if Gradle might not plan to use this information internally any time soon)
 - Is coverage a completely orthogonal issue? Clover does bi-directional coverage for instance. Could we harness that to run just the tests for code that has recently changed?
