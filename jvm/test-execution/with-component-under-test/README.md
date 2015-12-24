# Build author can declare a component under test

Story: [#113](https://github.com/gradle/langos/issues/113)

## Summary
As a build author, I can declare a component under test for a test suite, making it effectively testable. In other words, this story enabled test driven development.

## Usage

### Attempt to execute tests and succeed

TODO

### Remove component under test from build script

TODO

### Attempt to execute tests and fail to compile

TODO

### Clean up

TODO

----

## Test cases

 - [ ] Test suite can use an API class defined in the component under test
 - [ ] Test suite can use a non-API class defined in the component under test
 - [ ] Test suite is executed against the runtime classes of the component under test
 - [ ] Test suite can see (use) resources of the component under test
 - [ ] API jar and runtime jar of the component under test are not built when test suite is executed
 - [ ] Updating sources of the component under test should trigger execution of the tests
 - [ ] Tests should not be executed if sources of component under test haven't changed
