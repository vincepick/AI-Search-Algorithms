import java.util.List;

class AStarOpt extends GeneralSearch {

    /**
     * 
     * @param gridSize
     * @param startNode
     * @param coverage
     * @param verbose
     * @param time_limit
     */
    public AStarOpt(int gridSize, String startNode, int coverage, boolean verbose, int time_limit) {
        super(gridSize, startNode, coverage, verbose, time_limit);
    }

    /**
     * Calculate Heuristic
     */
    @Override
    protected int calculateHeuristic() {
        // Checking the possible moves of the current node
        List<Coordinate> possibleMoves = findPossibleMoves(currentNode.getCoordinates());
        return possibleMoves.size();
    }

    /**
     * Calculate Cost
     */
    @Override
    protected int calcCost(int heuristic, int totalPathCost) {
        return heuristic + totalPathCost;
    }

}