package cn.itcast.travel.service;

public interface FavoriteService {
    boolean find(int rid, int uid);
    void saveFavorite(int rid, int uid);
}
