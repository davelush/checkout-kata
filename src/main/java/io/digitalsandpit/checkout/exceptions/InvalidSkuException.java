package io.digitalsandpit.checkout.exceptions;

/**
 * This represents a programming error where an invalid sku was presented
 */
public class InvalidSkuException extends RuntimeException {

    public InvalidSkuException() {
        super("You must provide an SKU that is part of the pricing rules");
    }
}
