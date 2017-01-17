package io.digitalsandpit.checkout;

/**
 * Offer relating to purchasing multiples of @see io.digitalsandpit.checkout.Item
 */
public class Offer {

    private final int count;            // A count of the number of items needed to receive the discount
    private final int discountAmount;   // The amount that is discounted from the bill if you scan enough items

    public Offer(int count, int offerPrice, int discountAmount) {
        this.count = count;
        this.discountAmount = discountAmount;
    }

    public int getCount() {
        return count;
    }

    public int getDiscountAmount() {
        return this.discountAmount;
    }
}
