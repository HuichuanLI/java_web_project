package dao.impl;

import dao.UserDao;
import domain.User;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {

    @Override
    public User login(User user) {
        // 获得链接
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from user where username = ? and password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                User existUser = new User();
                existUser.setUid(rs.getInt("uid"));
                existUser.setPassword(rs.getString("password"));
                existUser.setUsername(rs.getString("username"));
                return existUser;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, pstmt, conn);
        }
        // 编写SQL
        //预编译SQL

        return null;
    }
}
