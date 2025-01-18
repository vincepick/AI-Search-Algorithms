import pandas as pd
import matplotlib.pyplot as plt
import os
import argparse

def clean_and_average_data(file_path):
    # load data from the CSV file
    df = pd.read_csv(file_path)
    
    # remove rows where 'pathcost' is -1 or -100, would mess up graph output while providing no helpful data for this analysis.  
    df = df[~df['pathcost'].isin([-1, -100])]
    
    averaged_data = df.groupby('boardsize').mean(numeric_only=True).reset_index()    
    return averaged_data

def plot_algorithm_performance(data, graphs_dir):
    # fields to plot against boardsize
    fields = ['pathcost', 'executiontime', 'nodes_explored', 'memory_usage']
    
    for field in fields:
        # Create a new plot for each field
        plt.figure(figsize=(10, 6))
        
        # plot each algo's data for the specific field against boardsize
        for algo, df in data.items():
            if field in df.columns:
                plt.plot(df['boardsize'], df[field], label=f"{algo} Average {field.capitalize()}")
        
        plt.xlabel('Board Size')
        plt.ylabel(f'Average {field.capitalize()}')
        plt.title(f'Algorithm Performance by {field.capitalize()} and Board Size')
        plt.legend()
        plt.grid(True)
        
        # Save each plot in output directory
        output_path = os.path.join(graphs_dir, f'algorithm_performance_{field}-linegraph.png')
        plt.savefig(output_path)
        plt.close()
        print(f"graph for {field} saved to {output_path}")

# main function to handle command-line arguments and call other functions
if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("folder_name", type=str)

    args = parser.parse_args()
    folder_name = f"../../Data/{args.folder_name}"
    graphs_dir = os.path.join("../../Graphs", os.path.basename(folder_name) + "-linegraph")
    os.makedirs(graphs_dir, exist_ok=True)

    # algorithms
    algorithms = ["AStar", "BestF", "BestFOpt", "AStarOpt", "Alt"]

    averaged_data = {}

    # Process each csv
    for algo in algorithms: # for loop to iterate through each algo
        file_path = os.path.join(folder_name, f"{algo}.csv")
        if os.path.exists(file_path):
            print(f"Processing {file_path}")
            averaged_data[algo] = clean_and_average_data(file_path)
        else:
            print(f"File {file_path} not found.")

    # Plot data for each field
    plot_algorithm_performance(averaged_data, graphs_dir)
