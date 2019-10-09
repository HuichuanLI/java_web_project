package dao.impl;

import dao.CategoryDao;
import domain.Category;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> findAll() {
        System.out.println("CategoryDao的findAll方法执行了...");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Category> list = null;
        try {
            // 1.获得连接
            conn = JDBCUtils.getConnection();
            // 2.编写SQL
            String sql = "select * from category";
            // 3.预编译SQL
            pstmt = conn.prepareStatement(sql);
            // 4.设置参数
            // 5.执行SQL
            rs = pstmt.executeQuery();
            // 6.结果处理
            list = new ArrayList<Category>();
            while (rs.next()) {
                Category category = new Category();
                category.setCid(rs.getInt("cid"));
                category.setCname(rs.getString("cname"));
                category.setCdesc(rs.getString("cdes"));

                list.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7.释放资源
            JDBCUtils.release(rs, pstmt, conn);
        }

        return list;
    }

    @Override
    public void save(Category category) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // 1.获得连接
            conn = JDBCUtils.getConnection();
            // 2.编写SQL
            String sql = "insert into category values(null,?,?)";
            // 3.预编译SQL
            pstmt = conn.prepareStatement(sql);
            // 4.设置参数
            pstmt.setString(1, category.getCname());
            pstmt.setString(2, category.getCdesc());
            // 5.执行SQL
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7.释放资源
            JDBCUtils.release(pstmt, conn);
        }
    }

    @Override
    public Category findone(int cid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Category category = null;
        try {
            // 1.获得连接
            conn = JDBCUtils.getConnection();
            // 2.编写SQL
            String sql = "select * from category where cid = ?";
            // 3.预编译SQL
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cid);
            // 4.设置参数
            // 5.执行SQL
            rs = pstmt.executeQuery();
            // 6.结果处理

            if (rs.next()) {
                category = new Category();
                category.setCid(rs.getInt("cid"));
                category.setCname(rs.getString("cname"));
                category.setCdesc(rs.getString("cdesc"));
                return category;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7.释放资源
            JDBCUtils.release(rs, pstmt, conn);
        }

        return null;
    }

    @Override
    public void update(Category category) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // 获得连接
            conn = JDBCUtils.getConnection();
            // 编写SQL:
            String sql = "update category set cname = ?,cdesc = ? where cid = ?";
            // 预编译SQL
            pstmt = conn.prepareStatement(sql);
            // 设置参数:
            pstmt.setString(1, category.getCname());
            pstmt.setString(2, category.getCdesc());
            pstmt.setInt(3, category.getCid());
            // 执行SQL
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(pstmt, conn);
        }
    }

    @Override
    public void delete(Integer cid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // 获得连接
            conn = JDBCUtils.getConnection();
            // 编写SQL:
            String sql = "delete from category where cid = ?";
            // 预编译SQL:
            pstmt = conn.prepareStatement(sql);
            // 设置参数:
            pstmt.setInt(1, cid);
            // 执行SQL：
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(pstmt, conn);
        }
    }


}
