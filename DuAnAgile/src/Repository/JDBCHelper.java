package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Repository.DBContext;

public class JDBCHelper {

    public static ResultSet excuteQuery(String sql, Object... args) {
        Connection connection = null;
//        Nhận dữ liệu kết quả của câu truy vấn dùng: ResultSet
        ResultSet resultSet = null;
//        Thực thi câu truy vấn có tham số dùng: PreparedStatement
        PreparedStatement preparedStatement = null;

        try {
            //        Gọi phương thức getConnection từ DBcontext
            connection = DBContext.getConnection();
        } catch (Exception ex) {
            Logger.getLogger(JDBCHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
//                Gán giá trị cho các tham số
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultSet;
    }

    public static int excuteUpdate(String sql, Object... args) {
        Connection connection = null;
        int row = 0;
//        Thực thi câu truy vấn có tham số dùng: PreparedStatement
        PreparedStatement preparedStatement = null;

        try {
            //        Gọi phương thức getConnection từ DBcontext
            connection = DBContext.getConnection();
        } catch (Exception ex) {
            Logger.getLogger(JDBCHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
//                Gán giá trị cho các tham số
                preparedStatement.setObject(i + 1, args[i]);
            }
            row = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
}
