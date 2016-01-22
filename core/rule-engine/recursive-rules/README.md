# Apply `RuleSource` rules to all elements with a given type recursively [#147](https://github.com/gradle/langos/issues/147)

This story is about applying rules to all elements in a given scope with a given type.

## Definition of recursively applied rules

A more precise definition of recursive rule application is this:

> Starting from a given element in the model, apply the rule to all descendant elements of that element that can be viewed as the given type.

This means that rules are not applied to descendant nodes that are references to other nodes. Traversal of the model stops when a reference node is encountered.

The rule is only applied to true descendants of the given element, and never to the given element itself (even if it can be viewed as the given type).

## API

```groovy
class MyCompoentRules extends RuleSource {
    @Mutate @Each
    void mutateMyComponents(MyComponentSpec component) {
        // do something with each MyComponentSpec, no matter where it is in the model
    }
}
```

The scope in which `@Each` is applied is the same scope that the `RuleSource` is applied to.

## Tests

* rule is applied to all children of model element that can be viewed as the required type
* rule is not applied to scope element even if it can be viewed as the required type
* rule is not applied to descendant references
* rule is not applied to children of referenced node

## Implementation

`@Each` rules can be applied to elements via an ancestral `ModelListener` when the node is discovered.

Rules applied via `ModelRegistry.applyTo(allLinksTransitive(...), ...)` should be converted to use the new notation where possible.
