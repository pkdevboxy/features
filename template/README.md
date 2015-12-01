BOF

    $ ../gradlew hello
    :template:hello
    Hello, World!

    BUILD SUCCESSFUL

    Total time: 0.709 secs

MOF

    $ ../gradlew bogus

    FAILURE: Build failed with an exception.

    * What went wrong:
    Task 'bogus' not found in project ':template'.

    * Try:
    Run gradlew tasks to get a list of available tasks. Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output.

    BUILD FAILED

    Total time: 0.693 secs

MOF

    # gradle noop
    [...]

    BUILD SUCCESSFUL

    Total time: 0.0 secs

EOF
