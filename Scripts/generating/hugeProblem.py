import subprocess
import csv
import os

# List of algorithms to test
algorithms = ["AStar", "BestF", "Alt", "AStarOpt", "BestFOpt"]

# File path to save results
output_dir = "../../Data/Hugeresults"
output_file = os.path.join(output_dir, "hugeResults.csv")

# Ensure the output directory exists
os.makedirs(output_dir, exist_ok=True)

# Column headers for the CSV file as a list
headers = ["algorithm", "boardsize", "coverage", "startx", "starty", "pathcost", "executiontime", "nodes_explored", "path_length", "memory_usage"]

# Run each algorithm and save results
with open(output_file, mode="w", newline="") as csv_file:
    writer = csv.writer(csv_file)
    writer.writerow(headers)  # Write the header row

    for algorithm in algorithms:
        # Command to run, with working directory set to ../../src
        # -1 means that it can run indefinitely
        command = ["java", "P1main", algorithm, "8", "0,0", "100", "CSV", "Limit:-1"]
        
        try:
            # Run the command and capture the output, setting cwd to ../../src
            result = subprocess.run(command, capture_output=True, text=True, check=True, cwd="../../src")
            output = result.stdout.strip()  # Get the output and remove extra whitespace
            
            # Split the output into the expected columns
            # Assuming the output matches the headers order
            output_values = output.split(",")  # Split the output string by commas if it's CSV formatted
            
            # Write the algorithm name and output values to the CSV
            writer.writerow([algorithm] + output_values)
            print(f"Saved result for {algorithm}: {output}")
        
        except subprocess.CalledProcessError as e:
            print(f"Error running algorithm {algorithm}: {e.stderr}")
