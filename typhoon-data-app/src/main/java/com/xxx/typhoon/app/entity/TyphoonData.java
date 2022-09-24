package com.xxx.typhoon.app.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.xxx.tphoon.fileOperation.annotation.CSVField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@ToString
@TableName("typhoon_data")
public class TyphoonData implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "typhoon_data_id", type = IdType.ASSIGN_ID)
    @ExcelIgnore
    private Long typhoonDataId;

    @TableField("publish_time")
    @ExcelProperty("publish_time")
    @CSVField("publish_time")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date publishTime;

    @TableField("user_name")
    @ExcelProperty("user_name")
    @CSVField("user_name")
    private String userName;

    @TableField("user_link")
    @ExcelProperty("user_link")
    @CSVField("user_link")
    private String userLink;

    @TableField("content")
    @ExcelProperty("content")
    @CSVField("content")
    private String content;

    @TableField("source")
    @ExcelProperty("source")
    @CSVField("source")
    private String source;

    @TableField("location_url")
    @CSVField("location_url")
    @ExcelProperty("location_url")
    private String locationUrl;

    @TableField("location_name")
    @ExcelProperty("location_name")
    @CSVField("location_name")
    private String locationName;

    @TableField("image_urls")
    @ExcelProperty("image_urls")
    @CSVField("image_urls")
    private String imageUrls;

    @TableField("weibo_link")
    @ExcelProperty("weibo_link")
    @CSVField("weibo_link")
    private String weiboLink;

    @TableField("forward_num")
    @ExcelProperty("forward_num")
    @CSVField("forward_num")
    private Integer forwardNum;

    @TableField("comment_num")
    @ExcelProperty("comment_num")
    @CSVField("comment_num")
    private Integer commentNum;

    @TableField("like_num")
    @ExcelProperty("like_um")
    @CSVField("like_um")
    private Integer likeNum;

    @TableField("typhoon_name")
    private String typhoonName;

    @Version
    @TableField("version")
    private Integer version;

}
