package com.migros.courier.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_generatorr")
    @SequenceGenerator(name="store_generator", sequenceName = "store_seq")
    private Long id;
    private String name;
    private Double lat;
    private Double lng;
    @OneToMany(mappedBy = "store", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CourierMoves> courierMoves;

}
