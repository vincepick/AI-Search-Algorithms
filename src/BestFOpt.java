import java.util.List;

class BestFOpt extends GeneralSearch {

    /**
     * 
     * @param gridSize   Size of the Grid being explored
     * @param startNode  The coordinates of the node from which the tour begins
     * @param coverage   The number of nodes required in a path for the tour to be
     *                   considered finished
     * @param verbose    Boolean, weather verbose output is enabled
     * @param time_limit The amount of time before the search times out
     */
    public BestFOpt(int gridSize, String startNode, int coverage, boolean verbose, int time_limit) {
        // Refering to superclass (parent) objects
        super(gridSize, startNode, coverage, verbose, time_limit);
    }

    /**
     * Calculating heuristic using Warnsdorf's rule
     * @return Warnsdorf's rule
     */
    @Override
    protected int calcHeuristic() {
        List<Coordinate> possibleMoves = findPossibleMoves(currentNode.getCoordinates());
        return possibleMoves.size();
    }

    /**
     * Cost for BestF is just the heuristic
     * @return Warnsdorf's rule
     */
    @Override
    protected int calcCost(int heuristic, int totalPathCost) {
        return heuristic;
    }

}