package ru.job4j.cars.model;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String description;
    private byte[] photo;
    private boolean sold;

    private Car car;

    @ManyToOne
    @JoinColumn(name = "driver_id", foreignKey = @ForeignKey(name = "DRIVER_ID_FK"))
    private Driver driver;

}
