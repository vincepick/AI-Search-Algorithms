import subprocess  # for running java commands
import os
import argparse
import random

# algorithms
algorithms = ["BestF", "AStar", "BestFOpt", "AStarOpt", "Alt"]

# parse command line
parser = argparse.ArgumentParser()
parser.add_argument("run_dir", type=str)
parser.add_argument("num_runs", type=int)
args = parser.parse_args()

# Directory paths for code base and data
source_dir = "../../src"
data_dir = f"../../Data/{args.run_dir}"
header = "boardsize,coverage,startx,starty,pathcost,executiontime,nodes_explored,memory_usage\n"

os.makedirs(data_dir, exist_ok=True)

# Compile Java files 
subprocess.run(f"javac {source_dir}/*.java", shell=True)
print("Java files compiled :D")

# fixed coverage and board size
coverage = 100
board_size = 5

# looping over each algorithm and execute program for board sizes 1 to 10
for algo in algorithms:
    csv_file = f"{data_dir}/{algo}.csv"
    
    if not os.path.exists(csv_file):
        with open(csv_file, 'w') as file:
            file.write(header)


    timeout_count = 0  # Counter to track consecutive timeouts

    for start_x in range(0, 6):  # Board sizes from 1 to 5
        for start_y in range(0, 6):  # Board sizes from 1 to 5
            for run in range(args.num_runs):

              
                command = f"java -cp {source_dir} P1main {algo} {board_size} {start_x},{start_y} {coverage} CSV"
                try:
                    # Run the Java program with a timeout 30 seconds
                    result = subprocess.run(command, shell=True, check=True, capture_output=True, text=True, timeout=30)
                    output = result.stdout.strip()  # Capture standard output

                    with open(csv_file, 'a') as file:
                        file.write(output + "\n")  # Append the full output as a new line in CSV

                    print(f"Run {run + 1} for {algo} completed with board size {board_size}, start ({start_x},{start_y}), and coverage {coverage}%.")

                    # Reset timeout counter on successful completion
                    timeout_count = 0

                except subprocess.TimeoutExpired:
                    print(f"Timeout occurred for {algo} with board size {board_size}.")
                    timeout_count += 1

                # stop further tests if two consecutive timeouts
                    if timeout_count >= 2:
                        print(f"Stopping further runs for {algo} due to two consecutive timeouts.")
                        break

                except subprocess.CalledProcessError as e:
                    print(f"An error occurred for {algo} with board size {board_size}, continuing: {e}")
                    continue

        # break outer loop if necessary
        if timeout_count >= 2:
            break
