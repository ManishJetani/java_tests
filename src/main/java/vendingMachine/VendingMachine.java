package vendingMachine;

import java.util.List;

public interface VendingMachine {
    VendingMachine payCoin (int value);
    int getPaidTotal();
    Product selectProduct(int index);
    Product getSelectedProduct();
    int cancelRequest();
    VendSuccess confirmRequest();
    VendingMachine reset();
    List<Product> getProducts();
}


