package io.digitalsandpit.checkout;

import io.digitalsandpit.checkout.exceptions.InvalidSkuException;
import io.digitalsandpit.checkout.exceptions.KeyMustMatchItemSkuException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents a checkout session with a number of Items scanned by sku
 */
public class Checkout {

    private int total = 0;
    private Map<String, PricingRule> pricingRules;
    private Map<String, AtomicInteger> itemCounters = new HashMap<>();

    /**
     * Constructor that accepts a set of pricing rules. If you don't supply any rules you won't
     * be able to scan anything during this Checkout session
     *
     * @param pricingRules A Map of the pricing rules
     */
    public Checkout(Map<String, PricingRule> pricingRules) {
        this.pricingRules = pricingRules;

        pricingRules.forEach((key, rule) -> {
            if (!key.equals(rule.getItem().getSku())) {
                throw new KeyMustMatchItemSkuException();
            }
            if (rule.hasOffer()) {
                itemCounters.put(key, new AtomicInteger());
            }
        });
    }

    /**
     * Scans a new Item based on it's SKU. If you didn't add a rule for this SKU, you won't
     * be able to scan it (instead you'll get an InvalidSkuException at runtime).
     *
     * @param sku The stock keeping unit
     */
    public void scan(String sku) {

        if (!pricingRules.containsKey(sku)) {
            throw new InvalidSkuException();
        }

        PricingRule rule = pricingRules.get(sku);
        total += rule.getItem().getUnitPrice();

        if (pricingRules.containsKey(sku) && pricingRules.get(sku).hasOffer()) {

            Offer validOffer = pricingRules.get(sku).getOffer();
            Integer itemCount = itemCounters.get(sku).incrementAndGet();
            Integer offerCount = validOffer.getCount();

            if (itemCount % offerCount == 0) {
                total = total - validOffer.getDiscountAmount();
            }
        }
    }

    /**
     * @return The monetary value of the overall checkout session
     */
    public int getTotal() {
        return total;
    }
}
