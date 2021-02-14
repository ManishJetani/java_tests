package vendingMachine;

public class DefaultVendingProduct implements VendingProduct {
    private int balance;
    private int sold;

    public int getBalance() {
        return balance;
    }

    public int getSold() {
        return sold;
    }

    public Product getProduct() {
        return product;
    }

    private Product product;

    public DefaultVendingProduct(int balance, int sold, Product product) {
        this.balance = balance;
        this.sold = sold;
        this.product = product;
    }

    public boolean vend() {
        if (balance > 0) {
            balance -= 1;
            sold += 1;
            return true;
        }
        return false;
    }

    @Override
    public int getPrice() {
        return product.getPrice();
    }

    @Override
    public String getName() {
        return product.getName();
    }
}

