# Toolchains for the Java software model

Feature: [#142](https://github.com/gradle/langos/issues/142)

## Summary

This feature brings toolchain declaration and toolchain selection to the Java software model. So far, users can create libraries, declare that they target a specific Java platform, but the toolchain that is used to compile or execute the application is at best undefined (it will use the version that Gradle is running on, or you can tweak the compiler options).

This feature will be deliverable when:

  - [ ] JDKs or JREs can be declared (independently of the vendor)
  - [ ] JDKs are appropriately selected for compilation
  - [ ] JREs are appropriately selected for execution

and complete when all of the above stories are implemented.

## Related Work

 - Similar modeling has been done for the native software model. It is expected to consolidate both software models as much as possible, that is to say reuse the same infrastructure when possible, extend it, and eventually align both in terms of semantics and DSL.

## Stories
_The stories (and other cards) below are listed according to the order in which they should be implemented._

 - [ ] [Build author declares installed JDKs](declare-installed)
 - [ ] [Java compile tasks select best compatible JDK](select-toolchain)
 - [ ] Test suite can declare Java runtime platforms and executes against all
 - [ ] [Build author declares JDK compatibility level](strict-toolchain)
 - [ ] Discovery of installed JDKs/JREs

 ## Out of scope

 - [ ] Automatic download (provisioning) of Java toolchains

