package doubleLetters;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class DoubleLetterCountStepDefinitions {
    int actualCounts;
    @Given("when passed {string}")
    public void when_passed(String input) {
        actualCounts = StringOperations.getDoubleLetterCounts(input);
    }

    @Then("it returns {int}")
    public void it_returns(int expectedCount) {

        Assert.assertEquals(actualCounts, expectedCount);
    }
}
