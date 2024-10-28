import java.util.List;

class BestFOpt extends GeneralSearch {

    public BestFOpt(int gridSize, String startNode, int coverage, boolean verbose, int time_limit) {
        super(gridSize, startNode, coverage, verbose, time_limit);
    }

    // needs to be an abstract function so that I can make an alternate heuristic
    // for other functions where necessary
    // better for extensibility
    @Override
    protected int calculateHeuristic() {
        List<Coordinate> possibleMoves = findPossibleMoves(currentNode.getCoordinates());
        return possibleMoves.size();
    }

    @Override
    protected int calcCost(int heuristic, int totalPathCost) {
        return heuristic;
    }

}