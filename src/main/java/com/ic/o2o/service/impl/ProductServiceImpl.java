package com.ic.o2o.service.impl;

import com.ic.o2o.dao.ProductDao;
import com.ic.o2o.dao.ProductImgDao;
import com.ic.o2o.dto.ImageHolder;
import com.ic.o2o.dto.ProductExecution;
import com.ic.o2o.entity.Product;
import com.ic.o2o.entity.ProductImg;
import com.ic.o2o.enums.ProductStateEnum;
import com.ic.o2o.exceptions.ProductOperationException;
import com.ic.o2o.service.ProductService;
import com.ic.o2o.util.ImageUtil;
import com.ic.o2o.util.PageCalculator;
import com.ic.o2o.util.PathUtil;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
        int count = productDao.queryProductCount(productCondition);
        ProductExecution pe = new ProductExecution();
        pe.setCount(count);
        pe.setProductList(productList);
        return pe;
    }

    @Override
    @Transactional
    public ProductExecution addProduct(Product product, ImageHolder imageHolder, List<ImageHolder> imageHolderList) throws ProductOperationException {
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            product.setEnableStatus(1);
            if (imageHolder != null) {
                addThumbnail(product, imageHolder);
            }
            try {
                int effNum = productDao.insertProduct(product);
                if (effNum <= 0) {
                    throw new ProductOperationException("商品创建失败");
                }

            } catch (Exception e) {
                throw new ProductOperationException("创建商品失败" + e.getMessage());
            }
            if (imageHolderList != null && imageHolderList.size() > 0) {
                addProductImgList(product, imageHolderList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS, product);
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }

    }

    @Override
    public ProductExecution modifyProduct(Product product, ImageHolder imageHolder, List<ImageHolder> imageHolderList) throws ProductOperationException {
        if (product != null && product.getShop() != null
                && product.getShop().getShopId() != null) {
            product.setLastEditTime(new Date());
            if (imageHolder != null) {
                Product tempProduct = productDao.queryProductById(product.getProductId());
                if (tempProduct.getImgAddr() != null) {
                    ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
                }
                addThumbnail(product, imageHolder);
            }
            if (imageHolderList != null && imageHolderList.size() > 0) {
                deleteProductImgList(product.getProductId());
                addProductImgList(product, imageHolderList);
            }
            try {
                int effNum = productDao.updateProduct(product);
                if (effNum <= 0) {
                    throw new ProductOperationException("更新商品信息失败");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS, product);
            } catch (Exception e) {
                throw new ProductOperationException("更新商品信息失败" + e.toString());
            }
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    private void deleteProductImgList(Long productId) {
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
        for (ProductImg productImg : productImgList) {
            ImageUtil.deleteFileOrPath(productImg.getImgAddr());
        }
        productImgDao.deleteProductImgByProductId(productId);
    }

    @Override
    public Product getProductById(long productId) {
        return productDao.queryProductById(productId);
    }

    private void addThumbnail(Product product, ImageHolder imageHolder) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(imageHolder, dest);
        product.setImgAddr(thumbnailAddr);
    }

    private void addProductImgList(Product product, List<ImageHolder> imageHolderList) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<>();
        for (ImageHolder productImgHolder : imageHolderList) {
            String imgAddr = ImageUtil.generateNormalImg(productImgHolder, dest);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(imgAddr);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            productImgList.add(productImg);
        }
        if (productImgList.size() > 0) {
            try {
                int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("创建商品详情图片失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品详情图片失败:" + e.toString());
            }
        }
    }
}
