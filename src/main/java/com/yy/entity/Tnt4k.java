package com.yy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author bs
 * @since 2022-04-23
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName("t_tnt_4k")
public class Tnt4k extends Model<Tnt4k> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 资源标题
     */
    private String title;

    /**
     * 发布日期
     */
    private String releaseDate;

    /**
     * 详情页链接
     */
    private String detailsLink;

    private String fujian;

    private String imgLink;

    /**
     * 详细内容
     */
    private String content;

    /**
     * 数据来源
     */
    private String source;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
