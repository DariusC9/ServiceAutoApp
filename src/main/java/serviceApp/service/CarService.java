package serviceApp.service;

import serviceApp.domain.Car;
import serviceApp.repository.Repository;

public class CarService {
    private Repository<Car> carRepository;

    public CarService() {
    }

    public CarService(Repository<Car> carRepository) {
        this.carRepository = carRepository;
    }

}
