package com.ic.o2o.util;

import com.ic.o2o.dto.ImageHolder;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    public static void deleteFileOrPath(String storePath) {
        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        if (fileOrPath.exists()) {
            if (fileOrPath.isDirectory()) {
                File files[] = fileOrPath.listFiles();
                for (int i = 0; i < files.length; i++) {
                    files[i].delete();
                }
            }
            fileOrPath.delete();
        }
    }

    public static File transferCommonsMultiparFile(CommonsMultipartFile cFile) {
        File newFile = new File(cFile.getOriginalFilename());
        try {
            cFile.transferTo(newFile);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return newFile;
    }

    public static String generateThumbnail(ImageHolder imageHolder, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(imageHolder.getImageName());
        makeDirPath(targetAddr);
        String relative = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is:" + relative);
        File dest = new File(PathUtil.getImgBasePath() + relative);
        logger.debug("current completeAddr is:" + PathUtil.getImgBasePath() + relative);
        try {
            Thumbnails.of(imageHolder.getImage()).size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT,
                            ImageIO.read(new File(basePath + "watermark.jpg")), 0.25f)
                    .outputQuality(0.8f)
                    .toFile(dest);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relative;
    }

    /**
     * 创建目标路径所涉及的路径
     *
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    /**
     * 获取文件流的拓展名
     *
     * @return
     */
    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成随机文件名：当前日期+随机五位数
     *
     * @return
     */
    public static String getRandomFileName() {
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }

    public static String generateNormalImg(ImageHolder imageHolder, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(imageHolder.getImageName());
        makeDirPath(targetAddr);
        String relative = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is:" + relative);
        File dest = new File(PathUtil.getImgBasePath() + relative);
        logger.debug("current completeAddr is:" + PathUtil.getImgBasePath() + relative);
        try {
            Thumbnails.of(imageHolder.getImage()).size(337, 640)
                    .watermark(Positions.BOTTOM_RIGHT,
                            ImageIO.read(new File(basePath + "watermark.jpg")), 0.25f)
                    .outputQuality(0.9f)
                    .toFile(dest);
        } catch (IOException e) {
            logger.error(e.toString());
            throw new RuntimeException("创建缩图片失败" + e.toString());
        }
        return relative;
    }
}
