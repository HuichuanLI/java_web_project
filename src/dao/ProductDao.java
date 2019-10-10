package dao;

import domain.Product;

import java.sql.Connection;
import java.util.List;

public interface ProductDao {
    List<Product> findAll();

    void save(Product product);

    Product findOne(Integer pid);

    void update(Product product);

    void update(Connection connection, Product product);


    void delete(Integer pid);

    List<Product> findByCid(Integer cid);
    int findCount();

    List<Product> findByPage(int begin, int limit);
}
