package com.ic.o2o.service;

import com.ic.o2o.entity.ShopCategory;

import java.util.List;

public interface ShopCategoryService {
    List<ShopCategory> getShopCAtegoryList(ShopCategory shopCategoryCondition);
}
