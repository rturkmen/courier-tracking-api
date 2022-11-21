package com.migros.courier.persistence.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Courier {

    @Id
    @Column(name = "id")
    private Long identityNumber;
    private Double lat;
    private Double lng;
    private LocalDateTime time;
    @OneToMany(mappedBy = "courier", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CourierMoves> courierMoves;
    @CreationTimestamp
    private LocalDateTime createDate;
    @UpdateTimestamp
    private LocalDateTime updateDate;

}
