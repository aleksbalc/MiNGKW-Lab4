package edu.wat.tim.lab1.service;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;

import edu.wat.tim.lab1.model.ItemEntity;
import edu.wat.tim.lab1.model.ProductEntity;
import edu.wat.tim.lab1.repository.BasketEntityRepository;
import edu.wat.tim.lab1.repository.ClientEntityRepository;
import edu.wat.tim.lab1.repository.ItemEntityRepository;
import edu.wat.tim.lab1.repository.ProductEntityRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
@Slf4j
public class ScriptService {
    private final ItemEntityRepository itemEntityRepository;
    private final ProductEntityRepository productEntityRepository;

    @Autowired
    public ScriptService(ItemEntityRepository itemEntityRepository, ProductEntityRepository productEntityRepository) {
        this.itemEntityRepository = itemEntityRepository;
        this.productEntityRepository = productEntityRepository;
    }

    public List<ProductEntity> getAllProducts() {
        return productEntityRepository.findAll();
    }

    public String exec(String script) {
        try (Context context = Context.newBuilder("js")
                .allowAllAccess(true)
                .build()) {
            var bindings = context.getBindings("js");
            bindings.putMember("itemEntityRepository", itemEntityRepository);
            bindings.putMember("productEntityRepository", productEntityRepository);
            return context.eval("js", script).toString();
        } catch (PolyglotException e) {
            log.error("Error executing", e);
            return e.getMessage() + "\n" + e.getSourceLocation().toString();
        }
    }
}
