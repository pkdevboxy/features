Publishes a set of libraries to a local ivy repository so they can be used for deterministic testing of the dependency report feature.

The published libraries have the following dependency structure:

```
com.acme:modules:1.0
+--- com.acme:collections:1.0
|    \--- com.acme:core:1.0
|         \--- com.acme:utils:1.0
+--- com.acme:core:1.0 (*)
\--- com.acme:utils:1.0
```
