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
     * 邮箱的激活唯一code
     * @param code code
     * @return
     */
    public User findByCode(String code);



    User findByUsernameAndPassword(String username, String password);


    /**
     * 保存数据
     * @param user
     */
    void save(User user);

    /**
     * 根据code修改数据信息
     * @param code
     */
    void update(String code);
}
