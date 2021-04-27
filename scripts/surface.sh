#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
java -jar $DIR/../shaper-surface/target/shaper-surface-*-jar-with-dependencies.jar $PWD/$1
