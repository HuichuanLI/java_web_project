package web.action;

import domain.Category;
import service.CategoryService;
import service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(value = "/CategoryServlet")
public class CategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodName = request.getParameter("method");
        System.out.println(methodName);
        if ("findAll".equals(methodName)) {
            // 查询所有分类:
            findAll(request, response);
        } else if ("save".equals(methodName)) {
            System.out.println("ok");
            save(request, response);
        } else if ("update".equals(methodName)) {
            // 修改分类的方法
            update(request, response);
        }else if("delete".equals(methodName)){
            // 删除分类的方法:
            try {
                delete(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1.接收数据:
        Integer cid = Integer.parseInt(request.getParameter("cid"));
        String cname = request.getParameter("cname");
        String cdesc = request.getParameter("cdesc");
        // 2.封装数据:
        Category category = new Category();
        category.setCid(cid);
        category.setCname(cname);
        category.setCdesc(cdesc);
        // 调用业务层处理数据:
        CategoryService categoryService = new CategoryServiceImpl();
        categoryService.update(category);
        // 页面跳转
        response.sendRedirect(request.getContextPath() + "/CategoryServlet?method=findall");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodName = request.getParameter("method").trim();


        if ("findall".equals(methodName)) {
            // 查询所有分类:
            findAll(request, response);
        } else if ("saveui".equals(methodName)) {
            saveui(request, response);
        } else if ("save".equals(methodName)) {
            System.out.println("ok");
            save(request, response);
        } else if ("edit".equals(methodName)) {
            edit(request, response);
        }else if("delete".equals(methodName)){
            // 删除分类的方法:
            try {
                delete(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveui(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin/category_add.jsp").forward(request, response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cname = request.getParameter("cname");
        String cdesc = request.getParameter("cdesc");
        Category category = new Category();
        category.setCname(cname);
        category.setCdesc(cdesc);

        CategoryService categoryService = new CategoryServiceImpl();
        categoryService.save(category);
        response.sendRedirect(request.getContextPath() + "/CategoryServlet?method=findall");


    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收参数
        // 封装数据
        // 调用业务层处理数据
        System.out.println("CategoryServlet的findAll方法执行了...");
        CategoryService categoryService = new CategoryServiceImpl();
        List<Category> list = categoryService.findAll();

        // 页面跳转
        request.setAttribute("list", list);
        request.getRequestDispatcher("/admin/category_list.jsp").forward(request, response);
    }


    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cid = Integer.parseInt(request.getParameter("cid"));
        CategoryService categoryService = new CategoryServiceImpl();
        Category category = categoryService.findone(cid);
        request.setAttribute("category", category);
        request.getRequestDispatcher("/admin/category_update.jsp").forward(request, response);

    }

    /**
     * 后台分类管理删除分类的方法
     * @param request
     * @param response
     * @throws IOException
     */
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        // 接收数据:
        Integer cid = Integer.parseInt(request.getParameter("cid"));
        // 处理数据:
        CategoryService categoryService = new CategoryServiceImpl();
        categoryService.delete(cid);
        // 页面跳转
        response.sendRedirect(request.getContextPath()+"/CategoryServlet?method=findall");
    }


}
