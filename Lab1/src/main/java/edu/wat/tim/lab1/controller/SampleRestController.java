package edu.wat.tim.lab1.controller;

import edu.wat.tim.lab1.model.BasketEntity;
import edu.wat.tim.lab1.model.ClientEntity;
import edu.wat.tim.lab1.model.ItemEntity;
import edu.wat.tim.lab1.model.ProductEntity;
import edu.wat.tim.lab1.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SampleRestController {

    private final SampleService service;

    @GetMapping("/echo")
    public String echo(String value) {
        return value;
    }

    @GetMapping("/echo/{value}")
    public String echoPath(@PathVariable String value) {
        return value;
    }

    @PostMapping("/client")
    public ResponseEntity<ClientEntity> createClientEntity(@RequestBody ClientEntity entity) {
        ClientEntity savedEntity = service.createClientEntity(entity);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    @GetMapping("/entity")
    public ResponseEntity<List<ClientEntity>> getAllEntities() {
        List<ClientEntity> entities = service.getAllEntities();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductEntity>> findProductByName(@RequestParam("name") String name) {
        List<ProductEntity> products = service.searchProductByName(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/basket/{b_id}/product/{p_id}")
    public ResponseEntity<ItemEntity> changeAmountOfProduct(@PathVariable(value = "b_id") Long basketId,
                                                            @PathVariable(value = "p_id") Long productId,
                                                            @RequestParam(value = "amount") Integer amount) {

        return new ResponseEntity<>(service.changeAmountOfProduct(basketId, productId, amount), HttpStatus.OK);
    }

    @PostMapping("/client/{id}/basket")
    public ResponseEntity<BasketEntity> addBasketEntity(@RequestBody BasketEntity entity, @PathVariable(value = "id") Long id) {
        BasketEntity savedEntity = service.addBasketEntity(entity, id);
        return new ResponseEntity<>(savedEntity, HttpStatus.OK);
    }

//    @PostMapping("/basket/{b_id}/item")
//    public ResponseEntity<ProductEntity> addProductToBasket(
//            @PathVariable("b_id") Long basketId,
//            @RequestBody ProductEntity productEntity,
//            @RequestParam("amount") Integer amount) {
//
//        ProductEntity savedEntity = service.addProductEntity(basketId, productEntity, amount);
//        return new ResponseEntity<>(savedEntity, HttpStatus.OK);
//    }

    @PostMapping("/product")
    public ResponseEntity<ProductEntity> addProduct(@RequestBody ProductEntity productEntity) {
        ProductEntity savedEntity = service.addProduct(productEntity);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    @PostMapping("/basket/{b_id}/product/{p_id}")
    public ResponseEntity<ItemEntity> addProductToBasket(
            @PathVariable("b_id") Long basketId,
            @PathVariable("p_id") Long productId,
            @RequestParam("amount") Integer amount) {

        ItemEntity itemEntity = service.addItemToBasket(basketId, productId, amount);
        return new ResponseEntity<>(itemEntity, HttpStatus.OK);
    }
    //
    @DeleteMapping("/basket/{b_id}/product/{p_id}")
    public ResponseEntity<?> deleteProductByBasketAndProductId(@PathVariable(value = "b_id") Long basketId,
                                                               @PathVariable(value = "p_id") Long productId) {
        service.deleteAllByBasketEntityIdAndProductEntityId(basketId, productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/basket/{b_id}/item/{i_id}")
    public ResponseEntity<?> deleteByBasketEntityIdAndItemEntityId(@PathVariable(value = "b_id") Long basketId,
                                                                   @PathVariable(value = "i_id") Long itemId) {
        service.deleteByBasketEntityIdAndId(basketId, itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


//    @DeleteMapping("/basket/{b_id}/product/{p_id}")
//    public ResponseEntity<?> deleteItem(@PathVariable(value = "p_id") Long productId, @PathVariable(value = "b_id") Long basketId,) {
//        service.deleteByBasketEntityIdAndProductEntityId(productId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//

//    @DeleteMapping("/product/{p_id}")
//    public ResponseEntity<?> deleteItem(@PathVariable(value = "p_id") Long productId) {
//        service.deleteByProductEntityId(productId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }


//    @PutMapping("/entity/{id}")
//    public ResponseEntity<ClientEntity> updateEntity(@RequestBody ClientEntity entity, @PathVariable(value = "id") Long id) {
//        return new ResponseEntity<>(service.updateEntity(entity, id), HttpStatus.OK);
//    }
}
