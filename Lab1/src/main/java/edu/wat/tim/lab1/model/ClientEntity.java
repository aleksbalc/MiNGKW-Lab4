package edu.wat.tim.lab1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="client")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "e-mail address")
    private String email;

    @OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL)
    private List<BasketEntity> basketEntitiesEntities = new ArrayList<>();

}
