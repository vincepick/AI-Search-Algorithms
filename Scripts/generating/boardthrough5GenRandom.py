import subprocess  # for running java commands
import os
import argparse
import random

# Algorithms and their corresponding output file names
algorithms = ["BestF", "AStar", "BestFOpt", "AStarOpt", "Alt"]

# parse command line
parser = argparse.ArgumentParser()
parser.add_argument("run_dir", type=str, help="Folder for result CSVs")
parser.add_argument("num_runs", type=int, help="Number of times to run each algorithm")
args = parser.parse_args()

# Set the number of runs based on the command line input
num_runs = args.num_runs

# Directory paths for code base and data
source_dir = "../../src"
data_dir = f"../../Data/{args.run_dir}"
header = "boardsize,coverage,startx,starty,pathcost,executiontime,nodes_explored,memory_usage\n"

os.makedirs(data_dir, exist_ok=True)

# Compile Java files
subprocess.run(f"javac {source_dir}/*.java", shell=True)
print("Java files compiled :D")

# Loop over each algorithm and execute the Java program, each algorithm having its outputs in a different csv
for algo in algorithms:
    csv_file = f"{data_dir}/{algo}.csv"
    # Write header if csv doesn't already exist
    if not os.path.exists(csv_file):
        with open(csv_file, 'w') as file:
            file.write(header)
    for i in range(4):
        board_size = i+2
        for i in range(num_runs):
            # Generate random values for board size, starting point, and coverage
            start_x = random.randint(0, board_size - 1)
            start_y = random.randint(0, board_size - 1)
            coverage = random.randint(1, 100)  # Coverage as a percentage

            # Set up the command with the current algorithm and random parameters
            command = f"java -cp {source_dir} P1main {algo} {board_size} {start_x},{start_y} {coverage} CSV"
            try:
                # Run the Java program and capture its output
                result = subprocess.run(command, shell=True, check=True, capture_output=True, text=True)
                output = result.stdout.strip()  # Capture standard output

            # no need to stop further tests if two conseuctive breaks, all commands work within this small board range

                # Write the full output to the CSV file as a new line
                with open(csv_file, 'a') as file:
                    file.write(output + "\n")  # append the full output as a new line in CSV

                print(f"Run {i + 1} for {algo} completed with board size {board_size}, start ({start_x},{start_y}), and coverage {coverage}%.")

            except subprocess.CalledProcessError as e:
                print(f"An error occurred in run {i + 1} for {algo}, continuing: {e}")
                continue
