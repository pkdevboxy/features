# Project with generated source-sets can be imported into the IDE

## Tests

- Generated sources are properly mapped and show up in the IDE
- User can import from production generated sources in production code
- User can import from test generated sources in test code

## Implementation notes

Generalize what's done in the Play software model for generated source-sets so we, build authors, and plugin authors, can register generated source-sets and distinguish them from non-generated ones.
