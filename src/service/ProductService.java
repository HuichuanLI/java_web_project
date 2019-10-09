package service;

import domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    void save(Product product);

    Product findOne(Integer pid);

    void update(Product product);
}
