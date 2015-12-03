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
 - [standalone-junit-test-suite](standalone-junit-test-suite)
