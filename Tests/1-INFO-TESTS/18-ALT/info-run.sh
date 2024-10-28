#!/bin/bash

result=$(java P1main Alt 5 0,0 50)

echo "$result"

result="${result//$'\n'/NEWLINE}"

result="${result// /}"

java -jar $TESTDIR/CheckOutput.jar $result



