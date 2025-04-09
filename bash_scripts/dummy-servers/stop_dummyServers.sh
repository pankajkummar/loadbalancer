#!/bin/bash

PID_FILE=dummy-pids.txt

if [ -f "$PID_FILE" ]; then
  while read pid; do
    echo "Stopping process $pid"
    kill $pid
  done < "$PID_FILE"
  rm "$PID_FILE"
  echo "All servers stopped."
else
  echo "No PID file found."
fi