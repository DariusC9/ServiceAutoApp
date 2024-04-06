package serviceApp.domain;

public class BaseId {
    private int id;

    public BaseId() {
    }

    public BaseId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseId{" +
                "id=" + id +
                '}';
    }
}
