package com.migros.courier.persistence.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CourierMoves {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "courier_id", referencedColumnName = "id")
    private Courier courier;
    @ManyToOne(optional = false)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;
    private Double lng;
    private Double lat;
    private LocalDateTime allowedTime;
    private Double distance;
    @CreationTimestamp
    private LocalDateTime createDate;
    @UpdateTimestamp
    private LocalDateTime updateDate;
}
