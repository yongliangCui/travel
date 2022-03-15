package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {
    /**
     * 按照名称查找数据
     * @param username 用户名称
     * @return
     */
    public User findByUsername(String username);

    /**
     * 保存数据
     * @param user
     */
    void save(User user);
}
