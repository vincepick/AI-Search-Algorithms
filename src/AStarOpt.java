import java.util.List;

class AStarOpt extends GeneralSearch {

    /**
     * 
     * @param gridSize   Size of the Grid being explored
     * @param startNode  The coordinates of the node from which the tour begins
     * @param coverage   The number of nodes required in a path for the tour to be
     *                   considered finished
     * @param verbose    Boolean, weather verbose output is enabled
     * @param time_limit The amount of time before the search times out
     */
    public AStarOpt(int gridSize, String startNode, int coverage, boolean verbose, int time_limit) {
        // Refering to superclass (parent) objects
        super(gridSize, startNode, coverage, verbose, time_limit);
    }

    /**
     * Calculate Heuristic, using Warnsdorf's rule
     * @return The heuristic, Warnsdorf's rule
     */
    @Override
    protected int calcHeuristic() {
        // Checking the possible moves of the current node
        List<Coordinate> possibleMoves = findPossibleMoves(currentNode.getCoordinates());
        return possibleMoves.size();
    }

    /**
     * Calculate Cost, adding heuristic and totalPathCost for A*
     * @return The cost for AStar Opt implementation
     */
    @Override
    protected int calcCost(int heuristic, int totalPathCost) {
        return heuristic + totalPathCost;
    }

}