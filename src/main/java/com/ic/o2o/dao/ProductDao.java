package com.ic.o2o.dao;

import com.ic.o2o.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {
    /**
     * 添加商品
     *
     * @param product
     * @return
     */
    int insertProduct(Product product);

    /**
     * 通过商品id查询商品信息
     *
     * @param productId
     * @return
     */
    Product queryProductById(long productId);

    /**
     * 更新商品信息
     *
     * @param product
     * @return
     */
    int updateProduct(Product product);

    /**
     * 删除商品
     *
     * @param productId
     * @return
     */
    int deleteProductImgByProductId(long productId);

    List<Product> queryProductList(@Param("productCondition") Product productCondition
            , @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    int queryProductCount(@Param("productCondition") Product productCondition);

    int updateProductCategoryToNull(long productCategoryId);
}
