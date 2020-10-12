package com.example.agricultural2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: Work //作业表  agricultural_work
 * @Description: TODO
 * @Author: idmin
 * @Date: 2020/7/23 10:53
 * @Version: 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Work对象",description="作业对象Work")
public class Work implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "作业ID")
    @TableId(value = "work_id", type = IdType.AUTO)
    private Integer workId;

    @ApiModelProperty(value = "作业的机械ID")
    private Integer workMachineryId;

    @ApiModelProperty(value = "作业开始时间")
    private Date workStartTime;

    @ApiModelProperty(value = "作业开始坐标")
    private String workStartMap;

    @ApiModelProperty(value = "作业结束时间")
    private Date workEndTime;

    @ApiModelProperty(value = "作业结束坐标")
    private String workEndMap;

    @ApiModelProperty(value = "行驶里程")
    private Double drivenDistance;

    @ApiModelProperty(value = "作业里程")
    private Double workLength;

    @ApiModelProperty(value = "作业面积")
    private Double workArea;

    @ApiModelProperty(value = "作业深度")
    private Double workDepth;

    @ApiModelProperty(value = "核对结果 1通过   -1不通过")
    private Integer verificationResults;

    @ApiModelProperty(value = "确认面积")
    private Double confirmArea;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "县")
    private String county;

    @ApiModelProperty(value = "镇")
    private String town;

    @ApiModelProperty(value = "村")
    private String village;

}
