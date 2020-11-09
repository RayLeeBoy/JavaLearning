package user;

import Utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.util.List;

public class UserDao {

    // 声明 JdbcTemplate 对象
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    public User login(User loginUser) {

        try {
            // 编写sql语句
            String sql = "select * from t_user where username = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),
                    loginUser.getUsername(), loginUser.getPassword());
            return user;
        } catch (DataAccessException e) {
            System.out.println(e);
            return null;
        }
    }

    public Boolean check(User registUser) {
        try {
            // 编写sql语句: 添加一行数据
            String sql = "select * from t_user where username = ?";
//            user.User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(user.User.class),
//                    registUser.getUsername());
            List<User> list = template.query(sql, new BeanPropertyRowMapper<>(User.class), registUser.getUsername());
            if (list.size() > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Boolean regist(User registUser) {
        try {
            // 编写sql语句: 添加一行数据
            String sql = "insert into t_user (username, password) values (?, ?)";
            template.update(sql, registUser.getUsername(), registUser.getPassword());
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
