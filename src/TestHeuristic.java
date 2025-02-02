public class TestHeuristic {

    public static void main(String[] args) {
        testHeuristic();
    }

    /**
     * A method purely for testing correct heuristic calculations
     * Results of this methods output are automatically verified through Stacscheck
     */
    public static void testHeuristic() {
        // make AStar with dummy values, which do not impact heuristic calculations anyway
        AStar search = new AStar(8, "0,0", 64, false, 1000);

        // test cases with different positions and expected results, correct results calculated manually
        Object[][] testCases = {
                { "Test minimal coverage remaining from initial position", "0,0", 1, 189 },
                { "Test some nodes visited", "0,0", 20, 132 },
                { "Test nearly full coverage", "0,0", 63, 3 },
                { "Test full coverage achieved", "0,0", 64, 0 }
        };

        // Loop through each test case
        for (int i = 0; i < testCases.length; i++) {
            // get parameters from test case array
            // need to cast because it is an array of type objects
            String testCaseName = (String) testCases[i][0];
            String startNode = (String) testCases[i][1];
            int initialVisited = (int) testCases[i][2];
            int expectedHeuristic = (int) testCases[i][3];

            // parse x and y cords
            String[] cordString = startNode.split(",");
            int x = Integer.parseInt(cordString[0].trim());
            int y = Integer.parseInt(cordString[1].trim());
            Coordinate coordinate = new Coordinate(x, y);

            // set currentNode for heuristic() to be called upon
            search.currentNode = new Node(coordinate, null, 0, initialVisited);

            // Calculate the heuristic and compare with the expected result
            int calculatedHeuristic = search.calcHeuristic();

            // print results for each test case
            if (calculatedHeuristic == expectedHeuristic) {
                System.out.println(testCaseName + " - correct");
            } else {
                System.out.println(
                        testCaseName + " - wrong: expected " + expectedHeuristic + " but got "
                                + calculatedHeuristic);
            }
        }
    }
}
