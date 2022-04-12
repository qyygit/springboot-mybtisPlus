package com.atguigu.boot.kuaidi100.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ExpressageTranEntity
 *
 * @author yaodd
 * @date 2022/3/22
 */
@Data
@ApiModel(value="快递100-data", description="快递100")
public class ExpressageTranEntity {


    @ApiModelProperty(value = "时间，原始格式")
    private String time;

    @ApiModelProperty(value = "格式化后时间")
    private String ftime;

    @ApiModelProperty(value = "内容")
    private String context;
}
