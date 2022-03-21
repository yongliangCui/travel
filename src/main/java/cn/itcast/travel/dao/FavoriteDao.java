package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

public interface FavoriteDao {
    public Favorite find(int rid ,int uid);
    void saveFavorite(int rid ,int uid);
}
