package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import javax.security.auth.login.AccountException;
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


        int bCount = itemCounts.getOrDefault('B', 0);

        // Apply special offer for item 'E': buy 2 E's, get one B free
        int eCount = itemCounts.getOrDefault('E', 0);

        total += prices.get('E') * eCount;
        if (bCount > 0) {
            if (eCount >= 2 && bCount >= 1) bCount--;
            else if (eCount >= 4 && bCount >= 2) bCount -= 2;
            else if (eCount >= 6 && bCount >= 3) bCount -= 3;
            else if (eCount >= 8 && bCount >= 4) bCount -= 4;
        }

        // Apply special offer for SKU 'B'

        int offerQuantityB = 2;
        int offerPriceB = 45;
        total += (bCount / offerQuantityB) * offerPriceB + (bCount % offerQuantityB) * prices.get('B');


        // Calculate total for remaining items
        for (Map.Entry<Character, Integer> entry : itemCounts.entrySet()) {
            char sku = entry.getKey();
            int count = entry.getValue();
            if (sku != 'A' && sku != 'B' && sku != 'E') {
                total += count * prices.get(sku);
            }
        }

        return total;

    }
}
