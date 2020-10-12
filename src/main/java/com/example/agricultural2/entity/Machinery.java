package com.example.agricultural2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ClassName: Machinery //农业机械表 machinery
 * @Description: TODO
 * @Author: idmin
 * @Date: 2020/7/23 10:49
 * @Version: 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Machinery对象",description="农业机械对象Machinery")
public class Machinery implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "农业机械ID")
    @TableId(value = "machinery_id", type = IdType.AUTO)
    private Integer machineryId;

    @ApiModelProperty(value = "农业机械所有者ID")
    private Integer machineryOwner;

    @ApiModelProperty(value = "农业机械编号")
    private String machineryNo;

    @ApiModelProperty(value = "国际移动设备识别码")
    private String machineryImel;

    @ApiModelProperty(value = "农业机械车牌号")
    private String machineryLicense;

    @ApiModelProperty(value = "农业机械品牌")
    private String machineryBrand;

    @ApiModelProperty(value = "农业机械宽度")
    private Double machineryWidth;

    @ApiModelProperty(value = "农机状态  1正常   2损坏  3维修  4报废删除")
    private Integer machineryStatus;

    @ApiModelProperty(value = "省")
    private String machineryProvince;

    @ApiModelProperty(value = "市")
    private String machineryCity;

    @ApiModelProperty(value = "县")
    private String machineryCounty;

    @ApiModelProperty(value = "镇")
    private String machineryTown;

    @ApiModelProperty(value = "村")
    private String machineryVillage;

    @ApiModelProperty(value = "合作社")
    private String machineryCooperative;

    @ApiModelProperty(value = "备注")
    private String machineryRemake;
}
