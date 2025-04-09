#!/bin/bash

JAR_FILE=dummyServer-0.0.1-SNAPSHOT.jar
PID_FILE=dummy-pids.txt

mkdir -p logs
> $PID_FILE  # Clear previous PIDs

start_server() {
  local config=$1
  local log=$2

  echo "Starting server with config: $config"
  java -jar $JAR_FILE --spring.config.location=$config > $log 2>&1 &
  echo $! >> $PID_FILE
}

start_server config/server1.properties logs/server1.log
start_server config/server2.properties logs/server2.log
start_server config/server3.properties logs/server3.log

echo "All servers started. PIDs stored in $PID_FILE"