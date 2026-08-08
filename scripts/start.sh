#!/bin/bash

cd /home/ec2-user/app

nohup java -jar *.jar > application.log 2>&1 &