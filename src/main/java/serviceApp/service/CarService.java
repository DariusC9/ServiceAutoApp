package serviceApp.service;

import serviceApp.domain.BaseId;
import serviceApp.domain.Car;
import serviceApp.repository.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CarService {
    private Repository<Car> carRepository;

    public CarService() {
    }

    public CarService(Repository<Car> carRepository) {
        this.carRepository = carRepository;

        // pentru test
        this.carRepository.save(Car.testCar);
        this.carRepository.save(Car.testCar);
    }

    public List<Car> showCarList() {
        return carRepository.findAll();
    }

    public boolean validateCarId (int idNewCar) {
        List<Car> carList = carRepository.findAll();
        for (Car carElement : carList) {
            if (carElement.getId() == newCar.getId()) {
                return false;
            }
        }
        return true;
    }
}
