package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.omg.CORBA.PUBLIC_MEMBER;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet("/findRoute/*")
public class RouteServlet extends BaseServlet{
    private RouteService routeService = new RouteServiceImpl();

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        int cid = Integer.parseInt(request.getParameter("cid"));
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        String rname = (String)request.getParameter("rname");
        System.out.println(rname);
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        try {
            mapper.writeValue(response.getWriter(),routeService.find(cid,currentPage,pageSize,rname));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        int rid = Integer.parseInt(request.getParameter("rid"));
        Route rout = routeService.findOne(rid);
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        try {
            mapper.writeValue(response.getWriter(),rout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
