package vendingMachine;

import org.junit.Assert;
import org.junit.Test;

public class CoinFactoryTests {

    @Test
    public void it_creates_penny_coin_with_correct_name_and_value() {
        Coin penny = CoinFactory.createPenny();
        Assert.assertTrue(penny.getName().equals("Penny") && penny.getValue() == 1);
    }

    @Test
    public void it_creates_nickel_coin_with_correct_name_and_value() {
        Coin nickel = CoinFactory.createNickel();
        Assert.assertTrue(nickel.getName().equals("Nickel") && nickel.getValue() == 5);
    }

    @Test
    public void it_creates_dime_coin_with_correct_name_and_value() {
        Coin dime = CoinFactory.createDime();
        Assert.assertTrue(dime.getName().equals("Dime") && dime.getValue() == 10);
    }

    public void it_creates_quarter_cion_with_correct_name_and_value() {
        Coin quarter = CoinFactory.createQuarter();
        Assert.assertTrue(quarter.getName().equals("Quarter") && quarter.getValue() == 25);
    }
}
