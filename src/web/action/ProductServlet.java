package web.action;

import domain.Category;
import domain.Product;
import service.CategoryService;
import service.ProductService;
import service.impl.CategoryServiceImpl;
import service.impl.ProductServiceImpl;
import utils.UploadUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodName = request.getParameter("method");
        if ("findall".equals(methodName)) {
            findAll(request, response);
        } else if ("saveui".equals(methodName)) {
            saveUI(request, response);
        } else if ("save".equals(methodName)) {
            save(request, response);
        } else if ("edit".equals(methodName)) {
            edit(request, response);
        } else if ("update".equals(methodName)) {
            update(request, response);
        }
    }

    /**
     * 后台商品管理，保存商品的方法
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("save=============");
        // 文件上传
        Map<String, String> map = UploadUtils.uploadFile(request);
        // 将数据完成封装
        Product product = new Product();
        product.setPname(map.get("pname"));
        product.setAuthor(map.get("author"));
        product.setPrice(Double.parseDouble(map.get("price")));
        product.setDescription(map.get("description"));
        product.setFilename(map.get("filename"));
        product.setPath(map.get("path"));
        product.getCategory().setCid(Integer.parseInt(map.get("cid")));
        // 处理数据
        ProductServiceImpl productService = new ProductServiceImpl();
        productService.save(product);
        // 页面跳转：
        response.sendRedirect(request.getContextPath() + "/ProductServlet?method=findall");
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductServiceImpl productService = new ProductServiceImpl();
        List<Product> list = productService.findAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/admin/product_list.jsp").forward(request, response);
    }

    private void saveUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 查询所有分类的信息:
        CategoryService categoryService = new CategoryServiceImpl();
        List<Category> list = categoryService.findAll();
        // 页面跳转
        request.setAttribute("categoryList", list);
        request.getRequestDispatcher("/admin/product_add.jsp").forward(request, response);
    }

    /**
     * 后台商品管理，修改商品的方法
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收数据:
        Integer pid = Integer.parseInt(request.getParameter("pid"));
        // 调用业务层处理数据:
        ProductServiceImpl productService = new ProductServiceImpl();
        Product product = productService.findOne(pid);
        System.out.println(product);
        // 查询所有分类:
        CategoryService categoryService = new CategoryServiceImpl();
        List<Category> categoryList = categoryService.findAll();
        // 页面跳转
        request.setAttribute("product", product);
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("/admin/product_update.jsp").forward(request, response);
    }


    /**
     * 后台商品管理，修改商品的方法
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 接收数据
        Map<String, String> map = UploadUtils.uploadFile(request);
        // 封装数据
        Product product = new Product();
        product.setPid(Integer.parseInt(map.get("pid")));
        product.setPname(map.get("pname"));
        product.setAuthor(map.get("author"));
        product.setPrice(Double.parseDouble(map.get("price")));
        product.setDescription(map.get("description"));
        product.setFilename(map.get("filename"));
        product.setPath(map.get("path"));
        product.getCategory().setCid(Integer.parseInt(map.get("cid")));
        // 处理数据
        ProductServiceImpl productService = new ProductServiceImpl();
        productService.update(product);
        //  页面跳转
        response.sendRedirect(request.getContextPath() + "/ProductServlet?method=findall");
    }

}
