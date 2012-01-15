#!/bin/sh

mkdir -p build

cljs-watch src '{:output-to "build/main.js"}'

