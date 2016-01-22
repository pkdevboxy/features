# Rule engine

## Summary

TBD: What is the rule engine? What are its boundaries? Which user roles interact with it? How do they interact with it?

## Motivation

TBD: Why did we build the rule engine in the first place? What problem(s) does it solve?

## Feature Label

[`feature:rule-engine`](https://huboard.com/gradle/langos#/?label=%5B%22feature%3Arule-engine%22%5D)

----

# Changes for Gradle 2.12

## Summary

The current implementation of the rule engine does is missing certain key functionality, and this requires us to implement plugins via workarounds. This set of changes aims to implementing some of that missing functionality, and to clean up existing code where possible.

## Motivation

Currently many of the rules our own plugins declare have problems. In some cases the whole thing works only by coincidence, in other cases the problem is more about bloated, hard to read code that relies heavily on internal APIs. There's already some layers of workarounds accumulated in our core plugins, and new work requires more and more of these. We've seen in several times how these workarounds have slowed down the implementation of even simple stories.

Therefore the goal here is as much to make the life of our plugin author users' lives easier as it is to speed up our own development. Another goal is to enable us to write the right rules, providing good examples for plugin authors looking at our code for examples.

### Problems to fix

Typical problems these changes try to address:

- **rules targeting a broader subject than necessary** -- instead of applying a rule to each individual "thing", we apply a rule to the thing container. This makes the resulting model much more interdependent, and leads to (among other things) the dreaded ordering problem. The cause of this is that we don't have fine-grained enough control over rule targeting.

- **rules targeting the wrong subject** -- we have rules that target the global task container and use a binary as input only to modify the binary in the end. This works because we don't enforce immutability on binaries properly, but it again messes up the dependencies between rules. The problem can be fixed by creating tasks through the individual task containers of each buildable, and only collecting them in the global container.

## Work

See cards labeled `feature:rule-engine` and the subdirectories here. The following work has yet to be mapped to a card and subdirectory:

- Element has immutable properties defined early
- Can define tasks for a binary
- Can define tasks for a build item
- Component binaries and sources, and binary sources should be immutable when used as input
- More of the model should be managed, dependency declarations in particular
- Fix existing rules to use correct subject
