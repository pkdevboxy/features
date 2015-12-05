# JVM Test Execution

## Summary
This feature brings test execution as a first class citizen to the JVM software model. Build authors can declare test suites as components, just like JVM libraries are components. Doing this, test suites benefit from the same advantages as everything done in the software model, with additional capabilities, like definining a test framework as a variant, or runtime environment requirements.

## Motivation
This feature aims at completing the minimal amount of feature set that is required to be able to create JVM components with the JVM software model. Without such a feature, a build author can create components, but doesn't have any mean to test the application, without hardcoding tasks and wiring them by hand in the build lifecycle.

## Related Work
 - Similar test component modeling and execution work has already been done in the Native Software Model for CUnit and Google Test. That work should be generalized where possible.
 - This feature will reuse the existing `Test` tasks as the low-level infrastructure for executing tests, but will make sure that tests are components of their own in the software model.

## Status
In design.

## Stories
 - [Build author can declare a JUnit test suite](with-junit)
 - [Build author can declare dependencies for test suite sources](with-dependencies)
 - [Build author can declare a test suite with resources](with-resources)

## Debt
Certain refactorings are necessary to implement the first story correctly, but are being deferred until just after it is complete in order to avoid scope creep. These refactorings include:
 - A way to reuse compilation infrastructure already in use by JvmLibrary and co
   - all the stuff that knows how to go from source code to byte code
   - but leave behind the stuff that's library-specific, e.g. api jar
   - for a library, we assemble all the compiled bits
   - for a test suite, we execute the compiled bits
 - Use native-style `testSuites {}` container vs `components {}` container
   - per https://github.com/gradle/features/commit/4afb68#commitcomment-14750921
   - avoids building the test suite classes on `gradle assemble`
   - and with this in place, we can return to using `gradle check` vs `gradle mySuiteTest`

We'll likely fork off dedicated debt card(s) for the above when the first story is complete, but tracking them here for now.

## Not in Scope
 - Targeting multiple platforms
 - Integration with standard lifecycle (check task)
 - Reporting
   - should have some 'generic reporting' already by virtue of being a component
   - need to think about what testing-specific reporting we want to do
   - per https://github.com/gradle/features/commit/2078f2#commitcomment-14750784, `gradle components` should have something useful for the test suite
 - User can express whether tests are parallelizable
 - Should the test order be preserved?
 - Can we support just running tests that previously failed?
 - Are different failure modes supported?
 - Can (some of) the tests be run in parallel?
 - Should a subset of tests be run using filters/groups/categories?
 - Can we better describe declaratively what we are testing? All main src classes? An application? A web app? (Opens up potentially powerful additional testing plugins even if Gradle might not plan to use this information internally any time soon)
 - Is coverage a completely orthogonal issue? Clover does bi-directional coverage for instance. Could we harness that to run just the tests for code that has recently changed?
