package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;
import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {


        public Integer checkout(String skus) {
            // Price table and special offers
            Map<Character, Integer> prices = new HashMap<>();
            prices.put('A', 50);
            prices.put('B', 30);
            prices.put('C', 20);
            prices.put('D', 15);
            prices.put('E', 40);

            // Initialize total checkout value
            int total = 0;

            // Count occurrences of each item
            Map<Character, Integer> itemCounts = new HashMap<>();
            for (char sku : skus.toCharArray()) {
                if (!prices.containsKey(sku)) {
                    return -1; // Illegal input
                }
                itemCounts.put(sku, itemCounts.getOrDefault(sku, 0) + 1);
            }

            // Apply special offer for SKU 'A' and calculate total checkout value
            int aCount = itemCounts.getOrDefault('A', 0);
            int offerPriceA3 = 130;
            int offerPriceA5 = 200;

            total += (aCount / 5) * offerPriceA5 + ((aCount % 5) / 3) * offerPriceA3 + ((aCount % 5) % 3) * prices.get('A');

            // Apply special offer for item 'E': buy 2 E's, get one B free
            int eCount = itemCounts.getOrDefault('E', 0);
            int bCount = itemCounts.getOrDefault('B', 0);
            int freeBs = eCount / 2;
            total += eCount * prices.get('E') - Math.min(freeBs, bCount) * prices.get('B');

            // Calculate total for remaining items
            for (Map.Entry<Character, Integer> entry : itemCounts.entrySet()) {
                char sku = entry.getKey();
                int count = entry.getValue();
                if (sku != 'A' && sku != 'E') {
                    total += count * prices.get(sku);
                }
            }

            return total;
        }
    }



