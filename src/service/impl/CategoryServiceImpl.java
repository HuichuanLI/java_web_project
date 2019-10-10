package service.impl;

import dao.CategoryDao;
import dao.ProductDao;
import dao.impl.CategoryDaoImpl;
import dao.impl.ProductDaoImpl;
import domain.Category;
import domain.Product;
import service.CategoryService;

import java.sql.Connection;
import java.sql.SQLException;
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

    @Override
    public void delete(Integer cid) throws SQLException {
        Connection connection = null;
        connection.setAutoCommit(false);
        try {
            ProductDao productDao = new ProductDaoImpl();
            List<Product> list = productDao.findByCid(cid);
            for (Product produc : list) {
                produc.getCategory().setCid(null);
                productDao.update(connection, produc);
            }
            CategoryDao categoryDao = new CategoryDaoImpl();
            categoryDao.delete(connection, cid);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
