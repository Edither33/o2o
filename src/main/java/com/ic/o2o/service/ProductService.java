package com.ic.o2o.service;

import com.ic.o2o.dto.ImageHolder;
import com.ic.o2o.dto.ProductExecution;
import com.ic.o2o.entity.Product;
import com.ic.o2o.exceptions.ProductOperationException;

import java.io.InputStream;
import java.util.List;

public interface ProductService {
    /**
     * @param productCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);

    /**
     * 添加商品图片以及图片处理信息
     *
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product, ImageHolder imageHolder
            , List<ImageHolder> imageHolderList) throws ProductOperationException;

    /**
     * 修改商品信息
     *
     * @param product
     * @param imageHolder
     * @param imageHolderList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution modifyProduct(Product product, ImageHolder imageHolder
            , List<ImageHolder> imageHolderList) throws ProductOperationException;

    /**
     * 获取指定id商品的信息
     *
     * @param productId
     * @return
     */
    Product getProductById(long productId);
}
