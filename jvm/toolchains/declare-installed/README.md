# Build author declares installed Java toolchain

Story:

## Summary

This will allow users to define the installed JDKs, which Gradle will use to compile libraries and execute tests.

## Usage

TODO

## Implementation Goals

In this story we assume that we are lenient, like the current implementation, we regards to choosing a compatible JDK: strictly speaking, when compiling, we should choose the exact version of the JDK that is required by a binary and fail if no such JDK is declared. However, for practical reasons we might just want to choose the highest compatible version and possibly warn about it (the Java compiler will emit a warning in any case).

 - [ ] Add mechanism to declare Java toolchain resolvers.
 - [ ] Add resolver that uses an specified install dir to locate toolchain.
 - [ ] Resolver probes the version of the installed toolchain (reusing existing logic to do this).
 - [ ] Implementation forks javac
 - [ ] To compile Java source, select the closest compatible toolchain to compile the variant
 - [ ] To run tests, select the closest compatible toolchain to run the variant

 It is a non-goal to support JDK 9 specific flags, that are there to support backwards compilation enforcing the API level (see http://openjdk.java.net/jeps/247), but if the implementation makes it easy to support that in the future, it's a plus.

## Test cases

TODO
