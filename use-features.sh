#!/usr/bin/env bash
#
# This script is run on each push to master by Travis CI at
# https://travis-ci.org/gradle/features
#
# See: .travis.yml

rootDir=$(dirname $0)

$rootDir/gradlew useFeature

let changed_files=$(git status -s $rootDir | wc -l | sed "s/ //g")
if [[ $changed_files > 0 ]]; then
    git status -s
    git diff
    exit 255
fi
