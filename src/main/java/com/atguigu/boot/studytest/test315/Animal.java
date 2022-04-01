package com.atguigu.boot.studytest.test315;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class Animal {
    public void breathing() {
        System.out.println("呼气...吸气...");
    }

    public static void main(String[] args) {




        Locale locale = LocaleContextHolder.getLocale();

        locale.getLanguage();
    }

}