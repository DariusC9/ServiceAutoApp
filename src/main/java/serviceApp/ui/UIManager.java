package serviceApp.ui;

import java.util.List;
import java.util.Scanner;

public class UIManager {
    public int optiuneMeniuPrincipal() {
        afiseazaOptiuniMeniuPrincipal();
        afiseaza("Alege o optiune");
        int optiune = citIntreg();
        return optiune;
    }
    public int optionCarMenu() {
        showCarMenuOptions();
        afiseaza("Choose an option: ");
        int optiune = citIntreg();
        return optiune;
    }
    // UIManager(print)
    public void afiseazaOptiuniMeniuPrincipal() {
        afiseaza("");
        afiseaza("1. Operatii CRUD Masina");
        afiseaza("2. Operatii CRUD Client");
        afiseaza("3. Operatii CRUD Tranzactie");
        afiseaza("4. Meniu prelucrare date");
        afiseaza("0. Terminare program");
    }
    public void showCarMenuOptions() {
        afiseaza("");
        afiseaza("1. Add Car");
        afiseaza("2. Show Cars List");
        afiseaza("3. Update Car Info");
        afiseaza("4. Delete Car from List");
        afiseaza("0. End program");
    }

    public void afiseaza(String text) { System.out.println(text); }
    public <T> void afiseazaObiecte(List<T> obiecte) {
        for (T obiect : obiecte) {
            System.out.println(obiect);
        }
    }
    // user input
    public int citIntreg() {
        try {
            Scanner s= new Scanner(System.in);
            int i=s.nextInt();
            return i;
        }
        catch(Exception e) {
            afiseaza("Va rugam sa introduceti un numar valid");
            return citIntreg();
        }
    }
    public float citFloat() {
        try {
            Scanner s= new Scanner(System.in);
            float i=s.nextFloat();
            return i;
        }
        catch(Exception e) {
            afiseaza("Va rugam sa introduceti un numar valid");
            return citIntreg();
        }
    }
    public String cititString() {
        try {
            Scanner s = new Scanner(System.in);
            String cuvant = s.nextLine();
            return cuvant;
        }
        catch(Exception e) {
            afiseaza("Ati introdus o optiune invalida pentru optiunea aleasa");
            return cititString();
        }
    }
}
