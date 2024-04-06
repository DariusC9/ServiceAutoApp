package serviceApp.service;

import serviceApp.domain.Car;
import serviceApp.repository.Repository;

import java.util.List;

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
}
