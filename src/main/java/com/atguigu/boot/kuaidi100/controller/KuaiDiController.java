package com.atguigu.boot.kuaidi100.controller;

import com.atguigu.boot.base.BaseController;
import com.atguigu.boot.kuaidi100.entity.ExpressageEntity;
import com.google.gson.Gson;
import com.kuaidi100.sdk.api.QueryTrack;
import com.kuaidi100.sdk.core.IBaseClient;
import com.kuaidi100.sdk.pojo.HttpResult;
import com.kuaidi100.sdk.request.QueryTrackParam;
import com.kuaidi100.sdk.request.QueryTrackReq;
import com.kuaidi100.sdk.utils.PropertiesReader;
import com.kuaidi100.sdk.utils.SignUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

/**
 * KuaiDiController
 *
 * @author yaodd
 * @date 2022/3/22
 */
@RestController
@RequestMapping("/kuaidi100")
@Api(tags = "快递100")
@Slf4j
@Validated
public class KuaiDiController  extends BaseController {


    private String key = PropertiesReader.get("key");
    private String customer = PropertiesReader.get("customer");
    private String secret = PropertiesReader.get("secret");
    private String siid = PropertiesReader.get("siid");
    private String userid = PropertiesReader.get("userid");
    private String tid = PropertiesReader.get("tid");
    private String secret_key = PropertiesReader.get("secret_key");
    private String secret_secret = PropertiesReader.get("secret_secret");

    @ApiImplicitParams({
            @ApiImplicitParam(name = "num", value = "订单号", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "com", value = "物流公司编码", dataType = "string", paramType = "query"),
    })
    @ApiOperation(value = "查询物流轨迹")
    @PostMapping(value = "/queryTrack")
    public ExpressageEntity queryTrack(@NotEmpty(message = "订单号不能为空") String num, String com){
        QueryTrackReq queryTrackReq = new QueryTrackReq();
        QueryTrackParam queryTrackParam = new QueryTrackParam();
//        queryTrackParam.setCom(CompanyConstant.YT);
        if(StringUtils.isNotEmpty(com))
            queryTrackParam.setCom(com);

        queryTrackParam.setNum(num); //YT6074326614455
        String param = new Gson().toJson(queryTrackParam);

        queryTrackReq.setParam(param);
        queryTrackReq.setCustomer(customer);
        queryTrackReq.setSign(SignUtils.querySign(param ,key,customer));

        IBaseClient baseClient = new QueryTrack();

        ExpressageEntity expressageEntity = null;

        try {
            HttpResult execute = baseClient.execute(queryTrackReq);
            String body = execute.getBody();
            expressageEntity = new Gson().fromJson(body, ExpressageEntity.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return expressageEntity;
    }


}
