#!/bin/bash

cd /home/ec2-user/app/target

nohup java -jar ecomApp-0.0.1-SNAPSHOT.jar > /home/ec2-user/app/application.log 2>&1 &