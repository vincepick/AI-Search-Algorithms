import java.util.List;

class AStarOpt extends GeneralSearch {

    public AStarOpt(int gridSize, String startNode, int coverage, boolean verbose, int time_limit) {
        super(gridSize, startNode, coverage, verbose, time_limit);
    }

    /**
     * 
     */
    @Override
    protected int calculateHeuristic() {
        // Checking the possible moves of the current node
        List<Coordinate> possibleMoves = findPossibleMoves(currentNode.getCoordinates());
        return possibleMoves.size();
    }

    // can i overload this or anything to not have it be scuffed
    @Override
    protected int calcCost(int heuristic, int totalPathCost) {
        return heuristic + totalPathCost;
    }

}