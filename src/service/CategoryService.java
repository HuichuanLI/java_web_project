package service;

import domain.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    void save(Category category);

    void update(Category category);

    Category findone(int cbid);

}
