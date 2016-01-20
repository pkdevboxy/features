# Project with multiple JVM components, a single binary each can be imported into the IDE

## Tests

- IDEA content root and Eclipse project directory should be the project directory of the Gradle project containing the component/binary
- Module/Project should have reasonable Java source settings (JDK, language level, target)
- Eclipse should have basic Java natures and builders
- Sources directories for the binary are correctly mapped
- Classpath should include all binary sources only
- Sources directories for the binary of each component are correctly mapped

## Out of scope

- Multi binaries
- Additional source sets
- Test suites
- External dependencies
- Generated sources
