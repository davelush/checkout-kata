package io.digitalsandpit.checkout;

/**
 * Item to be scanned and sold at checkout
 */
public class Item {

    private final String sku;       // the stock keeping unit
    private final int unitPrice;    // price in minor units (e.g. pence)

    public Item(String sku, int unitPrice) {
        this.sku = sku;
        this.unitPrice = unitPrice;
    }

    public String getSku() {
        return sku;
    }

    public int getUnitPrice() {
        return unitPrice;
    }
}
