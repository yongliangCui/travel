package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int findCount(int cid,String rname) {
        String sql = "select count(*) from tab_route where 1=1 ";
        List list = new ArrayList();
        if (cid != 0){
            sql = sql + " and cid = ?";
            list.add(cid);
        }
        if (rname != null && rname.length() > 0 ){
            sql = sql + " and rname like ? ";
            list.add("%"+rname+"%");
        }

        return template.queryForObject(sql,Integer.class,list.toArray());
    }

    /**
     * 查询数据，包含模糊查询
     * @param cid 种类
     * @param start 开始数据
     * @param end  结束数据
     * @param rname 模糊查询的关键字
     * @return
     */
    @Override
    public List<Route> findRoute(int cid, int start, int end,String rname) {
        String sql = "select * from tab_route where 1=1 ";
        List list = new ArrayList<>();
        if (cid != 0){
            sql = sql + " and cid = ? ";
            list.add(cid);
        }
        if (rname != null && rname.length() > 0){
            sql = sql + " and rname like ?";
            list.add("%"+rname+"%");
        }
        sql = sql + " limit ?,? ";
        list.add(start);
        list.add(end);
        List<Route> list1 = null;
        try {
            list1 = template.query(sql,
                    new BeanPropertyRowMapper<Route>(Route.class), list.toArray());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return list1;
    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";
        Route route = null;
        try {
            route = template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class),rid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return route;
    }
}
