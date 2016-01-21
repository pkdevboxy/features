# Project with multiple JVM components, multiple binaries each, can be imported into the IDE

This story is about tackling the multiple-binaries per component complexity.
This can happen with variants or via direct binary addition by the build author.

## Proposals

- Automatically choose a single "development variant" per component, maybe the "most compatible variant" across the components of the projects
- Merge all variants (union of sources, dependencies etc..)

## Tests

- TBD


## Out of scope

- Non variant binaries, ie. binaries arbitrarily added by build author
- Additional source sets
- Test suites
- External dependencies
- Generated sources
