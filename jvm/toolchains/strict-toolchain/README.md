# Build author declares JDK compatibility level

Story: https://github.com/gradle/langos/issues/156

## Summary

This will allow users to restrict an installed JDK to a set of specific platforms: if no compatible toolchain is found for a platform, build will fail. If several compatible toolchains are found the "closest" one is selected. If several equally "closest" toolchains are found, the first one is selected. In practice, it means that you would be able to enforce compiling Java 7 sources with a Java 7 compiler *only*, and fail if the only available toolchain is Java 8 only. This differs from not specifying target platforms of toolchains by the fact that in this case, the Java 8 compiler would be used.

## Usage

TODO

## Implementation Goals

TODO

## Test cases

TODO
