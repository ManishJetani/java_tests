package vendingMachine;

import java.util.List;

public interface VendingMachine {
    List<Coin> getAcceptableCoins();
    VendingMachine payCoin (int value);
    int getPaidTotal();
    Product selectProduct(int index);
    Product getSelectedProduct();
    void cancelRequest();
    VendSuccess confirmRequest();
    VendingMachine reset();
    List<Product> getProducts();
}


