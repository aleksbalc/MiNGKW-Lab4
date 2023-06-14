package edu.wat.tim.lab1.repository;

import edu.wat.tim.lab1.model.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ItemEntityRepository extends JpaRepository<ItemEntity, Long> {
    public ItemEntity findByProductEntityIdAndBasketEntityId(Long productId, Long basketId);
    public ItemEntity findByBasketEntityIdAndId(Long basketId, Long itemId);
    @Transactional
    public void deleteByProductEntityId(Long productId);

    @Transactional
    public void deleteByBasketEntityIdAndId(Long basketId, Long itemId);

    @Transactional
    public void deleteAllByBasketEntityIdAndProductEntityId(Long basketId, Long productId);
}