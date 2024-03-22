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

        Map<Character, SpecialOffer> specialOffers = new HashMap<>();
        specialOffers.put('A', new SpecialOffer(3, 130));
        specialOffers.put('Z', new SpecialOffer(5, 200)); // Use separate key for second offer of A
        specialOffers.put('B', new SpecialOffer(2, 45));
        specialOffers.put('E', new SpecialOffer(2, 0)); // Special offer for item E

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

        // Apply special offers and calculate total checkout value
        for (Map.Entry<Character, Integer> entry : itemCounts.entrySet()) {
            char sku = entry.getKey();
            int count = entry.getValue();
            int price = prices.get(sku);
            int specialPrice = calculateSpecialPrice(count, price, specialOffers.getOrDefault(sku, new SpecialOffer(1, price)), itemCounts, prices);
            total += specialPrice;
        }

        return total;
    }

    private int calculateSpecialPrice(int count, int price, SpecialOffer specialOffer, Map<Character, Integer> itemCounts, Map<Character, Integer> prices) {
        int quantity = specialOffer.getQuantity();
        int offerPrice = specialOffer.getOfferPrice();

        if (offerPrice == 0) { // Special offer for item E: buy 2 E's, get one B free
            int eCount = itemCounts.getOrDefault('E', 0);
            int freeBs = Math.min(count, eCount / quantity); // Limit free B's by available E's
            return count * price - freeBs * prices.get('B'); // Get the price of 'B'
        } else { // Other special offers
            int specials = count / quantity;
            int remaining = count % quantity;
            return specials * offerPrice + remaining * price;
        }
    }

    private static class SpecialOffer {
        private int quantity;
        private int offerPrice;

        public SpecialOffer(int quantity, int offerPrice) {
            this.quantity = quantity;
            this.offerPrice = offerPrice;
        }

        public int getQuantity() {
            return quantity;
        }

        public int getOfferPrice() {
            return offerPrice;
        }
    }
}


