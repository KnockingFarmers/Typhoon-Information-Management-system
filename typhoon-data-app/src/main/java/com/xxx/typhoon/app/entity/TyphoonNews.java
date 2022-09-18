package com.xxx.typhoon.app.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2022-09-02
 */
@Getter
@Setter
@TableName("typhoon_news")
public class TyphoonNews implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "typhoon_news_id", type = IdType.ASSIGN_ID)
    private Integer typhoonNewsId;

    @TableField("news_id")
    @ExcelProperty("微博id")
    private String newsId;

    @TableField("publisher_name")
    @ExcelProperty("发布者昵称")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private String publisherName;

    @TableField("publisher_sex")
    @ExcelProperty("发布者性别")
    private Integer publisherSex;

    @TableField("publisher_area")
    @ExcelProperty("发布者地区")
    private Integer publisherArea;

    @TableField("publisher_follows_num")
    @ExcelProperty("发布者关注数")
    private Long publisherFollowsNum;

    @TableField("publisher_fans _num")
    @ExcelProperty("发布者粉丝数")
    private Long publisherFansNum;

    @TableField("news_text")
    @ExcelProperty("微博正文")
    private String newsText;

    @TableField("image_url")
    @ExcelProperty("原始图片url")
    private String imageUrl;

    @TableField("release_time")
    @ExcelProperty("发布时间")
    private LocalDateTime releaseTime;

    @TableField("release_tool")
    @ExcelProperty("发布工具")
    private String releaseTool;

    @TableField("like_num")
    @ExcelProperty("点赞数")
    private Long likeNum;

    @TableField("forward_num")
    @ExcelProperty("转发数")
    private Long forwardNum;

    @TableField("comments_num")
    @ExcelProperty("评论数")
    private Long commentsNum;

    @TableField("release_location")
    @ExcelProperty("发布位置")
    private String releaseLocation;

    @TableField("pointed_forward_news_original_image_url")
    @ExcelProperty("被转发微博原始图片url")
    private String pointedForwardNewsOriginalImageUrl;

    @TableField("original_news")
    @ExcelProperty("是否为原创微博")
    private Integer originalNews;

    @Version
    @TableField("version")
    private Integer version;


}
