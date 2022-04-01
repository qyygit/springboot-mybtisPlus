package com.atguigu.boot.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.atguigu.boot.bean.User;
import com.atguigu.boot.bean.UserEasy;
import com.atguigu.boot.dozer.DozerUtils;
import com.atguigu.boot.exception.BizException;
import com.atguigu.boot.service.UserService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DeviceDataListener extends AnalysisEventListener<UserEasy> {

    private UserService userService;

    private DozerUtils dozerUtils;


    public DeviceDataListener(UserService userService,DozerUtils dozerUtils) {
        this.userService = userService;
        this.dozerUtils = dozerUtils;
    }

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    int i = 1;
    private static final int BATCH_COUNT = 3000;
    List<User> list = new ArrayList<>();

    /**
     * 导入设备
     * 默认  供应商：休恩   状态：在线  维保状态 、预警描述：未维保  工厂 1038
     * 转换   设备名称  类别     维保时间  预警时间   仓库
     *
     * @param data
     * @param context
     */
    @Override
    public void invoke(UserEasy data, AnalysisContext context) {
        User user = validate(data);
        i++;
        list.add(user);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    /**
     * 校验导入数据
     *
     * @param data
     * @return
     */
    public User validate(UserEasy data) {
        User user = this.dozerUtils.map(data, User.class);
        log.info("解析第{}条数据:{}", i, JSON.toJSONString(data));
        // 名称
        //校验不为空
        if (StringUtils.isEmpty(data.getName())) {
            throw new BizException("第" + i + "条数据,name不能为空");
        }
        //校验Excel 表中的数据是否重复
        list.stream().forEach(p -> {
            if (p.getName().equals(data.getName())) {
                throw new BizException("第" + i + "条数据,ip:" + data.getName() + "已经存在");
            }
        });
        // 校验数据库,name 是否相同
        User user1 = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getName, data.getName()));
        if (user1 != null){
            throw new BizException("第" + i + "条数据,ip:" + data.getName() + "已经存在");
        }
        return user;
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", list.size());
        if (!CollectionUtils.isEmpty(list)) {
            userService.saveBatch(list);
            System.out.println(list);
        }
        log.info("存储数据库成功！");
    }
}
