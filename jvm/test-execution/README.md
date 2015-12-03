# JVM Test Execution

## Summary

This feature brings test execution as a first class citizen to the Java software model. Build authors can declare test suites as components, just like a JVM library is a component. Doing this, test suites benefit from the same advantages as everything done in the software model, with additional capabilities, like definining a test framework as a variant, or runtime environment requirements.

## Motivation

This feature aims at completing the minimal amount of feature set that is required to be able to create JVM components with the Java software model: without such a feature, a build author can create components, but doesn't have any mean to test the application, without hardcoding tasks and wiring them by hand in the build lifecycle.

## Status

In design.

## Terminology

## History

## Related work

This feature will reuse the existing `Test` tasks as the low-level infrastructure for executing tests, but will make sure that tests are components of their own in the software model.

In the future, should we capture more information that test runners might need, e.g.
* Should the test order be preserved?
* Can we support just running tests that previously failed?
* Are different failure modes supported?
* Can (some of) the tests be run in parallel?
* Should a subset of tests be run using filters/groups/categories?
* Can we better describe declaratively what we are testing? All main src classes? An application? A web app? (Opens up potentially powerful additional testing plugins even if Gradle might not plan to use this information internally any time soon)
* Is coverage a completely orthogonal issue? Clover does bi-directional coverage for instance. Could we harness that to run just the tests for code that has recently changed?
