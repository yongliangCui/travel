package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

public interface UserService {
    /**
     * 调用dao中的方法，查看或者保存用户数据
     * @param user
     * @return
     */
    public boolean regist(User user);

    /**
     * 更新status状态，激活账户
     * @param code
     * @return
     */
    public boolean active(String code);

    /**
     * 使用username和password进行登录验证
     * @param username 用户名
     * @param password 用户密码
     * @return 是否登陆成功
     */
    public User login(String username, String password);

}
