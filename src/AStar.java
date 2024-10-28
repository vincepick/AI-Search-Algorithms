class AStar extends GeneralSearch {

    public AStar(int gridSize, String startNode, int coverage, boolean verbose, int time_limit) {
        super(gridSize, startNode, coverage, verbose, time_limit);
    }

    /*
     * Using heuristic provided in the spec
     */
    @Override
    protected int calculateHeuristic() {
        return 3 * (coverage - currentNode.getNumVisited());
    }

    // can i overload this or anything to not have it be scuffed
    @Override
    protected int calcCost(int heuristic, int totalPathCost) {
        return heuristic + totalPathCost;
    }

}