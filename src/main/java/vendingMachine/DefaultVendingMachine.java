package vendingMachine;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

public class DefaultVendingMachine implements VendingMachine {
    private List<VendingProduct> products;
    private VendingProduct selectedProduct;
    private List<Coin> acceptableCoins;
    private int totalReceived = 0;

    public DefaultVendingMachine(List<VendingProduct> products, List<Coin> acceptableCoins) {
        this.selectedProduct = null;
        this.products = products;
        this.acceptableCoins = acceptableCoins;
    }

    public VendingMachine payCoin (int value) {
        if (acceptableCoins.stream().anyMatch(x -> x.getValue() == value)) {
            totalReceived += value;
        }
        return this;
    }

    @Override
    public int getPaidTotal() {
        return totalReceived;
    }

    @Override
    public VendSuccess confirmRequest () {

        if (selectedProduct != null) {
            int price = selectedProduct.getProduct().getPrice();
            // check balance and amount received
            if (selectedProduct.getBalance() > 0 && price < totalReceived) {
                selectedProduct.vend();

                VendSuccess result = new VendSuccess(selectedProduct.getProduct(), totalReceived - price);
                clearSelection();
                return result;
            }
        }
        VendSuccess result = new VendSuccess(null, totalReceived);
        clearSelection();
        return result;
    }

    @Override
    public VendingMachine reset() {
        clearSelection();
        return this;
    }

    public List<Product> getProducts() {
        return products.stream().map(x -> x.getProduct()).collect(Collectors.toList());
    }

    @Override
    public Product selectProduct(int index) {
        if (index >= 0 && index < products.size()) {
            this.selectedProduct = products.get(index);
            return this.selectedProduct.getProduct();
        }
        return null;
    }

    private void clearSelection() {
        this.selectedProduct = null;
        this.totalReceived = 0;
    }

    @Override
    public Product getSelectedProduct() {
        if (selectedProduct == null) {
            return null;
        }
        return selectedProduct.getProduct();
    }

    @Override
    public int cancelRequest() {
        int refund = totalReceived;
        clearSelection();
        return refund;
    }

    public static VendingMachine createDefault() {
        VendingProduct coke = new DefaultVendingProduct(10, 0, ProductFactory.createCoke());
        VendingProduct pepsi = new DefaultVendingProduct(10, 0, ProductFactory.createPepsi());
        VendingProduct soda = new DefaultVendingProduct(10, 0, ProductFactory.createSoda());

        ArrayList<VendingProduct> products = new ArrayList<>();
        products.add(coke);
        products.add(pepsi);
        products.add(soda);

        VendingMachine vendingMachine = new DefaultVendingMachine(products, CoinFactory.createUSAcceptableVendingCoins());
        return vendingMachine;
    }
}
