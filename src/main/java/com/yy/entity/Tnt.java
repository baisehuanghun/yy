package com.yy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2022-04-22
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName("t_tnt")
public class Tnt extends Model<Tnt> {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 模块ID
     */
    private Integer fid;

    /**
     * 模块名称
     */
    private String fname;

    /**
     * 资源标题
     */
    private String title;

    /**
     * 老T发布日期
     */
    private String releaseDate;

    /**
     * 详情页链接
     */
    private String detailsLink;

    private String fujian;

    private String img;

    private Integer isFind;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
