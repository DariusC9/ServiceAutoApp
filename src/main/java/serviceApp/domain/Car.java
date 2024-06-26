package serviceApp.domain;

public class Car extends BaseId {
    private int id;
    private String model;
    private int yearAcquisition;
    private float numKm;
    private boolean hasWaranty;

    public Car() {
    }

    public Car(int id, String model, int yearAcquisition, float numKm, boolean hasWaranty) {
        super(id);
        this.model = model;
        this.yearAcquisition = yearAcquisition;
        this.numKm = numKm;
        this.hasWaranty = hasWaranty;
    }

    public static Car testCar = new Car(0, "Model", 2004, 1000, false);

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearAcquisition() {
        return yearAcquisition;
    }

    public void setYearAcquisition(int yearAcquisition) {
        this.yearAcquisition = yearAcquisition;
    }

    public float getNumKm() {
        return numKm;
    }

    public void setNumKm(int numKm) {
        this.numKm = numKm;
    }

    public boolean isHasWaranty() {
        return hasWaranty;
    }

    public void setHasWaranty(boolean hasWaranty) {
        this.hasWaranty = hasWaranty;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id = " + super.getId() +
                ", model = '" + model + '\'' +
                ", yearAcquisition = " + yearAcquisition +
                ", numKm = " + numKm +
                ", hasWaranty = " + hasWaranty +
                '}';
    }
}
