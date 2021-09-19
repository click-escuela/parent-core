#!/bin/bash
chmod +x /home/ec2-user/server/parent-core/logs
chmod +x /home/ec2-user/server/parent-core/logs/error.log
chmod +x /home/ec2-user/server/parent-core/logs/debug.log
var="$(cat /home/ec2-user/server/parent-core/parent-service.pid)"
sudo kill $var
sudo rm -rf /home/ec2-user/server/parent-core/parent-service.pid