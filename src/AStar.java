class AStar extends GeneralSearch {

    /**
     * 
     * @param gridSize
     * @param startNode
     * @param coverage
     * @param verbose
     * @param time_limit
     */
    public AStar(int gridSize, String startNode, int coverage, boolean verbose, int time_limit) {
        super(gridSize, startNode, coverage, verbose, time_limit);
    }

    /*
     * Using heuristic provided in the spec
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