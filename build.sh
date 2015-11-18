#!/usr/bin/env bash

./gradlew readme

git status -sb

git diff
