package vendingMachine;

public class DefaultUserConfirmation implements  UserConfirmation {

    private boolean hasUserConfirm;

    @Override
    public boolean confirmOrCancel(String name, int items, int change) {
        return hasUserConfirm;
    }

    public void setHasUserConfirm(boolean hasUserConfirm) {
        this.hasUserConfirm = hasUserConfirm;
    }
}
