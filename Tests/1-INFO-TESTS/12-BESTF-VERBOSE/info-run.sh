#!/bin/bash

result=$(java P1main BestF 5 0,0 50 verbose)

echo "$result"

result="${result//$'\n'/NEWLINE}"

result="${result// /}"

java -jar $TESTDIR/CheckOutput.jar $result verbose




