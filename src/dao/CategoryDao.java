package dao;

import domain.Category;
import domain.Product;

import java.sql.Connection;
import java.util.List;

public interface CategoryDao {
    List<Category> findAll();

    void save(Category category);

    Category findone(int cid);

    void update(Category category);

    void delete(Integer cid);

    void delete(Connection connection, Integer cid);



}
