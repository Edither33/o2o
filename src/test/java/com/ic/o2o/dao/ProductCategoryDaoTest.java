package com.ic.o2o.dao;

import com.ic.o2o.BaseTest;
import com.ic.o2o.entity.ProductCategory;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void testBQueryProductCategoryList() {
        long shopId = 28;
        List<ProductCategory> productCategories = productCategoryDao.queryProductCategoryList(shopId);

    }

    @Test
    public void testABatchInsert() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCreateTime(new Date());
        productCategory.setPriority(1);
        productCategory.setProductCategoryName("9999");
        productCategory.setShopId(28L);
        ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setCreateTime(new Date());
        productCategory1.setPriority(1);
        productCategory1.setProductCategoryName("666");
        productCategory1.setShopId(28L);
        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(productCategory);
        productCategoryList.add(productCategory1);
        int num = productCategoryDao.batchInsertProductCategory(productCategoryList);
        assertEquals(2, num);
    }

    @Test
    public void testCDelete() {
        long shopId = 28L;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
        for (ProductCategory pc : productCategoryList) {
            if ("9999".equals(pc.getProductCategoryName()) || "666".equals(pc.getProductCategoryName())) {
                int effNum = productCategoryDao.deleteProductCategory(pc.getProductCategoryId(), shopId);
                assertEquals(1, effNum);
            }
        }

    }
}
