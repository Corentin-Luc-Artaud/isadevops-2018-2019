#! /bin/bash

hyperplanning_image=rockduatra/hyperplanning
# set source
source build.scripts/buildlib.sh

# build mono hyperplanning
# build docker hyperplanning
echo "---- building all external services mono + docker image ----"
build-all-complete-mono build.config/build-mono

# start service hyperplanning
echo "---- starting hyperplanning ----"
docker run -d --rm --name teami-hyperplanning -p 9298:9090 $hyperplanning_image

# set env var
export hyperplanning_host=localhost
export hyperplanning_port=9298
# build maven 
echo "---- build all maven project ----"
build-all-maven build.config/build-maven
# stop container 
echo "---- stoping hyperplanning ----"
docker stop teami-hyperplanning
# build docker
echo "---- building docker for polydiploma ----"
docker-build-all build.config/build-docker
