package ru.job4j.cars.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.function.Function;

@Repository
public class CarRepository {

    private <T> T tx(final Function<Session, T> command) {
        T result = null;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            result = command.apply(session);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        return result;
    }

    public Collection<Car> getAllCars() {
        return this.tx(
                session -> session.createQuery("from Car").list()
        );
    }

    public Car getCarById(int carId) {
        return this.tx(
                session -> (Car) session.createQuery("from Car where id = :id")
                        .setParameter("id", carId).uniqueResult());
    }
}
