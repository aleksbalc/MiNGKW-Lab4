package edu.wat.tim.lab1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="basket")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BasketEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    @JsonIgnore
    private ClientEntity clientEntity;

    @OneToMany(mappedBy = "basketEntity", cascade = CascadeType.MERGE)
    private List<ItemEntity> itemEntities = new ArrayList<>();

}
