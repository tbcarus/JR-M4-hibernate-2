package ru.tbcarus.repository;

import org.hibernate.SessionFactory;
import ru.tbcarus.entity.City;

public class CityRepository extends AbstractRepository<City> {

    public CityRepository(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }
}
