package edu.wat.tim.lab1.service;

import edu.wat.tim.lab1.model.BasketEntity;
import edu.wat.tim.lab1.model.ClientEntity;
import edu.wat.tim.lab1.model.ItemEntity;
import edu.wat.tim.lab1.model.ProductEntity;
import edu.wat.tim.lab1.repository.BasketEntityRepository;
import edu.wat.tim.lab1.repository.ClientEntityRepository;
import edu.wat.tim.lab1.repository.ItemEntityRepository;
import edu.wat.tim.lab1.repository.ProductEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final ClientEntityRepository clientEntityRepository;
    private final BasketEntityRepository basketEntityRepository;
    private final ItemEntityRepository itemEntityRepository;
    private final ProductEntityRepository productEntityRepository;



    public ClientEntity createClientEntity(ClientEntity entity) {
        return clientEntityRepository.save(entity);
    }

    public List<ClientEntity> getAllEntities() {
        return clientEntityRepository.findAll();
    }

    public List<ProductEntity> searchProductByName(String name) {
        return productEntityRepository.findByName(name);
    }

    public BasketEntity addBasketEntity(BasketEntity basketEntity, Long clientId) {
        ClientEntity clientEntity = clientEntityRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono encji o id " + clientId));
        basketEntity.setClientEntity(clientEntity);
        return basketEntityRepository.save(basketEntity);
    }

    public void deleteBasketEntity(Long childId) {
        basketEntityRepository.deleteById(childId);
    }

//    public ProductEntity addProductEntity(Long basketId, ProductEntity productEntity, Integer amount) {
//        BasketEntity basketEntity = basketEntityRepository.findById(basketId)
//                .orElseThrow(() -> new RuntimeException("Nie znaleziono encji o id " + basketId));
//        productEntity = productEntityRepository.save(productEntity);
//        ItemEntity itemEntity = new ItemEntity();
//        itemEntity.setAmount(amount);
//        itemEntity.setBasketEntity(basketEntity);
//        itemEntity.setProductEntity(productEntity);
//
//        basketEntity.getItemEntities().add(itemEntity);
//        itemEntityRepository.save(itemEntity);
//        return productEntity;
//    }

    public ItemEntity addItemToBasket(Long basketId, Long productId, Integer amount) {
        BasketEntity basketEntity = basketEntityRepository.findById(basketId)
                .orElseThrow(() -> new RuntimeException("Basket not found with ID: " + basketId));

        ProductEntity productEntity = productEntityRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setAmount(amount);
        itemEntity.setBasketEntity(basketEntity);
        itemEntity.setProductEntity(productEntity);

        basketEntity.getItemEntities().add(itemEntity);
        itemEntityRepository.save(itemEntity);
        return itemEntity;
    }

    public ProductEntity addProduct(ProductEntity productEntity) {
        return productEntityRepository.save(productEntity);
    }

    public ClientEntity updateEntity(ClientEntity updatedEntity, Long id) {
        ClientEntity entity = clientEntityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono encji o id " + id));

        entity.setName(updatedEntity.getName());
        return clientEntityRepository.save(entity);
    }

    public ItemEntity changeAmountOfProduct(Long basketId, Long productId, Integer amount) {
        ProductEntity product = productEntityRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono koszyka o id " + productId));
        BasketEntity basket = basketEntityRepository.findById(basketId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono koszyka o id " + basketId));
        ItemEntity item = itemEntityRepository.findByProductEntityIdAndBasketEntityId(productId, basketId);
        if(amount < 1){
            throw new RuntimeException("Podano liczbe mniejsza niz 1");
        }
        item.setAmount(amount);
        return itemEntityRepository.save(item);
    }

    public void deleteAllByBasketEntityIdAndProductEntityId(Long basketId, Long productId) {
        BasketEntity basket = basketEntityRepository.findById(basketId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono koszyka o id " + basketId));
        ProductEntity product = productEntityRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono produktu o id " + productId));
        itemEntityRepository.deleteAllByBasketEntityIdAndProductEntityId(basketId, productId);

    }

    public void deleteByBasketEntityIdAndId(Long basketId, Long itemId) { // to działa picuś glancuś
        BasketEntity basket = basketEntityRepository.findById(basketId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono koszyka o id " + basketId));
        ItemEntity item = itemEntityRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono przedmiotu o id " + itemId));
        ItemEntity itemToDelete = itemEntityRepository.findByBasketEntityIdAndId(basketId, itemId);
        itemEntityRepository.delete(itemToDelete);
    }

//    public void deleteByProductEntityId(Long productId) {
//        ItemEntity item = itemEntityRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Nie znaleziono produktu o id " + productId));
//        ProductEntity product = item.getProductEntity();
//        itemEntityRepository.delete(item);
//        productEntityRepository.delete(product);
//    }
}
