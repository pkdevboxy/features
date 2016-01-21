# Build author declares toolchain compatible Java platforms

Story:

## Summary

This will allow users to restrict a Java toolchain to a set of specific platforms: if no compatible toolchain is found for a platform, build will fail. In practice, it means that you would be able to compile Java 7 sources *only* with a Java 7 compiler, and fail if the only available toolchain is Java 8. This differs from not specifying target platforms by the fact that in this case, the Java 8 compiler would be used,

## Usage

TODO

## Implementation Goals

TODO

## Test cases

TODO
