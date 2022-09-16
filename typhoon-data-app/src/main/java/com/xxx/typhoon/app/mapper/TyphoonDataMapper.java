package com.xxx.typhoon.app.mapper;

import com.xxx.typhoon.app.entity.TyphoonData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2022-08-31
 */
@Mapper
public interface TyphoonDataMapper extends BaseMapper<TyphoonData> {

    /**
     * 台风数据批量插入
     * @param dataList
     * @return
     */
    Integer insertTyphoonDataBatch(List<TyphoonData> dataList);

}
