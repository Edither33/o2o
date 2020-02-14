package com.ic.o2o.service;

import com.ic.o2o.dto.ImageHolder;
import com.ic.o2o.dto.ShopExecution;
import com.ic.o2o.entity.Shop;
import com.ic.o2o.exceptions.ShopOperationException;

import java.io.InputStream;

public interface ShopService {
    /**
     * 按shopid获取商铺信息
     *
     * @param shopId
     * @return
     */
    public Shop getShopById(long shopId);

    /**
     * 修改店铺
     *
     * @param shop
     * @param imageHolder
     * @return
     * @throws ShopOperationException
     */
    public ShopExecution modifyShop(Shop shop, ImageHolder imageHolder) throws ShopOperationException;

    /**
     * 注册商铺
     *
     * @param shop
     * @param imageHolder
     * @return
     * @throws ShopOperationException
     */
    public ShopExecution addShop(Shop shop, ImageHolder imageHolder) throws ShopOperationException;

    /**
     * 根据Shopcondition分页查询
     *
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);

}
