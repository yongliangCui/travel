package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int findCount(int cid) {
        String sql = "selet count(*) from tab_route where cid = ?";
        Integer count = template.queryForObject(sql, Integer.class, cid);
        return count;
    }

    @Override
    public List<Route> findRoute(int cid, int currentpage, int pagesize) {
        String sql = "selet * from tab_route where cid = ? limit ? , ?";
        List<Route> list = template.query(sql,
                new BeanPropertyRowMapper<Route>(Route.class), cid, currentpage, pagesize);

        return list;
    }
}
