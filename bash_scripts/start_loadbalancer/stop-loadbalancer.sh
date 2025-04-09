#!/bin/bash

JAR_NAME=loadbalancer-0.0.1-SNAPSHOT.jar

echo "Looking for Load Balancer process..."

# Find and kill the process running the JAR
PID=$(ps aux | grep $JAR_NAME | grep -v grep | awk '{print $2}')

if [ -z "$PID" ]; then
  echo "No running Load Balancer process found."
else
  echo "Stopping Load Balancer with PID: $PID"
  kill $PID
  echo "Load Balancer stopped."
fi