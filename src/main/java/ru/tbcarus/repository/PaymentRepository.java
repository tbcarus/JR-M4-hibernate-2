package ru.tbcarus.repository;

import org.hibernate.SessionFactory;
import ru.tbcarus.entity.Payment;

public class PaymentRepository extends AbstractRepository<Payment> {

    public PaymentRepository(SessionFactory sessionFactory) {
        super(Payment.class, sessionFactory);
    }
}
