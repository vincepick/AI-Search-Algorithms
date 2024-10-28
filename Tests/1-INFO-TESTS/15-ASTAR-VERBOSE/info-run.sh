#!/bin/bash

result=$(java P1main AStar 5 0,0 25 verbose)

echo "$result"

result="${result//$'\n'/NEWLINE}"

result="${result// /}"

java -jar $TESTDIR/CheckOutput.jar $result verbose



