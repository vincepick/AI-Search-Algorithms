
public class TestHeuristicOpt {

    public static void main(String[] args) {
        testHeuristicOpt();
    }

    /**
     * Test heuristic calculation for using Warnsdorf's rule
     */
    public static void testHeuristicOpt() {
        // make with dummy values, do not impact heuristic anyway
        BestFOpt search = new BestFOpt(12, "0,0", 64, false, 1000);

        // Define test cases with different positions and expected heuristic results
        Object[][] testCases = {
                { "Test minimal moves from corner position", "0,0", 1, 3 },
                { "Test central position with more potential moves", "3,3", 1, 8 },
                { "Test other central position", "7,7", 1, 8 },
                { "Test on near-edge position", "10,11", 1, 3 }
        };

        // loop through each test case
        for (int i = 0; i < testCases.length; i++) {
            // Extract test case parameters
            String testCaseName = (String) testCases[i][0];
            String startNode = (String) testCases[i][1];
            int initialVisited = (int) testCases[i][2];
            int expectedHeuristic = (int) testCases[i][3];

            // get x and y cordinates
            String[] cordString = startNode.split(",");
            int x = Integer.parseInt(cordString[0].trim());
            int y = Integer.parseInt(cordString[1].trim());
            Coordinate coordinate = new Coordinate(x, y);

            // set current node to be used in calcHeuristic method
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
