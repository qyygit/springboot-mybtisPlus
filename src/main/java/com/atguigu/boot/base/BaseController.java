package com.atguigu.boot.base;

import cn.hutool.core.util.StrUtil;
import com.atguigu.boot.common.result.R;
import com.atguigu.boot.dozer.DozerUtils;
import com.atguigu.boot.utils.DateUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;


/**
 * SuperController
 *
 */
public abstract class BaseController {

    @Resource
    protected HttpServletRequest request;
    @Resource
    protected HttpServletResponse response;
    @Autowired(required = false)
    protected DozerUtils dozerUtils;

    /**
     * 当前页
     */
    protected static final String CURRENT = "current";
    /**
     * 每页显示条数
     */
    protected static final String SIZE = "size";
    /**
     * 排序字段 ASC
     */
    protected static final String PAGE_ASCS = "ascs";
    /**
     * 排序字段 DESC
     */
    protected static final String PAGE_DESCS = "descs";

    protected static final String START_CREATE_TIME = "startCreateTime";
    protected static final String END_CREATE_TIME = "endCreateTime";
    /**
     * 默认每页条目10,最大条目数 100
     */
    int DEFAULT_LIMIT = 10;
    int MAX_LIMIT = 100;

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 成功返回
     *
     * @param data
     * @return
     */
    public R success(Map<String,Object> data) {
        return R.success(data);
    }


    public R success(IPage data) {
        return R.success()
                .data("list",((IPage) data).getRecords())
                .data("count",((IPage) data).getTotal());
    }

    public R success() {
        return R.success();
    }



    /**
     * 失败返回
     *
     * @param msg
     * @return
     */
    public R error(String msg) {
        return R.error(msg);
    }

    /*public <T> R<T> fail(String msg, Object... args) {
        return R.err(msg, args);
    }*/

    /**
     * 失败返回
     *
     * @param code
     * @param msg
     * @return
     */
    /*public <T> R<T> fail(int code, String msg) {
        return R.fail(code, msg);
    }

    public <T> R<T> fail(BaseExceptionCode exceptionCode) {
        return R.fail(exceptionCode);
    }

    public <T> R<T> fail(BizException exception) {
        return R.fail(exception);
    }

    public <T> R<T> fail(Throwable throwable) {
        return R.fail(throwable);
    }

    public <T> R<T> validFail(String msg) {
        return R.validFail(msg);
    }

    public <T> R<T> validFail(String msg, Object... args) {
        return R.validFail(msg, args);
    }

    public <T> R<T> validFail(BaseExceptionCode exceptionCode) {
        return R.validFail(exceptionCode);
    }
*/
    /**
     * 获取当前用户id
     */
    /*protected Long getUserId() {
        return BaseContextHandler.getUserId();
    }

    protected String getAccount() {
        return BaseContextHandler.getAccount();
    }

    protected String getName() {
        return BaseContextHandler.getName();
    }*/

    /**
     * 获取分页对象
     *
     * @return
     */
    protected <T> Page<T> getPage() {
        return getPage(false);
    }

    protected Integer getPageNo() {
        return com.atguigu.boot.utils.NumberHelper.intValueOf(request.getParameter(CURRENT), 1);
    }

    protected Integer getPageSize() {
        return com.atguigu.boot.utils.NumberHelper.intValueOf(request.getParameter(SIZE), DEFAULT_LIMIT);
    }

    /**
     * 获取分页对象
     *
     * @param openSort
     * @return
     */
    protected <T> Page<T> getPage(boolean openSort) {
        // 页数
        Integer pageNo = getPageNo();
        // 分页大小
        Integer pageSize = getPageSize();
        // 是否查询分页
        return buildPage(openSort, pageNo, pageSize);
    }

    private <T> Page<T> buildPage(boolean openSort, long pageNo, long pageSize) {
        // 是否查询分页
        pageSize = pageSize > MAX_LIMIT ? MAX_LIMIT : pageSize;
        Page<T> page = new Page<>(pageNo, pageSize);
        if (openSort) {
            page.setAsc(getParameterSafeValues(PAGE_ASCS));
            page.setDesc(getParameterSafeValues(PAGE_DESCS));
        }
        return page;
    }

    /**
     * 获取安全参数(SQL ORDER BY 过滤)
     *
     * @param parameter
     * @return
     */
    protected String[] getParameterSafeValues(String parameter) {
        return com.atguigu.boot.utils.AntiSqlFilter.getSafeValues(request.getParameterValues(parameter));
    }

    protected LocalDateTime getStartCreateTime() {
        return getLocalDateTime(START_CREATE_TIME);
    }

    protected LocalDateTime getEndCreateTime() {
        return getLocalDateTime(END_CREATE_TIME);
    }

    private LocalDateTime getLocalDateTime(String endCreateTime) {
        String startCreateTime = request.getParameter(endCreateTime);
        if (StrUtil.isBlank(startCreateTime)) {
            return null;
        }
        String safeValue = com.atguigu.boot.utils.AntiSqlFilter.getSafeValue(startCreateTime);
        if (StrUtil.isBlank(safeValue)) {
            return null;
        }
        return LocalDateTime.parse(safeValue, DateTimeFormatter.ofPattern(com.atguigu.boot.utils.DateUtils.DEFAULT_DATE_TIME_FORMAT));
    }
}
