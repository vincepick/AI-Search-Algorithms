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
        // is not included in abstract class because may not necessarily be used within
        // heuristic
    }

    // needs to be an abstract function so that I can make an alternate heuristic
    // for other functions where necessary
    // better for extensibility
    @Override
    /**
     * Function overrides the one of its superclass for a sepcific implementation
     * 
     * @param totalVisited The number of nodes visisted within this path at
     *                     this point
     * 
     * @return The heuristic for a provided value
     */
    protected int calculateHeuristic() {
        // TODO why is this plus 1
        return 3 * (coverage - currentNode.getNumVisited());
    }

    // can i overload this or anything to not have it be scuffed
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