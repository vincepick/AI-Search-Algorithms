This readme outlines how to run the program within this practical 

# Pre Requisites
This program has only been tested on the lab machines, and that version of Java (Amazon Corretto 21) 
Running it on other java versions may yield different results or not be supported. 

For graphing, this program also relies on the MatPlotLib and Pandas Python libraries. 

# Directory Structure

- **`src/`**: Contains all source files, including `P1main.java`.
- **`Tests/`**: Stacscheck automatic testing located within tests directory.
- **`scripts/generating/`**: Contains scripts for generating data and saving to CSV using the program.
- **`scripts/graphing/`**: Contains scripts for generating graphs from existing CSV files in `Data/`.
- **`Data/`**: Contains Data which can be used to make graphs.
- **`Graphs/`**: Contains graphs generated off the CSV data in `Data/`


# Running the program 
To run the program, while in src directory enter a command of the form: 
`java P1main <Algorithm> <D> <r,c> <coverage> [verbose or CSV]`
## Parameters
*Algorithm*: Specifies the algorithm. Options are:
    AStar: A* Search Algorithm
    BestF: Best First Search Algorithm
    AStarOpt: A* Search with different heuristic
    BestFOpt: Best First Search with different heuristic
    Alt: Not implemented within this practical 

*D*: Dimension of the grid.

*r,c*: Starting position as row and column (comma-separated).

*coverage*: Percentage of grid cells to cover (integer between 0-100).

[verbose]: Optional flag. Include this parameter to run in verbose mode

[CSV]: Optional flag. Include this parameter to run to output additional results in CSV format

## Running Modes
The program has three modes which are 
### Default
Only outputs the path cost
`java P1main <Algorithm> <D> <r,c> <coverage>`
### Verbose
Provides verbose output, as outlined in the specification
`java P1main <Algorithm> <D> <r,c> <coverage> verbose`
### CSV
Provides full results in the form of comma seperated values, typically used for generating CSV files
`java P1main <Algorithm> <D> <r,c> <coverage> CSV`
## Special Outputs
Path cost has numerous special output options which have different meanings. 
    -1   : no path exists
    -100 : algorithm times out (within 30 seconds)
    -200 : if selected algorithm was not implemented



# Running the stacscheck tests
While in project directory 
`stacscheck Tests`

# Running scripts
Scripts both for generating graphs and collecting/saving data must be ran while in their directory:
`python generateGraphsExampleScript` ran in `scripts/generating/`
or
`python generateDataExampleScript` ran in `scripts/graphing/`










