package edu.wat.tim.lab1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="item")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "amount")
    private Integer amount;

    @ManyToOne
    @JoinColumn(name="basket_id", nullable=false)
    @JsonIgnore
    private BasketEntity basketEntity;

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    @JsonIgnore
    private ProductEntity productEntity;
}
