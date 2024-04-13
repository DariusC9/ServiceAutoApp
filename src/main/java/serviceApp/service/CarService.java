package serviceApp.service;

import serviceApp.domain.Car;
import serviceApp.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class CarService extends SimpleObjectService<Car> {
    public CarService() {
    }
    public CarService(Repository<Car> carRepository) {
        super(carRepository);

        repository.save(Car.testCar);
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

    public void updateWaranty() {
        List<Car> carList = repository.findAll();
        for (Car carElem : carList) {
            if (carElem.getNumKm() > 60000) {
                carElem.setHasWaranty(false);
            }
        }
    }

    public Car searchById(int idCar) {
        return repository.findById(idCar);
    }
}
