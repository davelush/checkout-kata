package io.digitalsandpit.checkout;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PricingRuleTest {

    @Test
    public void hasOfferTest() {
        PricingRule rule = new PricingRule(new Item("A", 47), new Offer(5, 200, 70));
        assertTrue(rule.hasOffer());
    }

    @Test
    public void doesNotHaveOfferTest() {
        PricingRule rule = new PricingRule(new Item("A", 47), null);
        assertFalse(rule.hasOffer());
    }

    @Test
    public void travisNudgeTest() {

    }
}
