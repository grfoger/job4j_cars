package ru.job4j.cars.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Driver {

    @EqualsAndHashCode.Include
    private int id;
}
