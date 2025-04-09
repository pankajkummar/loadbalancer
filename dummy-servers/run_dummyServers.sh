#!/bin/bash

JAR_FILE=dummyServer-0.0.1-SNAPSHOT.jar

echo "Starting Server 1 on port 8081"
java -jar $JAR_FILE --spring.config.location=config/server1.properties > logs/server1.log 2>&1 &

echo "Starting Server 2 on port 8082"
java -jar $JAR_FILE --spring.config.location=config/server2.properties > logs/server2.log 2>&1 &

echo "Starting Server 3 on port 8083"
java -jar $JAR_FILE --spring.config.location=config/server3.properties > logs/server3.log 2>&1 &

echo "All servers started."