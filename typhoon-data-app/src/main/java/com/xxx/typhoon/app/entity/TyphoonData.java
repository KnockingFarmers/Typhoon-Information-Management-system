package com.xxx.typhoon.app.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2022-08-31
 */
@Getter
@Setter
@TableName("typhoon_data")
public class TyphoonData implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "typhoon_data_id", type = IdType.ASSIGN_ID)
    @ExcelIgnore
    private Long typhoonDataId;

    @TableField("publish_time")
    @ExcelProperty("publishTime")
    private Date publishTime;

    @TableField("user_name")
    @ExcelProperty("user_name")
    private String userName;

    @TableField("user_link")
    @ExcelProperty("user_link")
    private String userLink;

    @TableField("content")
    @ExcelProperty("content")
    private String content;

    @TableField("source")
    @ExcelProperty("source")
    private String source;

    @TableField("location_url")
    @ExcelProperty("location_url")
    private String locationUrl;

    @TableField("location_name")
    @ExcelProperty("location_name")
    private String locationName;

    @TableField("image_urls")
    @ExcelProperty("image_urls")
    private String imageUrls;

    @TableField("weibo_link")
    @ExcelProperty("weibo_link")
    private String weiboLink;

    @TableField("forward_num")
    @ExcelProperty("forward_num")
    private Integer forwardNum;

    @TableField("comment_num")
    @ExcelProperty("comment_num")
    private Integer commentNum;

    @TableField("like_num")
    @ExcelProperty("like_um")
    private Integer likeNum;

    @TableField("typhoon_name")
    private String typhoonName;

}
