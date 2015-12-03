
 - [ ] Create a debt card to capture the need for a DSL container general enough to include both components {} and testSuites {} (and in the future quality {}, etc)

I should be able to write a rule that says for every test suite spec, the version of JUnit is 4.11

We know we'll need certain refactorings to implement this *correctly*, including:
 - A way to reuse compilation infrastructure already in use by JvmLibrary and co
   - all the stuff that knows how to go from source code to byte code
   - but leave behind the stuff that's library-specific, e.g. api jar
   - for a library, we assemble all the compiled bits
   - for a test suite, we execute the compiled bits
 - Use native-style `testSuites {}` container vs `components {}` container
   - per https://github.com/gradle/features/commit/4afb68#commitcomment-14750921
   - avoids building the test suite classes on `gradle assemble`
   - and with this in place, we can return to using `gradle check` vs `gradle mySuiteTest`

Approach to dealing with these refactorings is to continue with this story as-is, deliver the user-facing value, opportunistically refactor where it's cheap to do so, but factor off a debt card to capture larger refactorings that we *will* perform as part of the larger #102 JVM Test Execution feature.

Additional stories identified

 - test suite-specific dependencies blocks
 - targeting multiple platforms
 - integration with standard lifecycle (check task)
 - reporting
   - should have some 'generic reporting' already by virtue of being a component
   - need to think about what testing-specific reporting we want to do
   - per https://github.com/gradle/features/commit/2078f2#commitcomment-14750784, `gradle components` should have something useful for the test suite
 - user can express whether tests are parallelizable
   - which implies tests actually can be parallelizable
