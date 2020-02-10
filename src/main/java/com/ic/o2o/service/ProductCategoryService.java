package com.ic.o2o.service;

import com.ic.o2o.dto.ProductCategoryExecution;
import com.ic.o2o.entity.ProductCategory;
import com.ic.o2o.exceptions.ProductCategoryOperationException;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> getProductCategoryList(long shopId);

    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
            throws ProductCategoryOperationException;

    ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
            throws ProductCategoryOperationException;
}
