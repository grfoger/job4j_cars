package ru.job4j.cars.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Engine {
    @EqualsAndHashCode.Include
    private int id;
}
