package com.atguigu.boot.studytest.test329;

import java.io.File;

class FileDemo {
    public static void main(String[] args) {
        File file = new File("D:\\file.txt");
        try {
            file.createNewFile(); // 创建一个文件

            // File的两个常量
            //路径分隔符与操作系统有关 <windows 里面是 ; linux 里面是 : >
            System.out.println(File.pathSeparator); // ;
            //路劲名称分隔符,与系统有关,  <windows 里面是  \ linux 里面是 / >
            System.out.println(File.separator); // \

//            ڢ删除文件
            String name = file.getName();
            System.out.println("删除的文件名称是" + name);
            File file2 = new File("D:\\file.txt");
            if (file2.exists()) {
                file2.delete();
            } else {
                System.out.println("෈文件不存在ࣁ");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}