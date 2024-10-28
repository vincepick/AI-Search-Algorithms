// a class for the nodes involved in each search algorithm
// import java.util.Objects;

// make implement it a  omprable, thats how the priority queue sorts them, need to specify to sort them by heuristic 
// consider switching this from <Coordinate> not sure if that is
public class Node {

    // use record, fields in it are immutable by default
    private final Coordinate coordinates;
    private final Node parent;
    private int cost;
    // TODO is this redundant, existing visited and numvisited
    private final int existingVisited;
    private final int numVisited;
    private final int totalCost;

    /**
     * Constructor for each node, each node represents a move in the search
     * 
     * @param coordinates     The coordinates of this node
     * @param parent          The parent of this node
     * @param cost            The cost of this node
     * @param existingVisited The length of the path leading up to this node (cost)
     * @param numVisited      The number of nodes visisted at hte point of this node
     * @param totalCost       The totalCost traveled to get to this node, TODO what
     *                        is total cost actually
     */
    public Node(Coordinate coordinates, Node parent, int existingVisited, int numVisited, int totalCost) {
        this.coordinates = coordinates;
        this.numVisited = numVisited;
        this.parent = parent;
        this.existingVisited = existingVisited;
        this.totalCost = totalCost;
    }

    // @Override
    // /**
    // * Compared to another node
    // */
    // public int compareTo(Node other) {
    // return Integer.compare(this.getCost(), other.getCost());
    // }

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
     * @return The totalCost
     */
    public int getTotalCost() {
        return totalCost;
    }

    /**
     * The cost
     * 
     * @return
     */
    public int getCost() {
        return cost;
    }

    /**
     * 
     * @return The existingVisited
     */
    public int getExistingDistance() {
        return existingVisited;
    }

    /**
     * 
     * @return The nuber of nodes visited up to this point
     */
    public int getNumVisited() {
        return numVisited;
    }

    /**
     * 
     * // * @param node Check if cordinates are equal with another node
     * // * @return boolean value for it it is equal or not
     * //
     */
    // public boolean cordEquals(Node node) {

    // if (this.coordinates == node.coordinates) {
    // return true;
    // }
    // return false;
    // }

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
        return new Node(new Coordinate(x, y), null, 0, 1, 0);
    }

}