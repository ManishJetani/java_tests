package vendingMachine;

public final class VendSuccess {
    private Product product;
    private int change;

    public VendSuccess(Product product, int change) {
        this.product = product;
        this.change = change;
    }

    public Product getProduct() {
        return product;
    }

    public int getChange() {
        return change;
    }
}
