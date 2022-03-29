package Account;

import java.util.Scanner;

public class PersonalNeedsAccount extends Account {
    private String need;

    PersonalNeedsAccount(String IBAN, int balance, String currency, int customerID, String need) {
        super(IBAN, balance, currency, customerID);
        this.need = need;
    }

    public PersonalNeedsAccount(int customerID) {
        super(customerID);
    }

    public void read(Scanner scanner) {
        super.read(scanner);
        System.out.println("Need: ");
        this.need = scanner.nextLine();
    }

    @Override
    public String toString() {
        String toReturn = super.toString();
        toReturn += "\nNeed: ";
        toReturn += need;
        return toReturn;
    }
}
