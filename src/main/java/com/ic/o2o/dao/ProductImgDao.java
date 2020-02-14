package com.ic.o2o.dao;

import com.ic.o2o.entity.ProductImg;

import java.util.List;

public interface ProductImgDao {
    /**
     * 批量添加商品图片
     *
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    /**
     * 删除指定商品的详情图
     *
     * @param productId
     * @return
     */
    int deleteProductImgByProductId(long productId);

    /**
     * 列出某个商品的详情图列表
     *
     * @param productId
     * @return
     */
    List<ProductImg> queryProductImgList(long productId);

}
