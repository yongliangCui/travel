package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    private UserService userService = new UserServiceImpl();
    /**
     * 登录功能
     * @param request
     * @param response
     * @throws IOException
     */
    public void login(HttpServletRequest request , HttpServletResponse response) throws IOException {
//        UserService userService = new UserServiceImpl();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        System.out.println(username);
        ResultInfo resultInfo = new ResultInfo();
        if (username == "" || password == ""){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("账户名和密码不能为空");
        }else{
            User user = userService.login(username, password);
            if (user == null){
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("用户名和密码不正确");
            }else{
                resultInfo.setFlag(true);
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(resultInfo);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * 注册
     * @param request
     * @param response
     * @throws IOException
     */
    public void regist(HttpServletRequest request , HttpServletResponse response) throws IOException {
//      提取数据
        Map<String,String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
//        System.out.println(user);
//        UserService userService = new UserServiceImpl();
        boolean falg = userService.regist(user);
        ResultInfo info = new ResultInfo();
        if (falg){
            info.setFlag(true);
        }else{
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * 激活账号
     * @param request
     * @param response
     * @throws IOException
     */
    public void activeUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        UserService userService = new UserServiceImpl();
        String code = request.getParameter("code");
        boolean active = userService.active(code);
        ResultInfo Info = new ResultInfo();
        if (active){
            Info.setFlag(true);
            Info.setErrorMsg("激活失败，不要随便修改信息");
        }else{
            Info.setFlag(false);
            Info.setErrorMsg("激活成功");
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(Info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
