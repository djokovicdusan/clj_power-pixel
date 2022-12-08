#!/usr/bin/env bash

mkdir  native
cp ./*.dylib native;
cp ./*.so native;
cp ./*.dll native;

jar -cMf opencv-native-2413.jar native
rm -rf native;
