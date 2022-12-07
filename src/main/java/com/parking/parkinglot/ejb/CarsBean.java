package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.CarDto;
import com.parking.parkinglot.entities.Car;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CarsBean {

    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<CarDto> findAllCars() {
        LOG.info("findAllCars");
        try {
            TypedQuery<Car> typedQuery =
                    entityManager.createQuery("SELECT c FROM Car c", Car.class);
            List<Car> cars = typedQuery.getResultList();
            return copyCarsToDto(cars);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public List<CarDto> copyCarsToDto(List<Car> cars) {
        List<CarDto> carsDto = new LinkedList<CarDto>();

        for (int i = 0; i < cars.size(); i++) {
            CarDto dto = new CarDto(cars.get(i).getId(),
                    cars.get(i).getLicensePlate(),
                    cars.get(i).getParkingSpot(),
                    cars.get(i).getOwner().getUsername());
            carsDto.add(dto);
        }

        return carsDto;
    }
}
