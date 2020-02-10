package com.ic.o2o.service;

import com.ic.o2o.dto.ShopExecution;
import com.ic.o2o.entity.Shop;
import com.ic.o2o.exceptions.ShopOperationException;

import java.io.File;
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
     * @param shopImgInputStream
     * @param fileName
     * @return
     * @throws ShopOperationException
     */
    public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;

    /**
     * 注册商铺
     *
     * @param shop
     * @param shopImage
     * @param fileName
     * @return
     * @throws ShopOperationException
     */
    public ShopExecution addShop(Shop shop, InputStream shopImage, String fileName) throws ShopOperationException;

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
