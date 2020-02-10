package com.ic.o2o.service.impl;

import com.ic.o2o.dao.ShopCategoryDao;
import com.ic.o2o.entity.ShopCategory;
import com.ic.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

    @Autowired
    ShopCategoryDao shopCategoryDao;

    @Override
    public List<ShopCategory> getShopCAtegoryList(ShopCategory shopCategoryCondition) {
        return shopCategoryDao.queryShopCategory(shopCategoryCondition);
    }

}
