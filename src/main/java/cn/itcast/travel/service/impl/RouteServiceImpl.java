package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    @Override
    public PageBean<Route> find(int cid, int currentPage, int pageSize,String rname) {
        PageBean<Route> pageBean = new PageBean<>();
//      返回查询到全部数据数
        int count = routeDao.findCount(cid,rname);
        pageBean.setTotalCount(count);
//      返回查询到的页数
        int page = count % pageSize == 0 ? count / pageSize : (count / pageSize)+1;
        pageBean.setTotalPage(page);
//      当前页码
        pageBean.setCurrentPage(currentPage);
//      每页数据数
        pageBean.setPageSize(pageSize);
//      返回查询到的当前页数据
        int start = (currentPage - 1) * pageSize;
        int end = (currentPage - 1) * pageSize +pageSize;
        List<Route> route = routeDao.findRoute(cid, start, end, rname);
        pageBean.setList(route);
        System.out.println(pageBean);
        return pageBean;
    }

    @Override
    public Route findOne(int rid) {
        Route route = routeDao.findOne(rid);
        int sid = route.getSid();
        Seller seller = sellerDao.findone(sid);
        route.setSeller(seller);
        List<RouteImg> list = routeImgDao.findOne(rid);
        route.setRouteImgList(list);
        return route;
    }
}
