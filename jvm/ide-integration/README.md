# Developer imports JVM software model based project into the IDE

## Summary

This feature brings import of JVM software model projects into IDEs.
Build users can import JVM projects based on the Gradle software model in their IDEs. One final story allows to mix old java plugins and JVM software model components.

## Motivation

`TBD`

## Status

- Spike for IDEA failed
- Spike for Eclipse succeeded
- Stories proposal in progress

## Spikes

### IDEA Spike

An IDEA Module has:

- a root directory
- production sources directories
- test sources directories
- generated sources directories
- scoped dependencies (compile/runtime/test/provided)
- Java language settings

A convenient model, modeled into our existing IdeaModel. Scoped dependencies allows distinction between compilation, runtime, test and “provided” dependencies but does not allows modeling dependencies between components of a single Gradle project inside a single IDEA module.

BUT IDEA kind of abuse TAPI and figure out a lot of things on his own by inspecting the Project and ignoring most of IdeaModel, [including source directories when importing a Gradle project](https://github.com/JetBrains/intellij-community/blob/e154dbccda96d3f19afff5433109cef9f00a61d0/plugins/gradle/src/org/jetbrains/plugins/gradle/service/project/BaseGradleProjectResolverExtension.java#L285-L285).

The spike failed. We will need insight from JetBrains before knowing if import is possible without changes in IDEA itself. As a fallback we also experimented with mapping the JVM Software Model to generated IDEA files and that was success.

When TAPI reports several IDEA Modules for the same directory, IDEA present theses Modules to the user so he can choose the one he wants to use. In other words, we could provide users with different views of the same Gradle project per variant/component etc.. and thus modeling the software model better. If we fallback to generated IDEA files then we would have to see if IDEA behave in the same way.

### Eclipse Spike

An Eclipse Project has:

- a root directory
- sources directories
- a single catch-all classpath
- Java language settings

A quite simple model, modeled into our existing EclipseModel. Eclipse, through Buildship, nicely use TAPI to get the existing EclipseModel.

This model allows users to do things like importing test external dependencies in production code without notifying any problem. Raw Eclipse projects and Maven imported projects behave in the same way, it’s the Eclipse model itself that leaks here.

The spike succeeded. See the [`pm-sm-eclipse-import`](https://github.com/gradle/gradle/commits/pm-sm-eclipse-import) in the main `gradle` repository.


## Stories

Java IDEs are far from having a model rich enough to express the granularity of the JVM Software Model. This proposal is about mapping a Gradle Project to an IDE Project/Module for providing users an experience close to what they’re used to. In other words, all components/binaries of a single Gradle project are mapped into a single IDE Project/Module, sort of “flattened”, not “expanded”.

_The stories (and other cards) below are listed according to the order in which they should be implemented._

- [ ] [Project with multiple JVM components, a single binary each, can be imported into the IDE](./multi-components-single-binary) - *Medium*
- [ ] [Project with multiple JVM components, multiple binaries each, can be imported into the IDE](./multi-components-multi-binaries) - *Small to Medium*
- [ ] [Project with additional source-sets on components and binaries can be imported into the IDE](./additional-source-sets) - *Small*
- [ ] [Project with components/testSuite/binaries level external dependencies can be imported into the IDE](./external-dependencies) - *Large*
- [ ] [Project with multiple standalone test suites, multiple binaries each, can be imported into the IDE](./standalone-test-suites) - *Small*
- [ ] [Project with generated source-sets can be imported into the IDE](./generated-sources) - *Medium*
- [ ] [Multiproject build with with local component dependencies can be imported into the IDE](./local-project-dependencies) - *Medium*
- [ ] [Project with Scala source-set can be imported into the IDE](./scala-source-set) - *Medium*
- [ ] [Play application can be imported into the IDE](./play-application) - *Medium to Large*
- [ ] [Project mixed with old source-sets (eg. old java plugin applied) can be imported into the IDE](./mixed-model)

## General implementation notes

An internal abstraction for querying sources (prod/test/generated) and dependencies (internal/external/project) from the software model should be introduced as part of the first story and then improved on subsequent ones. Something like an “IDE Component Model”. IDE specific TAPI and/or file generation can then build upon that. It would make sense to be implemented as a pair so work can be better split later on.

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
