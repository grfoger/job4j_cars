package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String login;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "history_owner", joinColumns = {
            @JoinColumn(name = "car_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "driver_id", nullable = false, updatable = false)})
    private Set<Car> cars = new HashSet<>();
}
