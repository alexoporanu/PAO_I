import Account.Account;
import Account.PersonalNeedsAccount;
import Account.SavingsAccount;
import Card.Card;
import Card.Generators;
import Client.Client;
import Client.Address;
import Transaction.Transaction;

import java.text.ParseException;
import java.util.*;

public class Service {
    public List<Client> clients = new ArrayList<>();
    private List<PersonalNeedsAccount> personalNeedsAccounts = new ArrayList<>();
    private List<SavingsAccount> savingsAccounts = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    private HashSet<String> IBANS = new HashSet<String>();
    private Map<String, Account> accountsMap = new HashMap<>();

    public Service() {
    }
    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<PersonalNeedsAccount> getAccounts() {
        return personalNeedsAccounts;
    }

    public void setAccounts(List<PersonalNeedsAccount> accounts) {
        this.personalNeedsAccounts = accounts;
    }

    public List<SavingsAccount> getSavingsAccounts() {
        return savingsAccounts;
    }

    public void setSavingsAccounts(List<SavingsAccount> savingsAccounts) {
        this.savingsAccounts = savingsAccounts;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addClient(Scanner in) throws ParseException {
        Client c = new Client();
        c.read(in);
        clients.add(c);
    }

    public void showAllClients() {
        for (int i = 0; i < clients.size(); i++) {
            System.out.println(clients.get(i));
            System.out.println("\n\n");
        }
    }

    public void showAccounts() {
        System.out.println("Personal needs accounts:\n");
        for(PersonalNeedsAccount acc: personalNeedsAccounts) {
            System.out.println(acc);
        }

        System.out.println("Savings accounts:\n");
        for(SavingsAccount acc: savingsAccounts) {
            System.out.println(acc);
        }
    }

    public Client getClientById(int id) throws Exception {
        if(id > clients.size()) {
            throw new Exception("There isn't any client with this ID");
        }
        return this.clients.get(id - 1);
    }

    public void addNullClient() {
        this.clients.add(new Client());
    }

    public void addPersonalNeedsAccount(int clientID, Scanner in) throws Exception {
        if(clientID > clients.size()) {
            throw new Exception("There isn't any client with this ID");
        }
        PersonalNeedsAccount Acc = new PersonalNeedsAccount(clientID);
        Acc.read(in);
        if(IBANS.contains(Acc.getIBAN())) {
            throw new Exception("There can't be two accounts with the same IBAN\n");
        }
        IBANS.add(Acc.getIBAN());
        this.personalNeedsAccounts.add(Acc);
    }

    public void addSavingsAccount(int clientID, Scanner in) throws Exception {
        if(clientID > clients.size()) {
            throw new Exception("There isn't any client with this ID");
        }
        SavingsAccount Acc = new SavingsAccount(clientID);
        Acc.read(in);
        if(IBANS.contains(Acc.getIBAN())) {
            throw new Exception("There can't be two accounts with the same IBAN\n");
        }
        IBANS.add(Acc.getIBAN());
        this.savingsAccounts.add(Acc);
    }

    public int getTotalMoneyInEuro(int clientID) {
        int toReturn = 0;
        for(int i = 0; i < personalNeedsAccounts.size(); ++i) {
            PersonalNeedsAccount crtAcc = personalNeedsAccounts.get(i);
            if (crtAcc.getCustomerID() == clientID) {
                if (crtAcc.getCurrency().equals("EURO"))
                    toReturn += crtAcc.getBalance();
                else if (crtAcc.getCurrency().equals("LEI"))
                    toReturn += crtAcc.getBalance() * 0.2;
                else if (crtAcc.getCurrency().equals("DOLAR"))
                    toReturn += crtAcc.getBalance() * 0.9;
            }
        }

        for(int i = 0; i < savingsAccounts.size(); ++i) {
            SavingsAccount crtAcc = savingsAccounts.get(i);
            if (crtAcc.getCustomerID() == clientID) {
                if (crtAcc.getCurrency().equals("EURO"))
                    toReturn += crtAcc.getBalance();
                else if (crtAcc.getCurrency().equals("LEU"))
                    toReturn += crtAcc.getBalance() * 0.2;
                else if (crtAcc.getCurrency().equals("DOLAR"))
                    toReturn += crtAcc.getBalance() * 0.9;
            }
        }

        return toReturn;
    }

    public void createClientCard(int customerID, String IBAN) throws Exception {
        boolean ok = false;
        for(PersonalNeedsAccount acc: personalNeedsAccounts) {
            if (acc.getCustomerID() == customerID && acc.getIBAN().equals(IBAN)) {
                Card card = Generators.randomCardGenerator();
                acc.addCard(card);
                ok = true;
            }
        }
        for(SavingsAccount acc: savingsAccounts) {
            if (acc.getCustomerID() == customerID && acc.getIBAN().equals(IBAN)) {
                Card card = Generators.randomCardGenerator();
                acc.addCard(card);
                ok = true;
            }
        }
        if(ok == false) {
            throw new Exception("No account found!\n");
        }

    }

    public void showCards(int customerID) {
        List<Card> L = new ArrayList<>();
        for(PersonalNeedsAccount acc: personalNeedsAccounts) {
            if (acc.getCustomerID() == customerID) {
                L.addAll(acc.getCards());
            }
        }
        for(SavingsAccount acc: savingsAccounts) {
            if (acc.getCustomerID() == customerID) {
                L.addAll(acc.getCards());
            }
        }

        for(int i = 0; i < L.size(); ++i) {
            System.out.println(L.get(i));
        }
    }

    public void mapAccounts(){
        for(PersonalNeedsAccount account: this.personalNeedsAccounts)
            this.accountsMap.put(account.getIBAN(), account);
        for(SavingsAccount account: this.savingsAccounts)
            this.accountsMap.put(account.getIBAN(), account);
    }

    public void createTransaction(Scanner in) throws Exception {
        System.out.println("Source Account: ");
        String srcIBAN = in.nextLine();
        System.out.println("Destination Account: ");
        String dstIBAN = in.nextLine();
        System.out.println("Transfered Money: ");
        int amount = in.nextInt();
        Account srcAcc = null, dstAcc = null;

        if(accountsMap.containsKey(srcIBAN))
            srcAcc = accountsMap.get(srcIBAN);
        if(accountsMap.containsKey(dstIBAN))
            dstAcc = accountsMap.get(dstIBAN);

        if(srcAcc.getBalance() < amount)
            throw new Exception("Insufficient founds!");

        srcAcc.setBalance(srcAcc.getBalance() - amount);
        dstAcc.setBalance(dstAcc.getBalance() + amount);

        Transaction newTransaction = new Transaction(amount, srcAcc.getCurrency(), srcIBAN, dstIBAN, new Date());
        this.transactions.add(newTransaction);
    }
}




