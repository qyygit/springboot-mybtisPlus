package com.atguigu.boot.kuaidi100.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * KuaiDi
 *
 * @author yaodd
 * @date 2022/3/22
 */
@Data
@ApiModel(value="快递100-HttpResult", description="快递100")
public class ExpressageEntity {

    @ApiModelProperty(value = "消息体，请忽略")
    private String message;

    @ApiModelProperty(value = "单号")
    private String nu;

    @ApiModelProperty(value = "是否签收标记，0未签收，1已签收，请忽略，明细状态请参考state字段")
    private Integer ischeck;

    @ApiModelProperty(value = "快递单明细状态标记，暂未实现，请忽略")
    private String condition;

    @ApiModelProperty(value = "快递公司编码,一律用小写字母")
    private String com;

    @ApiModelProperty(value = "通讯状态，请忽略")
    private Integer status;

    /**
     * 快递单当前状态，默认为0在途，1揽收，2疑难，3签收，4退签，5派件，8清关，14拒签等10个基础物流状态，如需要返回高级物流状态，请参考 resultv2 传值
     */
    @ApiModelProperty(value = "快递单当前状态，默认为0在途，1揽收，2疑难，3签收，4退签，5派件，8清关，14拒签等10个基础物流状态")
    private Integer state;

    @ApiModelProperty(value = "data数据")
    private List<ExpressageTranEntity> data;






}
