#! /bin/sh

if [ -f service.exe ]; then 
    rm service.exe 
fi
mcs src/*.cs -pkg:wcf -out:service.exe
