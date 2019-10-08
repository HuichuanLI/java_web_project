package dao;

import domain.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> findAll();

    void save(Category category);

    Category findone(int cid);

    void update(Category category);

    void delete(Integer cid);

}
