package vendingMachine;

public interface VendingProduct {

    int getBalance();

    int getSold();

    Product getProduct();

    boolean vend();

    int getPrice();

    String getName();
}
