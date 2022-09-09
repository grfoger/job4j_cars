package ru.job4j.cars.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Ad;
import ru.job4j.cars.model.Brand;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class AdRepository {


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

    public Ad add(Ad ad) {
        return this.tx(
                session -> (Ad) session.save(ad)
        );
    }

    public Collection<Ad> values() {
        return this.tx(
                session -> session.createQuery("from Ad").list()
        );
    }

    public Collection<Ad> noPhotoAds() {
        return this.tx(
                session -> session.createQuery("from Ad where photo = null").list()
        );
    }

    public Collection<Ad> lastDayAds() {
        return this.tx(
                session -> session.createQuery("from Ad where created > :created")
                        .setParameter("created", LocalDateTime.now().minusDays(1)).list()
        );
    }

    public Collection<Ad> adsByBrand(Brand brand) {
        return this.tx(
                session -> session.createQuery("from Ad where car.brand.id = :brandId")
                        .setParameter("brandId", brand.getId()).list()
        );
    }


}
