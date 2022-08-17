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
    private String brand;
    private String model;
    private String body;
    private byte[] photo;
    private boolean sold;

    @ManyToOne
    @JoinColumn(name = "account_id", foreignKey = @ForeignKey(name = "ACCOUNT_ID_FK"))
    private Account account;

}