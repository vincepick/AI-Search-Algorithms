class AStar extends GeneralSearch {

    /**
     * 
     * @param gridSize   Size of the Grid being explored
     * @param startNode  The coordinates of the node from which the tour begins
     * @param coverage   The number of nodes required in a path for the tour to be
     *                   considered finished
     * @param verbose    Boolean, weather verbose output is enabled
     * @param time_limit The amount of time before the search times out
     */
    public AStar(int gridSize, String startNode, int coverage, boolean verbose, int time_limit) {
        // Refering to superclass (parent) objects
        super(gridSize, startNode, coverage, verbose, time_limit);
    }

    /*
     * Using heuristic provided in the specification
     */
    @Override
    protected int calcHeuristic() {
        return 3 * (coverage - currentNode.getNumVisited());
    }

    /**
     * Heuristic + path cost for A star implementation
     */
    @Override
    protected int calcCost(int heuristic, int totalPathCost) {
        return heuristic + totalPathCost;
    }

}