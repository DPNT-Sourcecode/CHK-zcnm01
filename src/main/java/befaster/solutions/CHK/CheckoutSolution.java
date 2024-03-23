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
        prices.put('F', 10);
        prices.put('G', 20);
        prices.put('H', 10);
        prices.put('I', 35);
        prices.put('J', 60);
        prices.put('K', 80);
        prices.put('L', 90);
        prices.put('M', 15);
        prices.put('N', 40);
        prices.put('O', 10);
        prices.put('P', 50);
        prices.put('Q', 30);
        prices.put('R', 50);
        prices.put('S', 30);
        prices.put('T', 20);
        prices.put('U', 40);
        prices.put('V', 50);
        prices.put('W', 20);
        prices.put('X', 90);
        prices.put('Y', 10);
        prices.put('Z', 50);

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
/*

        // Apply special offer for SKU 'A' and calculate total checkout value
        int aCount = itemCounts.getOrDefault('A', 0);
        int offerPriceA3 = 130;
        int offerPriceA5 = 200;
        total += (aCount / 5) * offerPriceA5 + ((aCount % 5) / 3) * offerPriceA3 + ((aCount % 5) % 3) * prices.get('A');

*/
        int bCount = itemCounts.getOrDefault('B', 0);

        // Apply special offer for item 'E': buy 2 E's, get one B free
        int eCount = itemCounts.getOrDefault('E', 0);

        total += prices.get('E') * eCount;
        if (bCount > 0) {
            if (eCount == 2 && bCount >= 1 || eCount==3 && bCount>=1) bCount--;
            else if (eCount >= 4 && bCount >= 2) bCount -= 2;
            else if (eCount >= 6 && bCount >= 3) bCount -= 3;
            else if (eCount >= 8 && bCount >= 4) bCount -= 4;
        }

        // Apply special offer for SKU 'B'

        int offerQuantityB = 2;
        int offerPriceB = 45;
        total += (bCount / offerQuantityB) * offerPriceB + (bCount % offerQuantityB) * prices.get('B');
 /*
        // Apply special offer for item 'F': buy 2 F's, get one F free
        int fCount = itemCounts.getOrDefault('F', 0);
        int freeFs = fCount / 3;
        total += (fCount - freeFs) * prices.get('F');

      // Calculate total for remaining items
        for (Map.Entry<Character, Integer> entry : itemCounts.entrySet()) {
            char sku = entry.getKey();
            int count = entry.getValue();
            if (sku != 'A' && sku != 'B' && sku != 'E' && sku != 'F') {
                total += count * prices.get(sku);
            }
        }
*/
        for (char sku : itemCounts.keySet()) {
            int count = itemCounts.get(sku);
            switch (sku) {
                case 'A':
                    int offerPriceA3 = 130;
                    int offerPriceA5 = 200;
                    total += (count / 5) * offerPriceA5 + ((count % 5) / 3) * offerPriceA3 + ((count % 5) % 3) * prices.get('A');
                    break;
                case 'B':
                    break;
                case 'E':
                    break;
                case 'F':
                    int freeFs = count / 3;
                    total += (count - freeFs) * prices.get('F');
                    break;
                case 'H':
                    int offerPriceH5 = 45;
                    int offerPriceH10 = 80;
                    total += (count / 10) * offerPriceH10 + ((count % 10) / 5) * offerPriceH5 + ((count % 10) % 5) * prices.get('H');
                    break;
                case 'K':
                    int offerQuantityK = 2;
                    int offerPriceK = 150;
                    total += (count / offerQuantityK) * offerPriceK + (count % offerQuantityK) * prices.get('K');
                    break;
                case 'N':
                    int freeMs = count / 3;
                    total += (count - freeMs) * prices.get('N');
                    break;
                case 'P':
                    int offerPriceP5 = 200;
                    total += (count / 5) * offerPriceP5 + (count % 5) * prices.get('P');
                    break;
                case 'Q':
                    int offerQuantityQ = 3;
                    int offerPriceQ = 80;
                    total += (count / offerQuantityQ) * offerPriceQ + (count % offerQuantityQ) * prices.get('Q');
                    break;
                case 'R':
                    int freeQs = count / 3;
                    total += (count - freeQs) * prices.get('R');
                    // Subtract the price of free Qs
                    total -= Math.min(freeQs, itemCounts.getOrDefault('Q', 0)) * prices.get('Q');
                    break;
                case 'U':
                    int freeUs = count / 3;
                    total += (count - freeUs) * prices.get('U');
                    break;
                case 'V':
                    int offerPriceV2 = 90;
                    int offerPriceV3 = 130;
                    total += (count / 3) * offerPriceV3 + ((count % 3) / 2) * offerPriceV2 + ((count % 3) % 2) * prices.get('V');
                    break;
                default:
                    total += count * prices.get(sku);
            }
        }

        return total;

    }
}


