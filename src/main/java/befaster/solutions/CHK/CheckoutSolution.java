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

        // Calculate total price based on item count and regular price
        for (Map.Entry<String, Integer> entry : itemCount.entrySet()) {
            String item = entry.getKey();
            int price = getPrice(item);
            int count = entry.getValue();
            total += price * count;
        }

        return total;
    }

    // Helper method to apply a specific offer
    private void applyOffer(HashMap<String, Integer> itemCount, String item1, String item2, int requiredCount1, int discount) {
        if (itemCount.containsKey(item1) && itemCount.get(item1) >= requiredCount1) {
            int freeItemCount = itemCount.get(item1) / requiredCount1;
            itemCount.put(item1, itemCount.get(item1) % requiredCount1); // Update remaining item1 count
            if (item2 != null) {
                itemCount.put(item2, Math.max(itemCount.getOrDefault(item2, 0) - freeItemCount, 0)); // Update item2 count after discount
            }
        }
    }

    // Helper method to get price for an item (replace with actual price lookup logic)
    private int getPrice(String item) {
        switch (item) {
            case "A":
                return 50;
            case "B":
                return 30;
            case "C":
                return 20;
            case "D":
                return 15;
            case "E":
                return 40;
            default:
                throw new IllegalArgumentException("Invalid item code: " + item);
        }
    }
}