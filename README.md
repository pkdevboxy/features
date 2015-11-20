[![Build Status](https://travis-ci.org/gradle/features.svg?branch=master)](https://travis-ci.org/gradle/features)

## Introduction

This repository is where features for the [Gradle build system](https://github.com/gradle/gradle) are proposed and refined through a combination of _feature specification_ documents and _working code_. For an early example of this approach, see the contents of the [compile-avoidance](compile-avoidance) directory, and in particular the [Usage](compile-avoidance#Usage) section of its README.

## Related resources

This approach is similar in many ways to the [design docs](https://github.com/gradle/gradle/tree/master/design-docs) that have long been committed in the main Gradle repository. However, feature specs differ from design docs in several important ways:

 1. Feature specs are scoped to a single feature, favoring directory hierarchies for _sub-features_ and _stories_ over combining multiple features and stories into a single document.
 2. Feature specs are written in a fashion designed to be readable by Gradle users; i.e. to clearly communicate the value delivered by the feature.
 3. Feature specs favor the inclusion of working code and a simple _Usage_ section that guides the reader unambigously through using the feature and realizing its benefits.

Upon successful experimentation with this new top-level repository, we'll determine a plan for properly migrating existing design docs so as to avoid any long term overlap between these two approaches.

There are also similarities between the feature specs' "executable" _Usage_ sections and the way Gradle [user guide samples](https://docs.gradle.org/nightly/userguide/sample_list.html) work. One future possibility is a unification of feature specs and samples, but any decisions or plans to this effect will be deferred until after sustained successful experimentation with this repository.

## See also

For more information, see [how to write a feature spec](https://github.com/gradle/flow/wiki/How%20to%20write%20a%20feature%20spec) in the [Gradle Flow wiki](https://github.com/gradle/flow/wiki).
