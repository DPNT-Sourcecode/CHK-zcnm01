package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;
import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {
    public int checkout(String basket) {
        int total = 0;
        HashMap<String, Integer> itemCount = new HashMap<>();

        // Count item occurrences
        for (char item : basket.toCharArray()) {
            itemCount.put(String.valueOf(item), itemCount.getOrDefault(String.valueOf(item), 0) + 1);
        }

        // Apply special offers in descending order of priority (more items get discount first)
        for (String item : itemCount.keySet()) {
            applyOffer(itemCount, item, null, 3, 130);
            applyOffer(itemCount, item, null, 5, 200);
            applyOffer(itemCount, item, "B", 2, 1); // Apply "E" offer after "A" for the same item
        }
        applyOffer(itemCount, "B", null, 2, 45);

        // Calculate total price with error handling
        for (Map.Entry<String, Integer> entry : itemCount.entrySet()) {
            String item = entry.getKey();
            try {
                int price = getPrice(item);
                int count = entry.getValue();
                total += price * count;
            } catch (IllegalArgumentException e) {
                // Handle invalid item here (e.g., return -1, log error)
                System.err.println("Invalid item code: " + item);
                return -1; // Example: return error code
            }
        }

        return total;
    }
}