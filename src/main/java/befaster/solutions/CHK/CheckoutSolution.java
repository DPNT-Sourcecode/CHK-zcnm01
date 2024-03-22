package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;
import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {

    int total = 0; // Global variable
    public Integer checkout(String skus) {
        // Price table and special offers
        Map<Character, Integer> prices = new HashMap<>();
        prices.put('A', 50);
        prices.put('B', 30);
        prices.put('C', 20);
        prices.put('D', 15);
        prices.put('E', 40);


        // Initialize item counts (ensure it's empty before processing)
        Map<Character, Integer> itemCounts = new HashMap<>();

        // Count occurrences of each item
        for (char sku : skus.toCharArray()) {
            if (!prices.containsKey(sku)) {
                return -1; // Illegal input
            }
            itemCounts.put(sku, itemCounts.getOrDefault(sku, 0) + 1);
        }

        // Apply special offers in descending order of priority (more items get discount first)
        applyOffers(itemCounts, 'A', null, 3, 130);
        applyOffers(itemCounts, 'A', null, 5, 200);
        applyOffers(itemCounts, 'E', 'B', 2, 1);
        applyOffers(itemCounts, 'B', null, 2, 45);

        // Calculate total price for remaining items
        for (Map.Entry<Character, Integer> entry : itemCounts.entrySet()) {
            char sku = entry.getKey();
            int count = entry.getValue();
            total += count * prices.get(sku);
        }

        return total;
    }

    // Helper method to apply a specific offer
    private void applyOffers(Map<Character, Integer> itemCounts, char item1, Character item2, int requiredCount1, int discount) {
        if (itemCounts.containsKey(item1) && itemCounts.get(item1) >= requiredCount1) {
            int discountCount = Math.min(itemCounts.get(item1) / requiredCount1,
                    itemCounts.getOrDefault(item1, 0)); // Limit discount to available items
            itemCounts.put(item1, itemCounts.get(item1) % requiredCount1); // Update remaining item1 count
            total += discountCount * discount; // Apply discount to total directly
            if (item2 != null) {
                itemCounts.put(item2, Math.max(itemCounts.getOrDefault(item2, 0) - discountCount, 0)); // Update item2 count after discount
            }
        }
    }


}
