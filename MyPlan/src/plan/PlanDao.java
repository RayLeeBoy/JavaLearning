package plan;

import Utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PlanDao {

    // 声明 JdbcTemplate 对象
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    public Boolean check(Plan createPlan) {
        try {
            // 编写sql语句: 添加一行数据
            String sql = "select * from myPlan where name = ?";
            List<Plan> list = template.query(sql, new BeanPropertyRowMapper<>(Plan.class), createPlan.getName());
            if (list.size() > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Boolean create(Plan createPlan) {
        try {
            // 编写sql语句: 添加一行数据
            String sql = "insert into myPlan (name, content) values (?, ?)";
            template.update(sql, createPlan.getName(), createPlan.getContent());
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Boolean addTime(Plan plan) {
        try {
            // 编写sql语句: 更新一行数据
            String sql = "update myPlan set totalMinutes = ?, count = ? where name = ?";
            template.update(sql, plan.getTotalMinutes(), plan.getCount(), plan.getName());
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public List<Plan> get() {
        try {
            // 编写sql语句: 添加一行数据
            String sql = "select * from myPlan";
            List<Plan> list = template.query(sql, new BeanPropertyRowMapper<>(Plan.class));
            if (list.size() > 0) {
                return list;
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
