# Rule engine

TBD: What is the rule engine? What are its boundaries? Which user roles interact with it? How do they interact with it?

## Motivation

TBD: Why did we build the rule engine in the first place? What problem(s) does it solve?

## Work

See the subdirectories here and the [`feature:rule-engine` label](https://huboard.com/gradle/langos#/?label=%5B%22feature%3Arule-engine%22%5D) in HuBoard.

# Changes for Gradle 2.12

The current implementation of the rule engine does not have all the features, and requires us to implement plugins via workarounds. This feature aims at implementing some of that missing functionality, and cleaning up existing code where possible.

## Motivation

Currently much of the rules our own plugins declare have problems. In some cases the whole thing works only because of coincidence, in other cases the problem is more about bloated, hard to read code that relies heavily on internal APIs. There's already some layers of workarounds accumulated in our core plugins, and new work requires more and more of these. We've seen in several stories how these workarounds have slowed down the implementation of even simple stories.

Therefore the goal here is as much to make the life of our plugin author users' lives easier as to speed up our own development. Another goal is to enable us to write the right rules, providing good examples for plugin authors looking at our code for examples.

### Problems to fix

Typical problems this feature tries to address:

- **rules targeting a broader subject than necessary** -- instead of applying a rule to each individual thing, we apply a rule to the thing container. This makes the resulting model much more interdependent, and leads to (among other things) the dreaded ordering problem. The cause of this is that we don't have fine-grained enough control over rule targeting.

- **rules target the wrong subject** -- we have rules that target the global task container and use a binary as input only to modify the binary in the end. This works because we don't enforce immutability on binaries properly, but it again messes up the dependencies between rules. The problem can be fixed by creating tasks through the individual task containers of each buildable, and only collecting them in the global container.

## Stories

Work to be mapped to a subdirectory and card:

- Element has immutable properties defined early
- Can define tasks for a binary
- Can define tasks for a build item
- Component binaries and sources, and binary sources should be immutable when used as input
- More of the model should be managed, dependency declarations in particular
- Fix existing rules to use correct subject
