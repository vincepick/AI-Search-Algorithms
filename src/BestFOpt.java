import java.util.List;

class BestFOpt extends GeneralSearch {

    /**
     * 
     * @param gridSize
     * @param startNode
     * @param coverage
     * @param verbose
     * @param time_limit
     */
    public BestFOpt(int gridSize, String startNode, int coverage, boolean verbose, int time_limit) {
        super(gridSize, startNode, coverage, verbose, time_limit);
    }

    /**
     * Calculating heuristic using Warnsdorf's rule
     */
    @Override
    protected int calcHeuristic() {
        List<Coordinate> possibleMoves = findPossibleMoves(currentNode.getCoordinates());
        return possibleMoves.size();
    }

    /**
     * Cost for BestF is just the heuristic
     */
    @Override
    protected int calcCost(int heuristic, int totalPathCost) {
        return heuristic;
    }

}