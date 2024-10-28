public class TestManhattan {

    public static void main(String[] args) {
        testManhattanDistance();
    }

    public static void testManhattanDistance() {
        // Dummy values, none matter while teseting the manhattan distance
        GeneralSearch search = new GeneralSearch(0, "0,0", 0, false, 0) {
            @Override
            protected int calculateHeuristic() {
                return 0; // Isnt being tested here, dummy value
            }

            @Override
            protected int calcCost(int heuristic, int totalPathCost) {
                return 0; // Isnt being tested here, dummy value
            }
        };

        // Define test cases with coordinates and expected results
        // Expected results achieved with:
        // https://www.omnicalculator.com/math/manhattan-distance
        Object[][] testCases = {
                { "manhattan distance with positive values", new Coordinate(1, 2), new Coordinate(4, 6), 7 },
                { "manhattan distance with negative values", new Coordinate(-1, -2), new Coordinate(-4, -6),
                        7 },
                { "manhattan distance with large values", new Coordinate(1000, 2000),
                        new Coordinate(4000, 6000), 7000 },
                { "manhattan distance with same coordinates", new Coordinate(0, 0), new Coordinate(0, 0), 0 },
                { "manhattan distance across zero boundary", new Coordinate(-1, -1), new Coordinate(1, 1), 4 }
        };

        // loop through each test case
        for (int i = 0; i < testCases.length; i++) {
            // format output string
            String testCase = "Test " + String.valueOf(i + 1) + ": ";
            // Having to type cast becuase had an array of type Object
            String testName = (String) testCases[i][0];
            Coordinate from = (Coordinate) testCases[i][1];
            Coordinate to = (Coordinate) testCases[i][2];
            int expectedDistance = (int) testCases[i][3];

            int calculatedDistance = from.calcDistance(to);

            // Print results for each test case

            if (calculatedDistance == expectedDistance) {
                System.out.println(testCase + testName + " - correct");
            } else {
                System.out.println(
                        testCase + testName + " - incorrect: expected " + expectedDistance + " but got "
                                + calculatedDistance);
            }
        }
    }
}
