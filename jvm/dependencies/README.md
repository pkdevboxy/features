# A build author should be able to model sets of dependencies

## Usage

    ../../../gradlew list

## Implementation Goals

We should take the time to make all container names consistent:

container of BinarySpec    => BinaryContainer < ModelMap BinarySpec
container of TestSuiteSpec => TestSuitesContainer < ModelMap TestSuiteSpec
container of ComponentSpec => ComponentContainer < ModelMap ComponentSpec
set of DependencySpec      => DependencySet < Set DependencySpec
