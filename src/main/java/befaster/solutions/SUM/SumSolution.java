package befaster.solutions.SUM;

import befaster.runner.SolutionNotImplementedException;

@SuppressWarnings("unused")
public class SumSolution {

    public int compute(int x, int y) {

        // Check if the input parameters are within the specified range
        if (x < 0 || x > 100 || y < 0 || y > 100) {
            throw new IllegalArgumentException("Input parameters must be between 0 and 100");
        }

        // Calculate the sum of the two numbers
        return x + y;

        //throw new SolutionNotImplementedException();
    }

}
