package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategroyDao;
import cn.itcast.travel.dao.impl.CategoryDaoimpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategroyDao categroyDao = new CategoryDaoimpl();

    @Override
    public List<Category> findAll() {
        List<Category> list = categroyDao.findAll();
        return list;
    }
}
