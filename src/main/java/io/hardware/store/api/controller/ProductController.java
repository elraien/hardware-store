package io.hardware.store.api.controller;

import io.hardware.store.api.model.catalogue.Product;
import io.hardware.store.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET, value = "/products")
    List<Product> getAllProducts() {
        return productService.findAllProducts();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/products/{userId}")
    Product addProduct(@PathVariable Long userId, @RequestBody Product product) {
        return productService.addNewProduct(userId, product);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/products/{productId}/{userId}")
    Product updateProduct(@PathVariable Long productId, @PathVariable Long userId, @RequestBody Product product) {
        return productService.updateProduct(productId, userId, product);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/products/{productId}/{userId}")
    void deleteProduct(@PathVariable Long productId, @PathVariable Long userId) {
        productService.deleteProduct(productId, userId);
    }
}
