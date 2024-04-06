package serviceApp.domain;

import java.time.LocalDate;

public class Transaction extends BaseId {
    private int id_car;
    private int id_client;
    private float partsPrice;
    private float workPrice;
    private LocalDate dateHour;

    public Transaction() {
    }

    public Transaction(int id, int id_car, int id_client, float partsPrice, float workPrice, LocalDate dateHour) {
        super(id);
        this.id_car = id_car;
        this.id_client = id_client;
        this.partsPrice = partsPrice;
        this.workPrice = workPrice;
        this.dateHour = dateHour;
    }

    public int getId_car() {
        return id_car;
    }

    public void setId_car(int id_car) {
        this.id_car = id_car;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public float getPartsPrice() {
        return partsPrice;
    }

    public void setPartsPrice(float partsPrice) {
        this.partsPrice = partsPrice;
    }

    public float getWorkPrice() {
        return workPrice;
    }

    public void setWorkPrice(float workPrice) {
        this.workPrice = workPrice;
    }

    public LocalDate getDateHour() {
        return dateHour;
    }

    public void setDateHour(LocalDate dateHour) {
        this.dateHour = dateHour;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id = " + super.getId() +
                ", id_car = " + id_car +
                ", id_client = " + id_client +
                ", partsPrice = " + partsPrice +
                ", workPrice = " + workPrice +
                ", dateHour = " + dateHour +
                '}';
    }
}
