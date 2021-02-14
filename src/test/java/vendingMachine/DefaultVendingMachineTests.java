package vendingMachine;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DefaultVendingMachineTests {
    private VendingMachine vendingMachine;

    @Before
    public void setup() {
        vendingMachine = DefaultVendingMachine.createDefault();
    }

    @Test
    public void verifies_returns_available_products() {
        List<Product> productList = vendingMachine.getProducts();

        Assert.assertThat(productList.size(), Is.is(3));

        Assert.assertTrue(productList.stream().anyMatch(x -> x.getName().equals(ProductNames.Coke)));
        Assert.assertTrue(productList.stream().anyMatch(x -> x.getName().equals(ProductNames.Pepsi)));
        Assert.assertTrue(productList.stream().anyMatch(x -> x.getName().equals(ProductNames.Soda)));
    }

    @Test
    public void it_does_accepts_valid_coins_but_does_not_accept_invalid_coin() {

        vendingMachine.payCoin(1)
                .payCoin(5)
                .payCoin(10)
                .payCoin(25);

        //Accepts valid coins
        Assert.assertTrue(vendingMachine.getPaidTotal() == 41);

        vendingMachine.payCoin(2).payCoin(50);
        //does not accept invalid coin
        Assert.assertTrue(vendingMachine.getPaidTotal() == 41);
    }

    @Test
    public void allows_user_to_select_product() {
        Product product = vendingMachine.selectProduct(2);

        Assert.assertTrue(product.equals(ProductFactory.createSoda()));
    }

    @Test
    public void allows_user_to_cancel_request_and_get_refund() {
        vendingMachine.payCoin(25).payCoin(25);
        vendingMachine.selectProduct(1);

        Assert.assertThat(vendingMachine.getSelectedProduct(), Is.is(ProductFactory.createPepsi()));
        Assert.assertThat(vendingMachine.getPaidTotal(), Is.is(50));

        int refund = vendingMachine.cancelRequest();
        Assert.assertThat(refund, Is.is(50));

        Assert.assertNull(vendingMachine.getSelectedProduct());
        Assert.assertThat(vendingMachine.getPaidTotal(), Is.is(0));
    }

    @Test
    public void returns_selected_product_and_remaining_change () {
        //pay 2 quarter
        vendingMachine.payCoin(25).payCoin(25);
        //Select soda
        vendingMachine.selectProduct(2);

        VendSuccess vendResult = vendingMachine.confirmRequest();

        Assert.assertThat(vendResult.getProduct(), Is.is(ProductFactory.createSoda()));
        Assert.assertThat(vendResult.getChange(), Is.is(5));
    }

    @Test
    public void it_allows_reset_operation () {
        //pay 2 quarter
        vendingMachine.payCoin(25).payCoin(25);
        //Select soda
        vendingMachine.selectProduct(2);

        vendingMachine.reset();

        Assert.assertNull(vendingMachine.getSelectedProduct());
        Assert.assertThat(vendingMachine.getPaidTotal(), Is.is(0));
    }
}
