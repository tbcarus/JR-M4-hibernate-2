package ru.tbcarus.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class AbstractRepository<T> {

    private Class<T> clazz;
    private SessionFactory sessionFactory;

    public T getById(int id) {
        return sessionFactory.getCurrentSession().get(clazz, id);
    }

    public List<T> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from " + clazz.getName(), clazz).list();
    }

    public List<T> getPage(int offset, int limit) {
        Query<T> query = sessionFactory.getCurrentSession().createQuery("from " + clazz.getName(), clazz);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    public void save(T entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }

    public void update(T entity) {
        sessionFactory.getCurrentSession().merge(entity);
    }

    public void delete(T entity) {
        sessionFactory.getCurrentSession().remove(entity);
    }

    public void deleteById(int id) {
        sessionFactory.getCurrentSession().remove(getById(id));
    }

}
