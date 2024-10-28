class Alt extends GeneralSearch {

    /**
     * 
     * @param gridSize
     * @param startNode
     * @param coverage
     * @param verbose
     * @param time_limit
     */
    public Alt(int gridSize, String startNode, int coverage, boolean verbose, int time_limit) {
        super(gridSize, startNode, coverage, verbose, time_limit);
        // is not included in abstract class because may not necessarily be used within
        // heuristic
    }

    // Overriding Function
    @Override
    protected int calcHeuristic() {
        // Testing for no heurstic whatsoever, effectively a BFS
        return 0;
    }

    /**
     * Returning just the heuristic as the cost, providing no information at all
     */
    @Override
    protected int calcCost(int heuristic, int totalPathCost) {
        return heuristic;
    }

}