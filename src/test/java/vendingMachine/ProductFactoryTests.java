package vendingMachine;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class ProductFactoryTests {

    @Test
    public void verify_createCoke_creates_coke_product() {
        final Product coke = ProductFactory.createCoke();
        Assert.assertThat(coke.getName(), Is.is(ProductNames.Coke));
        Assert.assertThat(coke.getPrice(), Is.is(25));
    }

    @Test
    public void verify_createPepsi_creates_pepsi_product() {
        final Product pepsi = ProductFactory.createPepsi();
        Assert.assertThat(pepsi.getName(), Is.is(ProductNames.Pepsi));
        Assert.assertThat(pepsi.getPrice(), Is.is(35));
    }

    @Test
    public void verify_createSoda_creates_soda_product() {
        final Product soda = ProductFactory.createSoda();
        Assert.assertThat(soda.getName(), Is.is(ProductNames.Soda));
        Assert.assertThat(soda.getPrice(), Is.is(45));
    }
}
