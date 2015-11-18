#!/usr/bin/env bash

./gradlew --no-daemon readme

git status -sb

git diff
