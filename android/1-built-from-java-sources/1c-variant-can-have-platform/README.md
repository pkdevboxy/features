# Android library variant can have an associated Android platform

## Test cases

- [ ] User can resolve Java platforms by name
    - Decide on a reasonable public way to resolve `JavaPlatform` instances.
- [ ] Android platform appears in the `components` report
- [ ] Android library sources are compiled for the Java platform corresponding to the Java platform of a variant
- [ ] Java ToolChain that will be used to build Android variant is displayed in the 'components' report

# Should be included

## Implementation plan

- [ ] Allow a property of type `JavaVersion` to be attached to an Android library variant, and marked up as declaring the target Java version  for the variant. Should have a reasonable default value.
- [ ] Use this to calculate a Java platform for the variant and/or its assembly.

Or:

- [ ] Allow a property of type `JavaPlatform` to be attached to an Android library variant, and marked up as declaring the target Java platform for the variant. Should have a reasonable default value.
- [ ] Add convenience to DSL that converts scalar types or `JavaVersion` to a `JavaPlatform`.
- [ ] Add a public service that can be used by rules to perform the above conversion. 

And:

- [ ] Use a consistent approach for JVM library, JUnit test suite, play application binaries, and custom library binaries.
- [ ] User guide and samples

## Later

- Add a mechanism to declare a managed `Platform` subtype.
- Add a mechanism to infer the `JavaPlatform` for a custom `Platform` subtype.
