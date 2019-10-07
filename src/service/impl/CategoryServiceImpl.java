package service.impl;

import dao.CategoryDao;
import dao.impl.CategoryDaoImpl;
import domain.Category;
import service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Override
    public List<Category> findAll() {
        // 调用CategoryDao的方法
        System.out.println("CategoryService的findAll方法执行了...");
        CategoryDao categoryDao = new CategoryDaoImpl();
        return categoryDao.findAll();
    }

    @Override
    public void save(Category category) {
        CategoryDao categoryDao = new CategoryDaoImpl();
        categoryDao.save(category);
    }

    @Override
    public Category findone(int cbid) {
        CategoryDao categoryDao = new CategoryDaoImpl();
        return categoryDao.findone(cbid);
    }

    @Override
    public void update(Category category) {
        CategoryDao categoryDao = new CategoryDaoImpl();
        categoryDao.update(category);
    }
}
