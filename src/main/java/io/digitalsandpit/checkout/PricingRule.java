package io.digitalsandpit.checkout;

/**
 * An aggregation of both an Item and an associated offer
 */
public class PricingRule {
    private final Item item;
    private final Offer offer;

    public PricingRule(Item item, Offer offer) {
        this.item = item;
        this.offer = offer;
    }

    public Item getItem() {
        return item;
    }

    public Offer getOffer() {
        return offer;
    }

    public boolean hasOffer() {
        return offer != null;
    }
}
