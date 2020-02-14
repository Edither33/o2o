package com.ic.o2o.dao;

import com.ic.o2o.BaseTest;
import com.ic.o2o.entity.Product;
import com.ic.o2o.entity.ProductImg;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductImgDaoTest extends BaseTest {
    @Autowired
    ProductImgDao productImgDao;

    @Test
    public void testABatchInsert() {
        ProductImg productImg1 = new ProductImg();
        productImg1.setImgAddr("图片1");
        productImg1.setImgDesc("测试图片1");
        productImg1.setCreateTime(new Date());
        productImg1.setPriority(1);
        productImg1.setProductId(1L);
        ProductImg productImg2 = new ProductImg();
        productImg2.setImgAddr("图片2");
        productImg2.setImgDesc("测试图片2");
        productImg2.setCreateTime(new Date());
        productImg2.setPriority(1);
        productImg2.setProductId(1L);
        List<ProductImg> productImgList = new ArrayList<>();
        productImgList.add(productImg1);
        productImgList.add(productImg2);
        int effNum = productImgDao.batchInsertProductImg(productImgList);
        assertEquals(2, effNum);
    }

    @Test
    public void testBBatchDelete() {
        long productId = 1;
        int effNum = productImgDao.deleteProductImgByProductId(productId);
        assertEquals(2, effNum);
    }
}
