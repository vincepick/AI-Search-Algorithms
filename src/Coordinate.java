//not possible i need this, depends on weather i want to override the toString or not 

public record Coordinate(int x, int y) {

    public int calculateManhattanDistance(Coordinate otherCoordinate) {
        return Math.abs(this.x - otherCoordinate.x) + Math.abs(this.y - otherCoordinate.y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
