public record Coordinate(int x, int y) {

    /**
     * Calculates the Manhattan Distance
     * 
     * @param otherCoordinate the coordinate going to
     * @return The manhattan distance from one coordinate to another
     */
    public int calcDistance(Coordinate otherCoordinate) {
        int dx = Math.abs(otherCoordinate.x() - x);
        int dy = Math.abs(otherCoordinate.y() - y);
        return dx + dy;
    }

    @Override
    /**
     * To string method for formatting coordinates for command line output
     */
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
