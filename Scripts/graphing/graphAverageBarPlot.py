import pandas as pd
import matplotlib.pyplot as plt
import os
import argparse

def clean_and_average_data(file_path):

   
    df = pd.read_csv(file_path)
    
    # remove rows where 'pathcost' is -1 or -100, would mess up graph output while providing no helpful data for this analysis.  
    df = df[~df['pathcost'].isin([-1, -100])]
    
    averaged_data = df.groupby('boardsize').mean(numeric_only=True).reset_index()
    
    return averaged_data

def plot_algorithm_performance(data, graphs_dir):
    # fields to base bar charts on
    fields = ['pathcost', 'executiontime', 'nodes_explored', 'memory_usage']
    
    algorithms = list(data.keys())
    for field in fields:
        # Collect average field values across algos
        averages = [data[algo][field].mean() for algo in algorithms if field in data[algo].columns]

        # Plot as a bar chart
        plt.figure(figsize=(10, 6))
        plt.bar(algorithms, averages, color='skyblue')
        
        plt.xlabel('Algorithm')
        plt.ylabel(f'Average {field.capitalize()}')
        plt.title(f'Average {field.capitalize()} across Algorithms')
        plt.grid(axis='y')
        
        # save each plot in output directory
        output_path = os.path.join(graphs_dir, f'algorithm_performance_{field}_bar-barplot.png')
        plt.savefig(output_path)
        plt.close()
        print(f"Bar chart for {field} saved to {output_path}")

# Main function to handle command-line arguments
if __name__ == "__main__":

    parser = argparse.ArgumentParser()
    parser.add_argument("folder_name", type=str)

    args = parser.parse_args()
    folder_name = f"../../Data/{args.folder_name}"
    graphs_dir = os.path.join("../../Graphs", os.path.basename(folder_name) + "-barplot")
    os.makedirs(graphs_dir, exist_ok=True)

    # List of algorithms
    algorithms = ["AStar", "BestF", "BestFOpt", "AStarOpt", "Alt"]

    averaged_data = {}

    # Process each csv file
    for algo in algorithms:
        file_path = os.path.join(folder_name, f"{algo}.csv")
        if os.path.exists(file_path):
            print(f"Processing {file_path}")
            averaged_data[algo] = clean_and_average_data(file_path)
        else:
            print(f"File {file_path} not found..")

    plot_algorithm_performance(averaged_data, graphs_dir)
