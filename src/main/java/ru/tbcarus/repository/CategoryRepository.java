package ru.tbcarus.repository;

import org.hibernate.SessionFactory;
import ru.tbcarus.entity.Category;

public class CategoryRepository extends AbstractRepository<Category> {

    public CategoryRepository(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }
}
