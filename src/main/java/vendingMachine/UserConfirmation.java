package vendingMachine;

public interface UserConfirmation {
    boolean confirmOrCancel (String name, int items, int change);
}
