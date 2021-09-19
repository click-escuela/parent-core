#!/usr/bin/env bash
cd /home/ec2-user/server/parent-core

sudo rm -rf /home/ec2-user/server/parent-core/parent-service.pid

echo "eliminando archivo"

sudo java -jar -Dlogging.file.name=/home/ec2-user/server/parent-core/debug.log  parent-core-0.0.1-SNAPSHOT.jar > /dev/null 2> /dev/null < /dev/null & echo $! > parent-service.pid
