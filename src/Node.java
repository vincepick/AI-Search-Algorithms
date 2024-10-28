public class Node {

    // Use record class, fields in it are immutable by default
    private final Coordinate coordinates; // The location of this nodes move, to avoid duplicate nodes in same
                                          // path
    private final Node parent; // Referencing this nodes parent
    private final int existingDistance; // The distance to reach this node, used for A star cost
    private final int numVisited; // Necessary for checking if coverage has been reached
    // TODO remove distance
    // private final int distance; // The distance traveled from this nodes parent
    // to this node, used for returning
    // final cost, the manhattan distance traveled by the pawn to reach the required
    // coverage.

    // updated with a setter
    private int cost; // The cost, used within the priority queue to pick the next node

    /**
     * Constructor for each node, each node represents a move in the search
     * 
     * @param coordinates      The coordinates of this node
     * @param parent           The parent of this node
     * @param cost             The cost of this node
     * @param existingDistance The length of the path leading up to this node (cost)
     * @param numVisited       The number of nodes visisted at hte point of this
     *                         node
     *                         TODO remove distance
     * @param distance         The distance traveled to get to this node from the
     *                         last node
     */
    // TODO remove distance
    public Node(Coordinate coordinates, Node parent, int existingDistance, int numVisited) {
        this.coordinates = coordinates;
        this.numVisited = numVisited;
        this.parent = parent;
        this.existingDistance = existingDistance;
        // TODO remove distance
        // this.distance = distance;
    }

    /**
     * Used to set the cost to reach this node from the last node
     * 
     * @param cost
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Method to retrieve the coordinates of this node
     * 
     * @return The coordinates
     */
    public Coordinate getCoordinates() {
        return coordinates;
    }

    /**
     * 
     * @return The parent
     */
    public Node getParent() {
        return parent;
    }

    /**
     * 
     * @return The distance
     */
    // public int getDistance() {
    // return distance;
    // }

    /**
     * The cost
     * 
     * @return Cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * 
     * @return The existingVisited
     */
    public int getExistingDistance() {
        return existingDistance;
    }

    /**
     * 
     * @return The nuber of nodes visited up to this point
     */
    public int getNumVisited() {
        return numVisited;
    }

    /**
     * For printing in verbose mode
     */
    public String toString() {
        String nodeString = (coordinates.toString() + ":" + cost);
        return nodeString;
    }

    /**
     * Used to create an initial node
     * 
     * @param x            X coordinate of the node
     * @param y            Y coordinate of the node
     * @param inititalCost The initial cost of this node
     * @return Returns a created initial Node
     */
    public static Node createInitialNode(int x, int y) {
        // Initial node will always have certain values, such as existingVisited 0
        return new Node(new Coordinate(x, y), null, 0, 1);
    }

}