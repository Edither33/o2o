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
}
