package edu.wat.tim.lab1.repository;

import edu.wat.tim.lab1.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByName(String name);

}
