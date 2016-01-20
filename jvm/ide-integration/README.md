# Developer imports JVM software model based project into the IDE

## Summary

## Motivation

## Status

Stories proposal in progress.

## Related Work

### Eclipse Model

An Eclipse Project has:

- a root directory
- sources directories
- a single catch-all classpath
- Java language settings

A quite simple model, modelled into our existing EclipseModel. Eclipse, through Buildship, nicely use TAPI to get the existing EclipseModel.

This model allows users to do things like importing test external dependencies in production code without notifying any problem. Raw Eclipse projects and Maven imported projects behave in the same way, it’s the Eclipse model itself that leaks here.

The spike succeeded. See the `pm-sm-eclipse-import` in the main `gradle` repository.

### IDEA Model

An IDEA Module has:

- a root directory
- production sources directories
- test sources directories
- generated sources directories
- scoped dependencies (compile/runtime/test/provided)
- Java language settings

A convenient model, modelled into our existing IdeaModel. Scoped dependencies allows distinction between compilation, runtime, test and “provided” dependencies but does not allows modelling dependencies between components of a single Gradle project inside a single IDEA module.

BUT IDEA kind of abuse TAPI and figure out a lot of things on his own by inspecting the Project and ignoring most of IdeaModel, including source directories when importing a Gradle project.

The spike failed. We will need insight from JetBrains before knowing if import is possible without changes in IDEA itself. One fallback would be to map the JVM Software Model to generated IDEA files.

## Stories

Java IDEs are far from having a model rich enough to express the granularity of the JVM Software Model. This proposal is about mapping a Gradle Project to an IDE Project/Module for providing users an experience close to what they’re used to. In other words, all components/binaries of a single Gradle project are mapped into a single IDE Project/Module, sort of “flattened”, not “expanded”.

_The stories (and other cards) below are listed according to the order in which they should be implemented._

- [ ] [Project with single JVM component and a single binary can be imported into the IDE](./single-component-single-binary)
- [ ] [Project with multiple JVM components, a single binary each, can be imported into the IDE](./multi-components-single-binary)
- [ ] [Project with multiple JVM components, multiple binaries each, can be imported into the IDE](./multi-components-multi-binaries)
- [ ] [Project with additional source-sets on components and binaries can be imported into the IDE](./additional-source-sets)
- [ ] [Project with components/testSuite/binaries level external dependencies can be imported into the IDE](./external-dependencies)
- [ ] [Project with multiple standalone test suites, multiple binaries each, can be imported into the IDE](./standalone-test-suites)
- [ ] [Project with multiple standalone test suites, multiple binaries each, testing components can be imported into the IDE](./components-under-test)
- [ ] [Project with generated source-sets can be imported into the IDE](./generated-sources)
- [ ] [Multiproject build with with local component dependencies can be imported into the IDE](./local-project-dependencies)
- [ ] [Project mixed with old source-sets (eg. old java plugin applied) can be imported into the IDE](./mixed-model)

## General implementation notes

An internal abstraction for querying sources (prod/test/generated) and dependencies (internal/external/project) from the software model should be introduced. Something like an “IDE Component Model”. IDE specific TAPI and/or file generation can then build upon that.

Whether we choose to start with a flat or rich mapping, this abstraction should be built with rich mapping in mind.

For the TAPI case, that is “ide import”, a new ToolingModelBuilder per supported IDE should be introduced. These would be chained with the ToolingModelBuilder for the legacy model. How this will fit with projects sharing legacy and software model it is still to be defined.

At some point we could also expose that generic “IDE Component Model” directly on TAPI so other IDEs can benefit from it.

On a more prospective angle, Adam suggested we should aim at implementing the mapping in an extensible manner. Meaning build authors would be able to influence the mapping to IDE models. Oh and another thing we should aim at at some point is to build all tooling api models using the model/rules to make this process less eager.

## Peculiarities

### Project root(s)

When given modules with overlapping content roots IDEA will only display one of them.
https://www.jetbrains.com/idea/help/content-root.html
https://www.jetbrains.com/idea/help/configuring-content-roots.html

Eclipse forbid overlapping Projects in the same Workspace.
If we map a Gradle project to several EclipseProjects we’ll have to find a way to circumvent this. 
Some very old content about just that:
http://www.eclipse.org/eclipse/development/inflexible-projects-problem.html
http://www.eclipse.org/eclipse/development/flexible-projects-proposal.html
https://wiki.eclipse.org/CDT:Flexible_Project_Structure
One possibility we can investigate is to create synthetic project roots under a chosen directory and use linked resources pointing to the actual source directories. Linked resources should use relative paths but this may require handling Eclipse Path variable facility. Eclipse will not fail on linked resources using absolute paths but show the user a warning about it in the project properties dialog.

### Unique names, scopes mapping etc..

We should leverage/reuse what's already done for the legacy model mapping.
We can also use the “display names” from the component model.

## Debt
