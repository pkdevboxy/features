#!/usr/bin/env bash
#
# This script is run on each push to master by Travis CI at
# https://travis-ci.org/gradle/features
#
# See: .travis.yml

./gradlew useFeature

git status -sb

git diff
