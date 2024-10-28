class BestF extends GeneralSearch {

    /**
     * 
     * @param gridSize
     * @param startNode
     * @param coverage
     * @param verbose
     * @param time_limit
     */
    public BestF(int gridSize, String startNode, int coverage, boolean verbose, int time_limit) {
        super(gridSize, startNode, coverage, verbose, time_limit);
    }

    //
    @Override
    /**
     * Function overrides the one of its superclass for a sepcific implementation
     * 
     * @param totalVisited The number of nodes visisted within this path at
     *                     this point
     * 
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