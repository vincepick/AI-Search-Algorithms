import subprocess  # for running java commands
import os
import argparse

# Algorithms and their corresponding output file names
algorithms = ["BestF", "AStar", "BestFOpt", "AStarOpt"]
num_runs = 100

parser = argparse.ArgumentParser()
parser.add_argument("run_dir", type=str, help="Folder for result CSVs")
args = parser.parse_args()

# Directory paths for code base and data
source_dir = "../src"
data_dir = f"../Data/{args.run_dir}"
header = "boardsize,coverage,pathcost,startx,starty,executiontime,nodes_explored,path_length,memory_usage\n"

os.makedirs(data_dir, exist_ok=True)

# Compile Java files
subprocess.run(f"javac {source_dir}/*.java", shell=True)
print("Java files compiled!")

# Loop over each algorithm and execute the Java program
for algo in algorithms:
    csv_file = f"{data_dir}/{algo}.csv"
    # Write header if csv_file doesn't already exist
    if not os.path.exists(csv_file):
        with open(csv_file, 'w') as file:
            file.write(header)

    for i in range(num_runs):
        # Set up the command with the current algorithm
        command = f"java -cp {source_dir} P1main {algo} {i} 0,0 50 CSV"
        try:
            # Run the Java program and capture its output
            result = subprocess.run(command, shell=True, check=True, timeout=30, capture_output=True, text=True)
            output = result.stdout.strip()  # Capture standard output

            # Write the full output to the CSV file as a new line
            with open(csv_file, 'a') as file:
                file.write(output + "\n")  # Append the full output as a new line in CSV

            print(f"Run {i + 1} for {algo} completed.")
        
        # Handle timeouts or errors
        except subprocess.TimeoutExpired:
            print(f"Run {i + 1} for {algo} timed out, skipping further iterations.")
            break
        except subprocess.CalledProcessError as e:
            print(f"Run {i + 1} for {algo} failed with error: {e}")
