class BestF extends GeneralSearch {

    /**
     * 
     * @param gridSize   Size of the Grid being explored
     * @param startNode  The coordinates of the node from which the tour begins
     * @param coverage   The number of nodes required in a path for the tour to be
     *                   considered finished
     * @param verbose    Boolean, weather verbose output is enabled
     * @param time_limit The amount of time before the search times out
     */
    public BestF(int gridSize, String startNode, int coverage, boolean verbose, int time_limit) {
        // Refering to superclass (parent) objects
        super(gridSize, startNode, coverage, verbose, time_limit);
    }

    //
    @Override
    /**
     * Function overrides the one of its superclass for a sepcific implementation
     * Uses the heuristic from the specification
     * @return The heuristic for a provided value
     */
    protected int calcHeuristic() {
        return 3 * (coverage - currentNode.getNumVisited());
    }

    @Override
    /**
     * @param heuristic     The heuristic
     * @param totalpathCost The total path cost up to this point
     * @return The cost to go to this node: in the case of BestF the same as the
     *         heuristic
     */
    protected int calcCost(int heuristic, int totalPathCost) {
        return heuristic;
    }

}