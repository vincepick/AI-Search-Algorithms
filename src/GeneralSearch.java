import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public abstract class GeneralSearch {
    protected final int gridSize;
    protected final PriorityQueue<Node> frontier;
    protected Node currentNode;
    protected final int coverage;
    protected final boolean verbose;
    private final int timeLimit;
    private long[] results = new long[4];
    private long timeElapsed;
    public static final int NOT_FOUND = -1;
    public static final int NOT_TERMINATED = -100;

    /**
     * General Search Constructor for Every Algorithm
     * 
     * @param gridSize    The size of the grid being searched
     * @param startString The starting point in the grid, provided as a string
     * @param coverage    The required coverage for a successsful tour
     * @param verbose     Boolean Value, for if verbose outputs are needed or not
     * @param time_limit  A time limit for how long the search should take before
     *                    canceling
     */
    public GeneralSearch(int gridSize, String startString, int coverage, boolean verbose, int timeLimit) {
        this.gridSize = gridSize;
        this.verbose = verbose;
        this.coverage = coverage;

        String[] coordinatePair = startString.split(",");
        int x = Integer.parseInt(coordinatePair[0].trim());
        int y = Integer.parseInt(coordinatePair[1].trim());

        this.currentNode = Node.createInitialNode(x, y);
        int initialCost = calcCost(calcHeuristic(), 0);
        currentNode.setCost(initialCost);

        // Comparator is based on the cost, which is specific to each Algorithm
        this.frontier = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
        this.timeLimit = timeLimit;
    }

    /**
     * Abstract method specifically implemented within each algorithm
     * No parameters, all necessary info is stored already, (currentNode
     * and totalNodesVisited)
     * 
     * @return The heuristic for a given totalLength
     */
    protected abstract int calcHeuristic();

    /**
     * Abstract method for returning cost specific to each algorithm
     * 
     * @param heuristic     The heuristic as an int
     * @param totalPathCost The total path cost
     * @return The cost for a given heuristic and total path cost
     */
    protected abstract int calcCost(int heuristic, int totalPathCost);

    /**
     * General serach method
     * 
     * @return The total path cost of the search
     */
    public int search() {

        long startTime = System.currentTimeMillis();
        // Checking if given an impossible input
        if (currentNode.getNumVisited() >= coverage) {
            results[0] = NOT_FOUND;
            return NOT_FOUND;

        }
        // Add the starting Node to the frontier
        frontier.add(currentNode);
        // Keeping track of the number of nodes explored
        int nodesExplored = 1;

        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); // Suggest garbage collection to get a more accurate measurement
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        // While loop to continue touring until frontier is empty
        while (!frontier.isEmpty()) {
            if (((System.currentTimeMillis() - startTime) / 1000) > timeLimit) {
                // for if the program times out
                results[0] = NOT_TERMINATED;
                return NOT_TERMINATED;
            }

            if (verbose) { // Conditional to print out the frontier if in verbose mode
                printFrontier(frontier);
            }

            currentNode = frontier.poll();

            // Conditional to check if most recently visited node fulfills coverage
            if (currentNode.getNumVisited() >= coverage) {
                timeElapsed = System.currentTimeMillis() - startTime;
                results[1] = timeElapsed;

                runtime.gc(); // Optional: run garbage collection again for a more stable reading
                long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
                results[3] = (memoryAfter - memoryBefore);
                return calculateResult(currentNode, nodesExplored);
            }

            // return the possible moves from the returned array list, checking all of them
            // for if they exist up the linked list
            List<Coordinate> possibleMoves = findPossibleMoves(currentNode.getCoordinates());

            addToFrontier(currentNode, possibleMoves);
            nodesExplored++;
        }
        // if the coverage was never reached, means that no path exists
        timeElapsed = System.currentTimeMillis() - startTime;
        results[1] = timeElapsed;
        results[2] = nodesExplored;
        return NOT_FOUND;
    }

    /**
     * Method used to calculate the traverse back up the linked list of the search
     * Returns the info back to user for both verbose and non verbose outputs
     * 
     * @param node          The node which the search ended on
     * @param nodesExplored The number of nodes explored
     * @return An int, the total cost of the path
     */
    private int calculateResult(Node node, int nodesExplored) {
        int totalCost = node.getExistingDistance();
        int numOfNodesInPath = node.getNumVisited();
        // Stringbuilder used for the final path, only created in verbose mode
        StringBuilder pathString = verbose ? new StringBuilder() : null;
        while (node != null && node.getParent() != null) {
            // Coordinates inserted at the beggining for correct final output order
            pathString = verbose ? pathString.insert(0, node.getCoordinates().toString()) : pathString;
            node = node.getParent();
        }

        if (verbose) {
            pathString.insert(0, node.getCoordinates().toString());
            printVerboseInfo(nodesExplored, pathString, numOfNodesInPath);
        }
        results[2] = nodesExplored;
        results[0] = totalCost;
        return totalCost;
    }

    /**
     * Helper method for printing verbose info
     * 
     * @param nodesExplored Number of nodes explored
     * @param pathString    Result of the string builder for full path
     * @param numVisited    The number of nodes visited
     */
    private void printVerboseInfo(int nodesExplored, StringBuilder pathString, int numVisited) {
        System.out.println(nodesExplored);
        System.out.println(pathString);
        System.out.println(numVisited);
    }

    /**
     * Method used to print the frontier while in verbose mode
     * 
     * @param frontier The provided frontier at any point
     */
    public void printFrontier(PriorityQueue<Node> frontier) {
        // String builder for formatting the frontier string
        StringBuilder builder = new StringBuilder("[");

        // No need for null check, empty frontier will never be passed
        Iterator<Node> iterator = frontier.iterator();
        // will always have at least one node passed
        Node node = iterator.next();
        builder.append(node.toString());
        while (iterator.hasNext()) {
            builder.append(", ").append(iterator.next().toString());
        }
        builder.append("]");
        System.out.println(builder);
    }

    /**
     * Method to add potential moves to the frontier, and create corresponding nodes
     * 
     * @param parentNode    The parent node of those being addded to the frontier at
     *                      this oint
     * @param possibleMoves Potential moves from that node
     */
    public void addToFrontier(Node parentNode, List<Coordinate> possibleMoves) {
        // Creates nodes for moves known to be possible for better memory complexity
        for (Coordinate move : possibleMoves) {

            int distance = parentNode.getCoordinates().calcDistance(move); // Distance for just this node
            int existingDistance = parentNode.getExistingDistance() + distance; // Existing distance
            int numVisited = currentNode.getNumVisited() + 1; // Incrementing the number visited

            Node newNode = Node.createNode(move, parentNode, existingDistance, numVisited);
            
            // Setting to instance variable currentNode so node info can be accessed for
            // this particular node when calculating the heuristic if necessary
            currentNode = newNode;
            int heuristic = calcHeuristic(); // Calculating the heuristic
            int nodeCost = calcCost(heuristic, existingDistance); // Calculating the cost for this individual node
            newNode.setCost(nodeCost);
            frontier.add(newNode);
            // setting back
            currentNode = parentNode;
        }

    }

    // having this as a seperate class variable so moves could be changed later on
    private final static int[][] moves = {
            { 3, 0 }, { -3, 0 }, { 0, 3 }, { 0, -3 }, // Moving horizontal/vertical
            { 2, 2 }, { 2, -2 }, { -2, 2 }, { -2, -2 } // Moving diagonal
    };

    /**
     * Method to find possible moves
     * 
     * @param node A node from which to find potential moves
     * @return An ArrayList of potential moves from the starting node
     */
    protected List<Coordinate> findPossibleMoves(Coordinate position) {
        // Saves the possible moves as an ArrayList
        List<Coordinate> possibleMoves = new ArrayList<>();

        for (int[] move : moves) {
            int testX = position.x() + move[0];
            int testY = position.y() + move[1];
            if (moveIsPossible(testX, testY) && !checkIfInParent(currentNode, testX, testY)) {
                possibleMoves.add(new Coordinate(testX, testY));
            }
        }

        return possibleMoves;
    }

    /**
     * Checks if a move is not out of bounds
     * 
     * @param x Clumn of potential move
     * @param y Row of potential move
     * @return True if that move is within grid bounds, false otherwise
     */
    public boolean moveIsPossible(int x, int y) {
        // Checks that
        if (x >= gridSize || x < 0 || y >= gridSize || y < 0) {
            return false;
        }
        return true;
    }

    /**
     * Method to check if a potential move has alredy been traversed in that tour
     * 
     * @param nodeToCheck The node which will have its parents checked
     * @param x           The column of a potential move
     * @param y           The row of a potential move
     * @return
     */
    public boolean checkIfInParent(Node nodeToCheck, int x, int y) {
        Coordinate targetCoordinate = new Coordinate(x, y);
        // Traverses up the linked list
        while (nodeToCheck != null) {
            if (nodeToCheck.getCoordinates().equals(targetCoordinate)) {
                return true;
            }
            nodeToCheck = nodeToCheck.getParent();

        }
        return false;
    }

    /**
     * 
     * @return Keeping track of the node currently being examined/used in the search
     */
    public Node getCurrentNode() {
        System.out.println("currentNode is" + currentNode.getCoordinates());
        return currentNode;
    }

    /**
     * Used for getting results to build the CSVs
     * 
     * @return results array
     */
    public long[] getResults() {
        return results;
    }
}
