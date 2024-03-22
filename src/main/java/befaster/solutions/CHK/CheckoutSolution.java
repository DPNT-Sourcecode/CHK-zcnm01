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

        Map<Character, SpecialOffer[]> specialOffers = new HashMap<>();
        specialOffers.put('A', new SpecialOffer[]{new SpecialOffer(3, 130), new SpecialOffer(5, 200)});
        specialOffers.put('B', new SpecialOffer[]{new SpecialOffer(2, 45)});
        specialOffers.put('E', new SpecialOffer[]{new SpecialOffer(2, 0)}); // Special offer for item E

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
            total += calculateSpecialPrice(count, price, specialOffers.getOrDefault(sku, new SpecialOffer[]{new SpecialOffer(1, price)}));
        }

        return total;
    }

    private int calculateSpecialPrice(int count, int price, SpecialOffer[] specialOffers) {
        int minTotal = count * price;
        for (SpecialOffer specialOffer : specialOffers) {
            int quantity = specialOffer.getQuantity();
            int offerPrice = specialOffer.getOfferPrice();
            if (count >= quantity) {
                int specialTotal = (count / quantity) * offerPrice + (count % quantity) * price;
                minTotal = Math.min(minTotal, specialTotal);
            }
        }
        return minTotal;
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
