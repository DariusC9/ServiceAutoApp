package serviceApp.service;

import serviceApp.domain.BaseId;
import serviceApp.domain.Car;
import serviceApp.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CarService extends SimpleObjectService<Car> {
    public CarService() {
    }

    public CarService(Repository<Car> carRepository) {
        super(carRepository);
    }
    public List<Car> searchByCarModel(String searchModel) {
        List<Car> carList = repository.findAll();
        List<Car> result = new ArrayList<>();
        for (Car carElem : carList) {
            if (carElem.getModel().equals(searchModel)) {
                result.add(carElem);
            }
        }
        return result;
    }
}
