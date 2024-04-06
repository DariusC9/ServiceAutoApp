package serviceApp.ui;

import serviceApp.service.CarService;
import serviceApp.service.ClientService;

import java.util.List;
import java.util.Scanner;

public class MainConsole {

    private ClientService clientService;
    private CarService carService;

    public MainConsole() {
    }
    public MainConsole(ClientService clientService, CarService carService) {
        this.clientService = clientService;
        this.carService = carService;
    }

    public void runMainConsole() {
        int optiune = optiuneMeniuPrincipal();
        while (optiune != 0) {
            switch (optiune) {
                case 1:
                    afiseaza("Meniu Car");
                    break;
                case 2:
                    afiseaza("Meniu Client");
                    break;
                case 3:
                    afiseaza("Meniu Tranzactie");
                    break;
                case 4:
                    afiseaza("Meniu Prelucrare");
                    break;
                case 0:
                    afiseaza("Program finalizat");
                    break;
                default:
                    afiseaza("Introduceti o optiune valida");
            }
            optiune = optiuneMeniuPrincipal();
        }
    }
    private int optiuneMeniuPrincipal() {
        afiseazaOptiuniMeniuPrincipal();
        afiseaza("Alege o optiune");
        int optiune = citIntreg();
        return optiune;
    }
    // UI(print)
    private void afiseazaOptiuniMeniuPrincipal() {
        afiseaza("");
        afiseaza("1. Operatii CRUD Masina");
        afiseaza("2. Operatii CRUD Client");
        afiseaza("3. Operatii CRUD Tranzactie");
        afiseaza("4. Meniu prelucrare date");
        afiseaza("0. Terminare program");
    }
    private void afiseaza(String text) { System.out.println(text); }

    private <T> void afiseazaObiecte(List<T> obiecte) {
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
