package ru.tbcarus.repository;

import org.hibernate.SessionFactory;
import ru.tbcarus.entity.Language;

public class LanguageRepository extends AbstractRepository<Language> {

    public LanguageRepository(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }
}
