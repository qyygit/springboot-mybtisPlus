package com.atguigu.boot.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LanguageUtils {
    private static MessageSource messageSource;
    public LanguageUtils(MessageSource messageSource){
        LanguageUtils.messageSource = messageSource;
    }
    public static String get(String msgKey){
        return messageSource.getMessage(msgKey,null, LocaleContextHolder.getLocale());
    }
    public static String get(String msgKey,Object... objects){
        return messageSource.getMessage(msgKey,objects,LocaleContextHolder.getLocale());
    }
    public static String get(String msgKey,String defaultMsg,Object... objects){
        return messageSource.getMessage(msgKey,objects,defaultMsg,LocaleContextHolder.getLocale());
    }

}
