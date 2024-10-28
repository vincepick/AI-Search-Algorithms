class Alt extends GeneralSearch {

    public Alt(int gridSize, String startNode, int coverage, boolean verbose, int time_limit) {
        super(gridSize, startNode, coverage, verbose, time_limit);
        // is not included in abstract class because may not necessarily be used within
        // heuristic
    }

    // needs to be an abstract function so that I can make an alternate heuristic
    // for other functions where necessary
    // better for extensibility
    @Override
    protected int calculateHeuristic() {
        // Testing for no heurstic whatsoever
        return 1;
    }

    @Override
    protected int calcCost(int heuristic, int totalPathCost) {
        return heuristic;
    }

}