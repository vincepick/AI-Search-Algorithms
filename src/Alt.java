class Alt extends GeneralSearch {

    /**
     * 
     * @param gridSize   Size of the Grid being explored
     * @param startNode  The coordinates of the node from which the tour begins
     * @param coverage   The number of nodes required in a path for the tour to be
     *                   considered finished
     * @param verbose    Boolean, weather verbose output is enabled
     * @param time_limit The amount of time before the search times out
     */
    public Alt(int gridSize, String startNode, int coverage, boolean verbose, int time_limit) {
        // Refering to superclass (parent) objects
        super(gridSize, startNode, coverage, verbose, time_limit);
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