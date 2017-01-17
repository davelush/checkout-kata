package io.digitalsandpit.checkout;

import io.digitalsandpit.checkout.exceptions.InvalidSkuException;
import io.digitalsandpit.checkout.exceptions.KeyMustMatchItemSkuException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CheckoutTest {
    private Checkout checkout;


    @Before
    public void setup() {
        Map<String, PricingRule> pricingRules = new HashMap<>();
        pricingRules.put("A", new PricingRule(new Item("A", 50), new Offer(3, 130, 20)));
        pricingRules.put("B", new PricingRule(new Item("B", 30), new Offer(2, 45, 15)));
        pricingRules.put("C", new PricingRule(new Item("C", 20), null));
        pricingRules.put("D", new PricingRule(new Item("D", 15), null));

        checkout = new Checkout(pricingRules);
    }

    @Test
    public void scanItemA() {
        checkout.scan("A");
        assertEquals("itemA not scanning successfully", 50, checkout.getTotal());
    }

    @Test
    public void scanItemB() {
        checkout.scan("B");
        assertEquals("itemB not scanning successfully", 30, checkout.getTotal());
    }

    @Test
    public void scanTwoItemsTest() {
        checkout.scan("B");
        checkout.scan("A");
        assertEquals("itemA and itemB not totalling correctly", 80, checkout.getTotal());
    }

    @Test
    public void itemABogofTest() {
        for (int i=0; i < 3; i++) {
            checkout.scan("A");
        }
        assertEquals("item A offer not being applied", 130, checkout.getTotal());
    }

    @Test
    public void itemABogofPlusACoupleMoreTest() {
        for (int i=0; i < 5; i++) {
            checkout.scan("A");
        }
        // 3 x A discounted = 130
        // 2 x A = 100
        // total = 230
        assertEquals("Not correctly applying an offer plus extras ", 230, checkout.getTotal());
    }

    @Test
    public void multipleOffersTest() {
        for (int i=0; i < 5; i++) {
            checkout.scan("A");
            checkout.scan("B");
            checkout.scan("C");
        }
        // 3 x A discounted = 130
        // 2 x A = 100
        // 4 x B discounted = 90
        // 1 x B = 30
        // 5 x C = 100
        // total = 450
        assertEquals("Combination of offers not working", 450, checkout.getTotal());
    }

    @Test(expected= InvalidSkuException.class)
    public void invalidSkuTest() {
        checkout.scan("E");
    }

    @Test(expected=InvalidSkuException.class)
    public void nullSkuTest() {
        checkout.scan(null);
    }

    @Test(expected= KeyMustMatchItemSkuException.class)
    public void keyNotMatchingRuleSkuTest() {
        Map<String, PricingRule> rules = new HashMap<>();
        rules.put("A", new PricingRule(new Item("B", 0), null));
        new Checkout(rules);
    }
}
