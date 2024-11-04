package ru.tbcarus.repository;

import org.hibernate.SessionFactory;
import ru.tbcarus.entity.Staff;

public class StaffRepository extends AbstractRepository<Staff> {

    public StaffRepository(SessionFactory sessionFactory) {
        super(Staff.class, sessionFactory);
    }
}
