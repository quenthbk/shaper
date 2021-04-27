#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
java -jar $DIR/../shaper-converter/target/shaper-converter-*-jar-with-dependencies.jar $PWD/$1 $PWD/$2
