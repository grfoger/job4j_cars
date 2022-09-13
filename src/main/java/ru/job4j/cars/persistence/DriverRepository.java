package ru.job4j.cars.persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Driver;

import java.util.Optional;
import java.util.function.Function;

@Repository
public class DriverRepository {

    private final SessionFactory sf;

    public DriverRepository(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
    public Optional<Driver> addDriver(Driver user) {
        try {
            tx(session -> session.save(user));
        } catch (HibernateException e) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

    public Optional<Driver> findUserByLoginAndPass(String login, String password) {
        return  tx(session ->
                session.createQuery("from Driver where login = :login AND password = :password")
                        .setParameter("login", login)
                        .setParameter("password", password)
                        .uniqueResultOptional());
    }

    public Driver getDriverById(int driverId) {
        return this.tx(
                session -> (Driver) session.createQuery("from Driver where id = :id")
                        .setParameter("id", driverId).uniqueResult());
    }
}
