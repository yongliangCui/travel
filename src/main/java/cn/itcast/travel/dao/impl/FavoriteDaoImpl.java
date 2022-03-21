package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Favorite find(int rid, int uid) {
        String sql = "select * from tab_favorite where rid = ? and uid = ? ";
        Favorite favorite = null;
        try {
           favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return favorite;
    }

    @Override
    public void saveFavorite(int rid, int uid) {
        String sql = "insert into tab_favorite(rid,uid) values(?,?)";
        int update = template.update(sql,rid,uid);
    }
}
