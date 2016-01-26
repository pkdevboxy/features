# Can define tasks for a binary [#150](https://github.com/gradle/langos/issues/150)

## Motivation

Currently `@BinaryTasks` rules target the global `tasks` container. Because of this it is impossible to create only the tasks required for a binary.

## Goal

Make `@BinaryTasks` address the `tasks` of the binary instead of the global `TasksContainer` object.

Mention the change in release notes.

## Tests

- TBD

## Implementation notes

- Delegate all the methods on `BuildableModelElement` to `BinaryTasksCollection` to prepare for [tasks for build items](../tasks-for-build-item) story.
- Use `tasks.builtBy()` instead of `binary.builtBy()` etc. to avoid having to mutate the binary in `@BinaryTasks`

## Out of scope

- Won't change `binary.tasks` into a `ModelMap` as part of this story.
