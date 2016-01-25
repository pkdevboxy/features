# Dependency report data as model element

As I build author, I can create rules and tasks that leverage the data in the dependency report.

## Test cases

## Implementation notes

Firstly, it should be reusing the resolved graph that the reporting already uses. This is the model for the output of a dependency resolution calculation.

Secondly, we will need some way to not calculate this graph when it is not requested, because of performance considerations. Currently, everything attached to an element is calculated when anything about the element is requested.
