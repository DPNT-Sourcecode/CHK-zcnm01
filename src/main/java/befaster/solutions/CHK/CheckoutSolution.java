package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;
import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        Map <Character, Integer> prices = new HashMap<>();
        prices.put('A',50);
        prices.put('B',30);
        prices.put('C',20);
        prices.put('D',55);

        Map<Character, SpecialOffer> specialOffers = new HashMap<>();
        specialOffers.put('A', new SpecialOffer(3, 130));
        specialOffers.put('B', new SpecialOffer(2, 45));

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

        // Calculate total checkout value
        for (Map.Entry<Character, Integer> entry : itemCounts.entrySet()) {
            char sku = entry.getKey();
            int count = entry.getValue();
            int price = prices.get(sku);
            int specialPrice = specialOffers.containsKey(sku) ? specialOffers.get(sku).calculatePrice(count, price) : price * count;
            total += specialPrice;
        }

        return total;
    }

    private static class SpecialOffer {
        private int quantity;
        private int offerPrice;

        public SpecialOffer(int quantity, int offerPrice) {
            this.quantity = quantity;
            this.offerPrice = offerPrice;
        }

        public int calculatePrice(int totalCount, int price) {
            int specials = totalCount / quantity;
            int remaining = totalCount % quantity;
            return specials * offerPrice + remaining * price;
        }
    }
}



