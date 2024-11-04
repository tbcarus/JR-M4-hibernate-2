package ru.tbcarus.repository;

import org.hibernate.SessionFactory;
import ru.tbcarus.entity.Actor;

public class ActorRepository extends AbstractRepository<Actor> {

    public ActorRepository(SessionFactory sessionFactory) {
        super(Actor.class, sessionFactory);
    }
}
