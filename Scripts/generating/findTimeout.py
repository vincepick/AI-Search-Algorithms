import subprocess  # for running java commands
import os
import argparse

# Algorithms and their corresponding output file names
algorithms = ["BestF", "AStar", "BestFOpt", "AStarOpt"]

parser = argparse.ArgumentParser()
parser.add_argument("run_dir", type=str, help="Folder for result CSVs")
args = parser.parse_args()

# Directory paths for code base and data
source_dir = "../../src"
data_dir = f"../../Data/{args.run_dir}"
header = "boardsize,coverage,startx,starty,pathcost,executiontime,nodes_explored,path_length,memory_usage\n"

os.makedirs(data_dir, exist_ok=True)

# Compile Java files
subprocess.run(f"javac {source_dir}/*.java", shell=True)
print("Java files compiled!")

# Set starting point and coverage
start_x, start_y = 0, 0
coverage = 50

# Loop over each algorithm and execute the Java program for board sizes 1 to 100
for algo in algorithms:
    csv_file = f"{data_dir}/{algo}.csv"
    
    # Write header if csv_file doesn't already exist
    if not os.path.exists(csv_file):
        with open(csv_file, 'w') as file:
            file.write(header)
    
    timeout_count = 0  # Counter to track consecutive timeouts

    for board_size in range(1, 101):  # Board sizes from 1 to 100
        # Set up the command with the current algorithm and fixed parameters
        command = f"java -cp {source_dir} P1main {algo} {board_size} {start_x},{start_y} {coverage} CSV"
        try:
            # Run the Java program with a timeout (e.g., 30 seconds)
            result = subprocess.run(command, shell=True, check=True, capture_output=True, text=True, timeout=30)
            output = result.stdout.strip()  # Capture standard output

            # Write the full output to the CSV file as a new line
            with open(csv_file, 'a') as file:
                file.write(output + "\n")  # Append the full output as a new line in CSV

            print(f"Run for {algo} completed with board size {board_size}, start ({start_x},{start_y}), and coverage {coverage}%.")
            
            # Reset timeout counter on successful completion
            timeout_count = 0

        except subprocess.TimeoutExpired:
            print(f"Timeout occurred for {algo} with board size {board_size}.")
            timeout_count += 1

            # Stop further tests if two consecutive timeouts occur
            if timeout_count >= 2:
                print(f"Stopping further runs for {algo} due to two consecutive timeouts.")
                break

        except subprocess.CalledProcessError as e:
            print(f"An error occurred for {algo} with board size {board_size}, continuing: {e}")
            continue
