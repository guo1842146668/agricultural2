package com.example.agricultural2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Region对象", description="")
public class Region  implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "区域ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer Id;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "部门名称")
    private Integer pid;

    @ApiModelProperty(value = "部门名称")
    private String sname;

    @ApiModelProperty(value = "部门名称")
    private Integer level;

    @ApiModelProperty(value = "部门名称")
    private String citycode;

    @ApiModelProperty(value = "部门名称")
    private String yzcode;

    @ApiModelProperty(value = "部门名称")
    private String mername;

    @ApiModelProperty(value = "部门名称")
    private Float Lng;

    @ApiModelProperty(value = "部门名称")
    private Float Lat;

    @ApiModelProperty(value = "部门名称")
    private String pinyin;
}
