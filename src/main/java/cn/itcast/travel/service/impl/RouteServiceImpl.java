package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;

public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();
    @Override
    public PageBean<Route> find(int cid, int currentPage, int pageSize) {
        PageBean<Route> pageBean = new PageBean<>();
        int count = routeDao.findCount(cid);
        pageBean.setTotalCount(count);
        int page = count % pageSize == 0 ? count / pageSize : (count / pageSize)+1;
        pageBean.setTotalPage(page);
        return null;
    }
}
