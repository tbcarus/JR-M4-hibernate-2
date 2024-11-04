package ru.tbcarus.repository;

import org.hibernate.SessionFactory;
import ru.tbcarus.entity.FilmText;

public class FilmTextRepository extends AbstractRepository<FilmText> {

    public FilmTextRepository(SessionFactory sessionFactory) {
        super(FilmText.class, sessionFactory);
    }
}
