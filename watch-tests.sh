#!/bin/sh

mkdir -p build

cljs-watch test '{:output-to "build/tests.js"}'

