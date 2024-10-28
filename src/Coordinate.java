public record Coordinate(int x, int y) {

    /**
     * Calculates the Manhattan Distance being the current node and a destination node
     * 
     * @param destinationCoordinate the coordinate going to
     * @return The manhattan distance from one coordinate to another
     */
    public int calcDistance(Coordinate destinationCoordinate) {
        int dx = Math.abs(destinationCoordinate.x() - x);
        int dy = Math.abs(destinationCoordinate.y() - y);
        return dx + dy;
    }

    @Override
    /**
     * To string method to format coordinate strings for command line output
     */
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
