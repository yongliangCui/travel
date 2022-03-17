package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public boolean regist(User user) {
        User resultUser = userDao.findByUsername(user.getUsername());
        if (resultUser != null){
            return false;
        }
//      设置唯一的Uuid
        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");
        userDao.save(user);
//      定义邮件正文，超链接
        String content = "<a href='http://localhost:80/travel/activeUserServlet?code="+user.getCode()+"'>点击激活账户【哈哈哈】</a>";
        MailUtils.sendMail(user.getEmail(),content,"测试用");

        return true;
    }

    @Override
    public boolean active(String code) {
        User result = userDao.findByCode(code);
        if (result != null){
            userDao.update(code);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean login(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username, password);
        if (user != null){
//          登录成功
            return true;
        }else{
//          登录失败
            return false;
        }
    }
}
