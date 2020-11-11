package com.example.agricultural2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 省
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Prov对象", description="")
public class EacooProv implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "区域编码")
    @TableId(value = "code", type = IdType.AUTO)
    private String code;

    @ApiModelProperty(value = "省")
    private String prov;
}
