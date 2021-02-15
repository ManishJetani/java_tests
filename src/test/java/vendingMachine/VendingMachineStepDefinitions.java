package vendingMachine;

import io.cucumber.java.en.*;
import org.hamcrest.core.Is;
import org.junit.Assert;

public class VendingMachineStepDefinitions {
    private VendingMachine vendingMachine;
    private int refund = 0;
    private VendSuccess vendResult;

    @Given("machine is ready")
    public void machine_is_ready() {
        vendingMachine = DefaultVendingMachine.createDefault();
    }

    @When("inserts following coins")
    public void user_insert_following_coins(io.cucumber.datatable.DataTable dataTable) {
        dataTable.asList().stream().map(x -> Integer.parseInt(x)).forEach(x -> {
            vendingMachine.payCoin(x);
        });
    }

    @Then("user should see total {int} paid")
    public void user_should_see_total_paid(Integer total) {
        Assert.assertThat(vendingMachine.getPaidTotal(), Is.is(total));
    }

    @When("user press {int} on machine")
    public void user_press_on_product(Integer selection) {
        vendingMachine.selectProduct(selection);
    }

    @Then("product is {string} and {string} displayed")
    public void productIsAndDisplayed(String expectedProduct, String expectedPrice) {
        Product product = vendingMachine.getSelectedProduct();
        Assert.assertThat(product.getName(), Is.is(expectedProduct));
        Assert.assertThat(product.getPrice(), Is.is(Integer.parseInt(expectedPrice)));
    }

    @And("cancels request")
    public void cancelsRequest() {
        refund = vendingMachine.cancelRequest();
    }

    @Then("machine refunds {int} cents")
    public void machineRefundsCents(int expectedRefund) {
        Assert.assertThat(refund, Is.is(expectedRefund));
    }

    @And("confirm request")
    public void confirmRequest() {
        vendResult = vendingMachine.confirmRequest();
    }

    @Then("machine returns {string} and <{int}> cent change")
    public void machineReturnsAndCentChange(String expectedProduct, int expectedChange) {
        Assert.assertThat(vendResult.getProduct().getName(), Is.is(expectedProduct));
        Assert.assertThat(vendResult.getChange(), Is.is(expectedChange));
    }

    @But("supplier reset it")
    public void supplierResetIt() {
        vendingMachine.reset();
    }

    @Then("it gets reset")
    public void itGetsReset() {
        Assert.assertNull(vendingMachine.getSelectedProduct());
        Assert.assertThat(vendingMachine.getPaidTotal(), Is.is(0));
    }
}
