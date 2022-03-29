package Client;

import Account.Account;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Client {
    private int ID;
    private String SEX;
    private String firstName, lastName;
    private String email;
    private String phoneNumber;
    private Date birthDate;
    private String CNP;
    private Address address;
    public static int StaticID;
    static {
        StaticID = 0;
    }

    {
        ++StaticID;
    }
    public Client() {
        this.ID = StaticID;
        this.birthDate = new Date();
    }
    public Client(String SEX, String firstName, String lastName, String email, String phoneNumber, Date birthDate, String CNP) {
        this.ID = StaticID;
        this.SEX = SEX;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.CNP = CNP;
    }

    @Override
    public String toString() {
        String toReturn = "";
        toReturn += "Client ID: " + ID + "\n";
        toReturn += "First name: " + firstName + "\n";
        toReturn += "Last name: " + lastName + "\n";
        toReturn += "Phone number: " + phoneNumber + "\n";
        toReturn += "Email: " + email + "\n";
        toReturn += "Birthdate: " + birthDate.toString() + "\n";
        return toReturn;
    }

    public static void main(String[] args) {
        Client A = new Client("M", "Alexandru", "Oporanu","alexoporanu11@gmail.com", "0752186071", new Date(), "1000000000000");
        System.out.println(A);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSEX() {
        return SEX;
    }

    public void setSEX(String SEX) {
        this.SEX = SEX;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getCNP() {
        return CNP;
    }

    public Address getAddress() {
        return address;
    }

    public void read(Scanner in) throws ParseException {
        System.out.println("Introduce client data");
        System.out.println("SEX: ");
        this.SEX = in.nextLine();
        System.out.println("First name: ");
        this.firstName = in.nextLine();
        System.out.println("Last name: ");
        this.lastName = in.nextLine();
        System.out.println("Email: ");
        this.email = in.nextLine();
        System.out.println("Phone Number: ");
        this.phoneNumber = in.nextLine();
        System.out.println("CNP: ");
        this.CNP = in.nextLine();
        System.out.println("Birth Date (yyyy-MM-dd): ");
        this.birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(in.nextLine());
        this.address = new Address();
        address.read(in);
    }

}
