package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    public int findCount(int cid);
    public List<Route> findRoute(int cid,int start,int pagesize);
}
