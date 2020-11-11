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
@ApiModel(value="Cooperative对象", description="")
public class Cooperative implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "合作社字典")
    @TableId(value = "cooperative_id", type = IdType.AUTO)
    private String cooperativeId;

    @ApiModelProperty(value = "合作社名称")
    private String cooperativeName;

    @ApiModelProperty(value = "所属城镇")
    private String cooperativeTown;
}
