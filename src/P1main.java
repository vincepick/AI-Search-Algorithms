
/********************
 * Starter Code
 * 
 * This class contains some examples of required inputs and outputs
 * 
 * @author alice toniolo
 *
 *         we assume parameters are valid, no need to check
 * 
 */

public class P1main {

	public static final int NOT_IMPLEMENTED = -200;
	public static final int NOT_FOUND = -1;
	public static final int NOT_TERMINATED = -100;
	private static long[] results;

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length < 4) {
			System.out
					.println(
							"usage: java P1main <AStar|BestF|Alt|AStarOpt|BestFOpt> <D> <r,c> <coverage> <CSV or verbose>");
			System.exit(1);
		}

		// Process input examples
		boolean verbose = false;
		boolean csv = false;

		// Choosing output mode
		if (args[args.length - 1].equals("verbose")) {
			verbose = true;
		} else if (args[args.length - 1].startsWith("CSV")) {
			csv = true;
		}

		String algo = args[0];
		int d = Integer.parseInt(args[1]);
		String start = args[2];
		int coverage = Math.round(d * d * Integer.parseInt(args[3]) / 100);
		int time_limit = 30; // run at most for 30s
		// run your search algorithm
		int path_cost = runSearch(algo, d, start, coverage, verbose, time_limit);

		// Printing for csv mode
		if (csv) {
			System.out.print(d + "," + coverage + "," + start);
			for (int i = 0; i < results.length; i++) {
				System.out.print("," + results[i]);
			}
			System.out.println();

		} else { // Printing path cost
			System.out.println(path_cost);
		}
	}

	/**
	 * Method to call a specific search algorithm
	 * 
	 * @param algo       choice of algorithm
	 * @param d          size of board
	 * @param start      starting coordinate
	 * @param coverage   required coverage
	 * @param verbose    verbose toggle
	 * @param time_limit amount of time to run for
	 * @return
	 */
	private static int runSearch(String algo, int d, String start, int coverage, boolean verbose, int time_limit) {
		int temp;
		switch (algo) {
			// AStar|BestF|Alt|AStarOpt|BestFOpt>
			case "BestF": // run BestF
				BestF bestfSearch = new BestF(d, start, coverage, verbose, time_limit);
				temp = bestfSearch.search();
				results = bestfSearch.getResults();
				return temp;
			case "AStar": // run AStar
				AStar aStarSearch = new AStar(d, start, coverage, verbose, time_limit);
				temp = aStarSearch.search();
				results = aStarSearch.getResults();
				return temp;
			case "AStarOpt": // run AStar with additional heuristic
				AStarOpt aStarOpt = new AStarOpt(d, start, coverage, verbose, time_limit);
				temp = aStarOpt.search();
				results = aStarOpt.getResults();
				return temp;
			case "BestFOpt": // run BestFOpt
				BestFOpt bestFOpt = new BestFOpt(d, start, coverage, verbose, time_limit);
				temp = bestFOpt.search();
				results = bestFOpt.getResults();
				return temp;
			case "Alt": // run alt
				Alt altSearch = new Alt(d, start, coverage, verbose, time_limit);
				temp = altSearch.search();
				results = altSearch.getResults();
				return temp;
		}

		return NOT_IMPLEMENTED;
	}

}