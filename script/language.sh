#!/bin/bash

NAME=$1

if [ $(find . -name Makefile) ]
then
    docker build -t $NAME -f /app/c/Dockerfile.standalone .
    docker run $NAME
elif [ $(find . -name *.bf) ]
then
    docker build -t $NAME -f /app/befunge/Dockerfile.standalone .
    docker run $NAME
elif  [ $(find . -name pom.xml) ]
then
    docker build -t $NAME -f /app/java/Dockerfile.standalone .
    docker run $NAME
elif  [ $(find . -name *.js) ]
then
    docker build -t $NAME -f /app/javascript/Dockerfile.standalone .
    docker run $NAME
elif [ $(find . -name requirements.txt) ]
then
    docker build -t $NAME -f /app/python/Dockerfile.standalone .
    docker run $NAME
else
    echo build fail
    exit 84
fi