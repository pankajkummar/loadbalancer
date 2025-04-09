#!/bin/bash

JAR_FILE=loadbalancer-0.0.1-SNAPSHOT.jar
CONFIG_FILE=config/loadbalancer.properties
LOG_FILE=logs/loadbalancer.log

# Create log directory if it doesn't exist
mkdir -p logs

echo "Starting Load Balancer..."
nohup java -jar $JAR_FILE --spring.config.location=$CONFIG_FILE > $LOG_FILE 2>&1 &

echo "Load Balancer started. Logs are available in $LOG_FILE"