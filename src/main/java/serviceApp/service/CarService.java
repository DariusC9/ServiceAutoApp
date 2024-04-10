package serviceApp.service;

import serviceApp.domain.BaseId;
import serviceApp.domain.Car;
import serviceApp.repository.Repository;

import java.util.ArrayList;
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
            if (carElement.getId() == idNewCar) {
                return false;
            }
        }
        return true;
    }

    public void addNewCar(Car car) {
        carRepository.save(car);
    }

    public void updateCar(Car updateCar) {
        carRepository.update(updateCar);
    }

    public void deleteCar(int idDeleteCar) {
        List<Car> carList = carRepository.findAll();
        for (Car carElem : carList) {
            if (carElem.getId() == idDeleteCar) {
                carRepository.delete(carElem);
                break;
            }
        }
    }

    public List<Car> searchByCarModel(String searchModel) {
        List<Car> carList = carRepository.findAll();
        List<Car> result = new ArrayList<>();
        for (Car carElem : carList) {
            if (carElem.getModel().equals(searchModel)) {
                result.add(carElem);
            }
        }
        return result;
    }
}
