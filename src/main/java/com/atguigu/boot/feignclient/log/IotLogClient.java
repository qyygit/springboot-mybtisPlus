//package com.atguigu.boot.feignclient.log;
//
//import com.shooin.config.base.R;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.List;
//
//
///**
// * @Classname feign
// * @Description 字典远程接口
// * @Date 2021/9/15 11:06
// * @Created by guolin
// */
//@Component
//@FeignClient(value = "iot-log")
//public interface IotLogClient {
//
//    @PostMapping("/device-log/saveLog")
//    public R saveLog(IotDeviceLog iotDeviceLog);
//
//    /**
//     * 批量增加设备信息
//     * @param logs
//     * @return
//     */
//    @PostMapping("/device-log/saveBatchLogs")
//    public R saveBatchLogs(List<IotDeviceLog> logs);
//
//
//    @PostMapping("/web-log/saveWebLog")
//    public R saveWebLog(com.shooin.config.feignclient.log.WebLog webLog);
//}
