package vendingMachine;

import java.util.Arrays;
import java.util.List;

public class CoinFactory {

    public static Coin createPenny () {
        return new Coin("Penny", 1);
    }

    public static Coin createNickel () {
        return new Coin("Nickel", 5);
    }

    public static Coin createDime () {
        return new Coin("Dime", 10);
    }

    public static Coin createQuarter () {
        return new Coin("Quarter", 25);
    }

    public static List<Coin> createUSAcceptableVendingCoins() {
        return Arrays.asList(new Coin[]{createPenny(), createNickel(), createDime(), createQuarter()});
    }
}
