package ru.tbcarus.repository;

import org.hibernate.SessionFactory;
import ru.tbcarus.entity.FilmActor;

public class FilmActorRepository extends AbstractRepository<FilmActor> {

    public FilmActorRepository(SessionFactory sessionFactory) {
        super(FilmActor.class, sessionFactory);
    }
}
