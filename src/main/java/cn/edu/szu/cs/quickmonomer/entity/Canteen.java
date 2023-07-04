package cn.edu.szu.cs.quickmonomer.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 餐厅基本信息表
 * @TableName canteen
 */
@TableName(value ="canteen")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Canteen implements Serializable {
    /**
     * 餐厅id
     * @mock @pick([1,2,3])
     */
    @TableId(value = "canteen_id", type = IdType.AUTO)
    private Long canteenId;

    /**
     * 餐厅名
     */
    @TableField(value = "canteen_name")
    private String canteenName;

    /**
     * 餐厅主照片
     */
    @TableField(value = "main_image")
    private String mainImage;

    /**
     * 餐厅地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 餐厅坐标经度
     */
    @TableField(value = "longitude")
    private BigDecimal longitude;

    /**
     * 餐厅坐标纬度
     */
    @TableField(value = "latitude")
    private BigDecimal latitude;

    /**
     * 营业时间
     */
    @TableField(value = "opening_time")
    private String openingTime;

    /**
     * 描述
     */
    @TableField(value = "`desc`")
    private String desc;

    /**
     * 管理员id
     */
    @TableField(value = "admin_id")
    private Integer adminId;

    /**
     * 删除状态
     */
    @TableField(value = "delete_status")
    @TableLogic
    private Integer deleteStatus;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}