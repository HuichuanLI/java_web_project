package service.impl;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import domain.PageBean;
import domain.Product;
import service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> findAll() {
        System.out.println("ProductService的findAll方法执行了...");
        ProductDao productDao = new ProductDaoImpl();
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        ProductDao productDao = new ProductDaoImpl();
        productDao.save(product);
    }

    @Override
    public Product findOne(Integer pid) {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.findOne(pid);
    }

    @Override
    public void update(Product product) {
        ProductDao productDao = new ProductDaoImpl();
        productDao.update(product);
    }

    @Override
    public void delete(Integer pid) {
        ProductDao productDao = new ProductDaoImpl();
        productDao.delete(pid);
    }

    @Override
    public PageBean<Product> findByPage(int page) {
        PageBean<Product> pageBean = new PageBean<>();
        int limit = 6;
        pageBean.setLimit(limit);
        pageBean.setPage(page);

        ProductDao productDao = new ProductDaoImpl();

        int totalCount = productDao.findAll().size();
        pageBean.setTotalCount(totalCount);
        // 封装总页数:(根据总记录数进行计算)
        int totalPage = 0;
        if (totalCount % limit == 0) {
            totalPage = totalCount / limit;
        } else {
            totalPage = totalCount / limit + 1;
        }
        pageBean.setTotalPage(totalPage);
        // 封装每页显示数据的集合:select * from xx limit begin,limit;
        int begin = (page - 1) * limit;
        List<Product> list = productDao.findByPage(begin, limit);
        pageBean.setList(list);
        return pageBean;

    }
}
