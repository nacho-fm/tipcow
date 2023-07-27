#!/bin/bash

docker run --network tipcow -p 5000:5000 --name tc-rewards -d tc-rewards
