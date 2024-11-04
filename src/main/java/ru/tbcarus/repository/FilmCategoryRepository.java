package ru.tbcarus.repository;

import org.hibernate.SessionFactory;
import ru.tbcarus.entity.FilmCategory;

public class FilmCategoryRepository extends AbstractRepository<FilmCategory> {

    public FilmCategoryRepository(SessionFactory sessionFactory) {
        super(FilmCategory.class, sessionFactory);
    }
}
