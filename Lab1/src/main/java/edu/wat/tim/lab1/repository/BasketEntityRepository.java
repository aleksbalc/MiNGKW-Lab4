package edu.wat.tim.lab1.repository;

import edu.wat.tim.lab1.model.BasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BasketEntityRepository extends JpaRepository<BasketEntity, Long> {


}
