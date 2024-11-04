package ru.tbcarus.repository;

import org.hibernate.SessionFactory;
import ru.tbcarus.entity.Country;

public class CountryRepository extends AbstractRepository<Country> {

    public CountryRepository(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }
}
