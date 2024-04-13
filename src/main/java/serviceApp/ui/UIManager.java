package serviceApp.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class UIManager {
    // Various Menu Options
    public int optionMainMenu() {
        displayMainMenuOption();
        displayText("Insert an option: ");
        int option = readInt();
        return option;
    }

    public int optionCarMenu() {
        showCarMenuOptions();
        displayText("Choose an option: ");
        int option = readInt();
        return option;
    }

    public void displayMainMenuOption() {
        displayText("");
        displayText("1. CRUD Menu Car");
        displayText("2. CRUD Menu Client Card");
        displayText("3. CRUD Menu Transactions");
        displayText("4. Search Car in database");
        displayText("5. Search Client Card by Last Name");
        displayText("6. Display transactions by sum");
        displayText("7. Display Cars by total work price");
        displayText("8. Display Client Cards by total discounts");
        displayText("9. Update Waranties ");
        displayText("10. Delete transactions between 2 dates");
        displayText("0. End program");
    }
  
    public int optionClientMenu() {
        clientMenuOptions();
        displayText("Insert an option: ");
        int option = readInt();
        return option;
    }
  
    public void clientMenuOptions() {
        displayText("");
        displayText("1. Add new Client Card");
        displayText("2. Display all Client Cards");
        displayText("3. Update Client Card info");
        displayText("4. Delete Client Card");
        displayText("0. End Program");
    }
  
    public void showCarMenuOptions() {
        displayText("");
        displayText("1. Add new Car");
        displayText("2. Display all Cars");
        displayText("3. Update Car Info");
        displayText("4. Delete Car from database");
        displayText("0. End program");
    }

    public int optionTransactionMenu() {
        transactionMenuOptions();
        displayText("Choose an option: ");
        int option = readInt();
        return option;
    }

    public void transactionMenuOptions() {
        displayText("");
        displayText("1. Add a new transaction");
        displayText("2. Show all transactions");
        displayText("3. Update a transaction");
        displayText("4. Delete a transaction");
        displayText("0. End program");
    }

    public void displayText(String text) { System.out.println(text); }

    public <T> void displayObjects(List<T> obiecte) {
        for (T obiect : obiecte) {
            System.out.println(obiect);
        }
    }

    // User Input Validation
    public int readInt() {
        try {
            Scanner s = new Scanner(System.in);
            int i = s.nextInt();
            return i;
        }
        catch(Exception e) {
            displayText("Please enter a valid number");
            return readInt();
        }
    }

    public float readFloat() {
        try {
            Scanner s = new Scanner(System.in);
            float i = s.nextFloat();
            return i;
        }
        catch(Exception e) {
            displayText("Please insert a valid number");
            return readFloat();
        }
    }

    public String readString() {
        try {
            Scanner s = new Scanner(System.in);
            String cuvant = s.nextLine();
            return cuvant;
        }
        catch(Exception e) {
            displayText("Please enter a valid text");
            return readString();
        }
    }

    public double readDouble() {
        try {
            Scanner s = new Scanner(System.in);
            double i = s.nextDouble();
            return i;
        }
        catch(Exception e) {
            displayText("Please enter a valid number");
            return readDouble();
        }
    }

    public  LocalDate addDate() {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter a date [M/d/yyyy]: ");
            String str = scan.nextLine();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
            return LocalDate.parse(str, dateFormat);
        }
        catch (Exception e) {
            displayText("Please insert a valid date [M/d/yyyy]");
            return addDate();
        }
    }
}
