package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/Favorite/*")
public class FavoriteServlet extends BaseServlet {
    private FavoriteService favoriteService = new FavoriteServiceImpl();
    public void findFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        int rid = Integer.parseInt(request.getParameter("rid"));
        int uid = Integer.parseInt(request.getParameter("uid"));
        boolean flag = favoriteService.find(rid, uid);
        ResultInfo info = new ResultInfo();
        response.setContentType("application/json;charset=utf-8");
        info.setFlag(flag);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),info);
    }
    public void saveFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        int rid = Integer.parseInt(request.getParameter("rid"));
        int uid = Integer.parseInt(request.getParameter("uid"));
        favoriteService.saveFavorite(rid,uid);
        response.setContentType("application/json;charset=utf-8");
        ResultInfo info = new ResultInfo();
        info.setFlag(true);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),info);
    }
}
