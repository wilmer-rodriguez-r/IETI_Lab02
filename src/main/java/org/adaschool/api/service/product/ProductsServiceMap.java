package org.adaschool.api.service.product;

import org.adaschool.api.exception.ProductNotFoundException;
import org.adaschool.api.repository.product.Product;
import org.adaschool.api.repository.product.ProductDto;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductsServiceMap implements ProductsService {

    private final Map<String, Product> products = new HashMap<>();
    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public Optional<Product> findById(String id) throws ProductNotFoundException {
        return Optional.of(products.get(id));
    }

    @Override
    public List<Product> all() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void deleteById(String id) {
        products.remove(id);
    }

    @Override
    public Product update(Product product, String productId) {
        ProductDto productDto = new ProductDto(product.getName(), product.getDescription(), product.getCategory(), product.getTags(), product.getPrice(), product.getImageUrl());
        Product productMap = products.get(productId);
        productMap.update(productDto);
        return products.get(productId);
    }
}
