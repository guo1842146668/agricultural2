package com.example.agricultural2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 县/乡
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Country对象", description="")
public class EacooCountry implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "区域编码")
    @TableId(value = "code", type = IdType.AUTO)
    private String code;

    @ApiModelProperty(value = "市")
    private String country;

    @ApiModelProperty(value = "省ID")
    private String cityCode;

}
