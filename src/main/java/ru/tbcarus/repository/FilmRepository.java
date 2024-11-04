package ru.tbcarus.repository;

import org.hibernate.SessionFactory;
import ru.tbcarus.entity.Film;

public class FilmRepository extends AbstractRepository<Film> {

    public FilmRepository(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }
}
