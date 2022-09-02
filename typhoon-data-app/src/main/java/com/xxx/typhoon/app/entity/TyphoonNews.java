package com.xxx.typhoon.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
    private String newsId;

    @TableField("publisher_name")
    private String publisherName;

    @TableField("publisher_follows_num")
    private Long publisherFollowsNum;

    @TableField("publisher_fans _num")
    private Long publisherFansNum;

    @TableField("news_text")
    private String newsText;

    @TableField("image_url")
    private String imageUrl;

    @TableField("release_time")
    private LocalDateTime releaseTime;

    @TableField("release_tool")
    private String releaseTool;

    @TableField("like_num")
    private Long likeNum;

    @TableField("forward_num")
    private Long forwardNum;

    @TableField("comments_num")
    private Long commentsNum;

    @TableField("release_location")
    private String releaseLocation;

    @TableField("pointed_forward_news_original_image_url")
    private String pointedForwardNewsOriginalImageUrl;

    @TableField("original_news")
    private Integer originalNews;


}
