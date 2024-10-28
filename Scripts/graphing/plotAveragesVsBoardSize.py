import pandas as pd
import matplotlib.pyplot as plt
import os
import argparse

def clean_and_average_data(file_path):
    # Load the data from the CSV file
    df = pd.read_csv(file_path)
    
    # Filter out rows where 'pathcost' is -1 or -100
    df = df[~df['pathcost'].isin([-1, -100])]
    
    # Group by 'boardsize' and calculate the mean of each numeric column
    averaged_data = df.groupby('boardsize').mean(numeric_only=True).reset_index()
    
    # # Save the cleaned and averaged data to a new CSV file
    # output_file = file_path.replace(".csv", "_cleaned_averaged.csv")
    # averaged_data.to_csv(output_file, index=False)
    # print(f"Cleaned and averaged data saved to {output_file}")
    
    return averaged_data

def plot_algorithm_performance(data, graphs_dir):
    # Define fields to plot against boardsize
    fields = ['pathcost', 'executiontime', 'nodes_explored', 'memory_usage']
    
    for field in fields:
        # Create a new plot for each field
        plt.figure(figsize=(10, 6))
        
        # Plot each algorithm's data for the specific field against boardsize
        for algo, df in data.items():
            if field in df.columns:
                plt.plot(df['boardsize'], df[field], label=f"{algo} Average {field.capitalize()}")
        
        # Add labels, title, and legend
        plt.xlabel('Board Size')
        plt.ylabel(f'Average {field.capitalize()}')
        plt.title(f'Algorithm Performance by {field.capitalize()} and Board Size')
        plt.legend()
        plt.grid(True)
        
        # Save each plot as a PNG file in the output directory
        output_path = os.path.join(graphs_dir, f'algorithm_performance_{field}.png')
        plt.savefig(output_path)
        plt.close()
        print(f"Graph for {field} saved to {output_path}")

# Main function to handle command-line arguments
if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Process and plot algorithm performance from CSV files.")
    parser.add_argument("folder_name", type=str, help="Path to the folder containing CSV files for each algorithm")

    args = parser.parse_args()
    folder_name = f"../../Data/{args.folder_name}"
    graphs_dir = os.path.join("../../Graphs", os.path.basename(folder_name))
    os.makedirs(graphs_dir, exist_ok=True)

    # List of algorithms
    algorithms = ["AStar", "BestF", "BestFOpt", "AStarOpt", "Alt"]

    # Dictionary to store averaged data for each algorithm
    averaged_data = {}

    # Process each algorithm CSV file
    for algo in algorithms:
        file_path = os.path.join(folder_name, f"{algo}.csv")
        if os.path.exists(file_path):
            print(f"Processing {file_path}")
            averaged_data[algo] = clean_and_average_data(file_path)
        else:
            print(f"File {file_path} not found, skipping.")

    # Plot data for each field
    plot_algorithm_performance(averaged_data, graphs_dir)
