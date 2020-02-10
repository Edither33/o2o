package com.ic.o2o.dao;

import com.ic.o2o.entity.Area;

import java.util.List;

public interface AreaDao {
    /**
     * 列出区域列表
     *
     * @return
     */
    List<Area> queryArea();
}
