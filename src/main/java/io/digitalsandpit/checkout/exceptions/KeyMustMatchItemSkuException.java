package io.digitalsandpit.checkout.exceptions;

public class KeyMustMatchItemSkuException extends RuntimeException {

    public KeyMustMatchItemSkuException() {
        super("The key you pass into the PricingRules map must match the SKU of the Item");
    }
}
