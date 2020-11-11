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
@ApiModel(value="Dict对象", description="")
public class Dict implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典表ID")
    @TableId(value = "dict_id", type = IdType.AUTO)
    private Integer dictId;

    @ApiModelProperty(value = "字典名称")
    private String dictName;

}
