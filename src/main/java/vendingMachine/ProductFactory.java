package vendingMachine;

public class ProductFactory {
    public static Product createProduct(String name, int price) {
        return new Product(name, price);
    }

    public static Product createCoke() {
        return createProduct(ProductNames.Coke, 25);
    }

    public static Product createPepsi() {
        return createProduct(ProductNames.Pepsi, 35);
    }

    public static Product createSoda() {
        return createProduct(ProductNames.Soda, 45);
    }
}
