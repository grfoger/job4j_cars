package ru.job4j.cars;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import ru.job4j.cars.model.Ad;
import ru.job4j.cars.model.Brand;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Driver;
import ru.job4j.cars.persistence.AdRepository;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AdRepositoryTest {


    @Test
    public void noPhotoTest() {
        AdRepository adR = new AdRepository();
       List<Ad> list = (List) adR.noPhotoAds();
        assertThat(list.size(), is(2));
    }

    @Test
    public void valuesTest() {
        AdRepository adR = new AdRepository();
        List<Ad> list = (List) adR.values();
        assertThat(list.size(), is(3));
    }

    @Test
    public void lastDayTest() {
        AdRepository adR = new AdRepository();
        List<Ad> list = (List) adR.lastDayAds();
        assertThat(list.size(), is(1));
    }

    @Test
    public void byBrandTest() {
        AdRepository adR = new AdRepository();
        Brand br = new Brand();
        br.setId(1);
        List<Ad> list = (List) adR.adsByBrand(br);
        assertThat(list.size(), is(3));
    }

}
