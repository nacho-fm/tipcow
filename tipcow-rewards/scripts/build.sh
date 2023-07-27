#!/bin/bash

git pull origin develop

docker image build --label tc-rewards --tag tc-rewards ..
if [[ "$1" == "--all" ]]; then
    ./cleanup.sh
    ./run.sh --build
    #./connect.sh
    watch docker logs tc-rewards
fi
