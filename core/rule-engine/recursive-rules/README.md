# Apply `RuleSource` rules to all elements with a given type recursively [#147](https://github.com/gradle/langos/issues/147)

This story is about applying rules to all elements in a given scope with a given type.

## Definition of recursively applied rules

A more precise definition of recursive rule application is this:

> Starting from a given element in the model, apply the rule to all descendant elements of that element that can be viewed as the given type.

This means that rules are not applied to descendant nodes that are references to other nodes. Traversal of the model stops when a reference node is encountered.

The rule is only applied to true descendants of the given element, and never to the given element itself (even if it can be viewed as the given type).

## API

It is possible to apply a single rule, or a full `RuleSource` to all descendants with a given type by annotating the subject with `@Each`:

```groovy
class MyRules extends RuleSource {
    @Mutate
    void mutateMyComponents(@Each MyComponentSpec component) {
        // do something with each MyComponentSpec, no matter where it is in the model
    }

    @Rules
    void mutateMyBinaries(MyBinaryRules rules, @Each MyBinarySpec bianry) {
        // apply MyBinaryRules to each MyBinarySpec, no matter where it is in the model
    }
}
```

The scope in which `@Each` is applied is the same scope that the enclosing `RuleSource` is applied.

## Tests

* check that these work for both standalone mutator rules and `@Rules` rules
  * rule is applied to all children of model element that can be viewed as the required type
  * rule is not applied to scope element even if it can be viewed as the required type
  * rule is not applied to descendant references
  * rule is not applied to children of referenced node
  * rule cannot be applied to subject addressed via `@Path`
  * fails when `@Each` is applied to an input or the `RuleSource` parameter

## Implementation

`@Each` rules can be applied to elements via an ancestral `ModelListener` when the node is discovered.

Rules applied via `ModelRegistry.applyTo(allLinksTransitive(...), ...)` should be converted to use the new notation where possible.

Also try to convert anything that applies to a broader scope than necessary. If it's easy to fix, fix it, or track any problems causing the fix to be too hard to do right now.

Add user guide docs with a sample.

## Out of scope

- break up existing rules applied to bigger than necessary scopes that need further model/rule engine features to be fixed
- apply rules to or through references
- support for applying the rule only to direct children (as opposed to all descendants) — in the future we can support that via `@Each(directOnly = true)` if we want to
