#!/bin/sh
gcloud config set project $1
./gradlew appengineDeploy
