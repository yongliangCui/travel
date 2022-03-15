package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

public interface UserService {
    /**
     * 调用dao中的方法，查看或者保存用户数据
     * @param user
     * @return
     */
    public boolean regist(User user);
}
