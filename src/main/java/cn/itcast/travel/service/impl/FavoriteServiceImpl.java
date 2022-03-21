package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    @Override
    public boolean find(int rid, int uid) {
        Favorite favorite = favoriteDao.find(rid, uid);
        if (favorite == null){
            return false;
        }
        return true;
    }

    @Override
    public void saveFavorite(int rid, int uid) {
        favoriteDao.saveFavorite(rid,uid);
    }
}
