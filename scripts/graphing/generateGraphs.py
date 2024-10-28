import pandas as pd
import matplotlib.pyplot as plt
import os
import argparse

def plot_algorithm_performance(folder_name):
    # List of algorithms
    algorithms = ["AStar", "BestF", "BestFOpt", "AStarOpt"]
    # algorithms = ["AStar", "BestF"]

    data = {}

    # Load the CSV files for each algorithm from the specified folder
    for algo in algorithms:
        file_path = os.path.join(f"../Data/{folder_name}", f"{algo}.csv")

        data[algo] = pd.read_csv(file_path)  # Each CSV is named after the algorithm, like AStar.csv

# Create the output directory for graphs
    graphs_dir = os.path.join("../Graphs", os.path.basename(folder_name))
    os.makedirs(graphs_dir, exist_ok=True)

    # Plot each algorithm's data
    plt.figure(figsize=(10, 6))

    for algo, df in data.items():
        plt.plot(df['pathcost'], df['boardsize'], label=algo)

    # Add labels, title, and legend
    plt.xlabel('Path Cost')
    plt.ylabel('Board Size')
    plt.title('Algorithm Performance by Path Cost and Board Size')
    plt.legend()
    plt.grid(True)

    # Save the plot as a PNG file in the output directory
    output_path = os.path.join(graphs_dir, 'algorithm_performance.png')
    plt.savefig(output_path)
    plt.close()
    print(f"Graph saved to {output_path}")

# Main function to handle command line argument
if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Plot algorithm performance from CSV files.")
    parser.add_argument("folder_name", type=str, help="Path to the folder containing CSV files for each algorithm")

    args = parser.parse_args()
    plot_algorithm_performance(args.folder_name)
