public class HelloWorldAnonymousClasses {

    interface HelloWorld {
        public void greet();

        public void greetSomeone(String someone);
    }

    public void sayHello() {

        /**
         * 1、局部类:EnglishGreeting实现了HelloWorld接口
         */
        class EnglishGreeting implements HelloWorld {
            String name = "无参";

            @Override
            public void greet() {
                greetSomeone(name);
            }

            @Override
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("局部类：" + name);
            }
        }
        // 创建局部类EnglishGreeting的实例化对象，使用接口类型接收
        HelloWorld englishGreeting = new EnglishGreeting();
        // 局部类：无参方法
        englishGreeting.greet();
        // 局部类：带参方法
        englishGreeting.greetSomeone("带参");


        /**
         * 2、匿名类实现HelloWorld接口并创建了实例化对象：frenchGreeting
         */
        HelloWorld frenchGreeting = new HelloWorld() {
            String name = "无参";

            @Override
            public void greet() {
                greetSomeone(name);
            }

            @Override
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("匿名类：" + name);
            }
        };

        // 匿名类：无参方法
        frenchGreeting.greet();
        // 匿名类：带参方法
        frenchGreeting.greetSomeone("带参");
    }

    public static void main(String... args) {
        HelloWorldAnonymousClasses myApp = new HelloWorldAnonymousClasses();
        myApp.sayHello();
    }
}