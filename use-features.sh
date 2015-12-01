#!/usr/bin/env bash
#
# This script is run on each push to master by Travis CI at
# https://travis-ci.org/gradle/features
#
# See: .travis.yml

./gradlew useFeature

let changed_files=$(git status -s | wc -l | sed "s/ //g")
if [[ $changed_files > 0 ]]; then
    git status -s
    git diff
    exit 255
fi
