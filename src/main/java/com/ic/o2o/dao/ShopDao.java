package com.ic.o2o.dao;

import com.ic.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {
    /**
     * 新增店铺
     *
     * @param shop
     * @return int
     */
    int insertShop(Shop shop);

    /**
     * 更新商铺信息
     *
     * @param shop
     * @return int
     */
    int updateShop(Shop shop);

    /**
     * 根据shopid查询商店信息
     *
     * @param shopId
     * @return
     */
    Shop queryShopById(long shopId);

    /**
     * 分页查询店铺，可输入的条件有：店铺名（模糊），店铺状态，店铺类别，区域id，owner
     *
     * @param shopCondition
     * @param rowIndex      从第几行开始
     * @param pageSize      返回条数
     * @return
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition
            , @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * 查询总数
     *
     * @param shopcondition
     * @return
     */
    int queryShopCount(@Param("shopCondition") Shop shopcondition);
}
