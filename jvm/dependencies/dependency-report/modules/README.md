Publishes a set of components with the following dependency structure to an ivy repository:

```
com.acme:modules:1.0
+--- com.acme:collections:1.0
|    \--- com.acme:core:1.0
|         \--- com.acme:utils:1.0
+--- com.acme:core:1.0 (*)
\--- com.acme:utils:1.0
```
