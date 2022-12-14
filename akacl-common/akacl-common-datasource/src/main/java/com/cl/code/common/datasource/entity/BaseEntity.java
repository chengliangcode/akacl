package com.cl.code.common.datasource.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chengliang
 * @since 2022/11/6 16:22
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 创建人id
     */
    @TableField(value = "create_user_id")
    private Long createUserId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Long createTime;

    /**
     * 更新人id
     */
    @TableField(value = "update_user_id")
    private Long updateUserId;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

    /**
     * 是否启用
     */
    @TableField(value = "enable", fill = FieldFill.INSERT)
    private Integer enable;

    /**
     * 逻辑删除
     */
    @TableField(value = "deleted", fill = FieldFill.INSERT)
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;

    public static final String ID = "id";

    public static final String CREATE_USER_ID = "createUserId";

    public static final String CREATE_TIME = "createTime";

    public static final String UPDATE_USER_ID = "updateUserId";

    public static final String UPDATE_TIME = "updateTime";

    public static final String ENABLE = "enable";

    public static final String DELETED = "deleted";

}
