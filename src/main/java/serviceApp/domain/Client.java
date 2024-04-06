package serviceApp.domain;

import java.time.LocalDate;

public class Client extends BaseId {
    private String lastName;
    private String firstName;
    private double cnp;
    private LocalDate birthday;
    private LocalDate registrationDate;

    public Client() {
    }

    public Client(int id, String lastName, String firstName, double cnp, LocalDate birthday, LocalDate registrationDate) {
        super(id);
        this.lastName = lastName;
        this.firstName = firstName;
        this.cnp = cnp;
        this.birthday = birthday;
        this.registrationDate = registrationDate;
    }

    public static Client testClient = new Client(1, "Nume", "Prenume", 1213141516, LocalDate.now(), LocalDate.now());

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getCnp() {
        return cnp;
    }

    public void setCnp(double cnp) {
        this.cnp = cnp;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "Client {" +
                "id = " + super.getId() +
                ", lastName = '" + lastName + '\'' +
                ", firstName = '" + firstName + '\'' +
                ", cnp=" + cnp +
                ", birthday = " + birthday +
                ", registrationDate = " + registrationDate +
                '}';
    }
}
