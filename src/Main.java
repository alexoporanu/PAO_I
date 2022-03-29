import Account.PersonalNeedsAccount;
import Account.SavingsAccount;
import Card.Card;
import Client.Address;
import Client.Client;
import Transaction.Transaction;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) throws Exception {

       Scanner scanner = new Scanner(System.in);

        Service S = new Service();
        S.addClient(scanner);
        S.addSavingsAccount(1, scanner);
        S.addPersonalNeedsAccount(1, scanner);

        S.mapAccounts();

        S.createTransaction(scanner);

        S.getTotalMoneyInEuro(1);

        S.showAccounts();

        S.createClientCard(1, "");
        S.showCards(1);
        S.getClientById(1);

    }
}
