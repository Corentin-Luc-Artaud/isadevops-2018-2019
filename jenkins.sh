#! /bin/bash

if [[ ! $1 ]]; then
	echo $1
	echo "need user in form <uid:gid> to start jenkins with the right on files"
	echo "start jenkins on port 8095"
	exit 0
fi

docker run --user $1 --rm -d --name jenkins-teami -p 8095:8080 -v $(pwd)/jenkins:/var/jenkins_home jenkinsci/blueocean

