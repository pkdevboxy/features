# Toolchains for the Java software model

Feature: [#142](https://github.com/gradle/langos/issues/142)

## Summary

This feature brings toolchain declaration and toolchain selection to the Java software model. So far, users can create libraries, declare that they target a specific Java platform, but the toolchain that is used to compile or execute the application is at best undefined (it will use the version that Gradle is running on, or you can tweak the compiler options).

This feature will be complete when:

  - [ ] toolchains can be declared
  - [ ] toolchains are appropriately selected for compilation
  - [ ] toolchains are appropriately selected for execution

