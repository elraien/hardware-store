package io.hardware.store.api.service;

import io.hardware.store.api.exception.ItemNotFoundException;
import io.hardware.store.api.exception.PermissionViolationException;
import io.hardware.store.api.model.catalogue.Product;
import io.hardware.store.api.model.user.UserRoleType;
import io.hardware.store.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ProductService {
    Logger logger = Logger.getLogger(ProductService.class.getName());

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product addNewProduct(Long id, Product product) {
        boolean hasRequiredPermission = userService.checkUserRole(id, UserRoleType.ADMIN);
        if (hasRequiredPermission) {
            logger.info("Adding new product.");
            return productRepository.save(product);
        } else {
            throw new PermissionViolationException(id);
        }
    }

    public void deleteProduct(Long productId, Long userId) {
        boolean hasRequiredPermission = userService.checkUserRole(userId, UserRoleType.ADMIN);
        if (hasRequiredPermission) {
            logger.info("Deleting product.");
            processDeletion(productId);
        }
    }

    public Product updateProduct(Long productId, Long userId, Product product) {
        boolean hasRequiredPermission = userService.checkUserRole(userId, UserRoleType.ADMIN);

        if (hasRequiredPermission) {
            logger.info("Updating product.");
            return processUpdate(productId, product);
        }
        throw new PermissionViolationException(userId);
    }

    private void processDeletion(Long productId) {
        var productForDeletion = productRepository.findById(productId);
        if (productForDeletion == null) {
            throw new ItemNotFoundException(productId);
        } else {
            productRepository.deleteById(productId);
            var deletedProduct = productRepository.findById(productId);
            if (deletedProduct == null) {
                logger.info(String.format("Successfully deleted product with id: %s.", productId));
            } else {
                logger.info(String.format("Product with id: %s was not deleted.", productId));
            }
        }
    }

    private Product processUpdate(Long productId, Product product) {
        var productForUpdate = productRepository.findById(productId);
        if (productForUpdate == null) {
            throw new ItemNotFoundException(productId);
        } else {
            var updatedProduct = productForUpdate.get();
            if (product.getName() != null) {
                updatedProduct.setName(product.getName());
            }
            if (product.getPrice() != 0) {
                updatedProduct.setPrice(product.getPrice());
            }
            if (product.getDescription() != null) {
                updatedProduct.setDescription(product.getDescription());
            }
            updatedProduct = productRepository.save(productForUpdate.get());
            if (!updatedProduct.equals(product)) {
                return updatedProduct;
            } else {
                logger.info(String.format("No change in product with id: %s.", productId));
                return product;
            }
        }
    }
}
