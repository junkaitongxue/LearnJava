package JAVA8.annotationLearn.myAutoWired;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        UserController userController = new UserController();
        Class<? extends UserController> clazz = userController.getClass();

        Stream.of(clazz.getDeclaredFields()).forEach(field -> {
            MyAutoWired annotation = field.getAnnotation(MyAutoWired.class);
            if (annotation != null) {
                field.setAccessible(true);
                Class<?> type = field.getType();

                try {
                    Object o = type.newInstance();
                    //设置对象到userController中
                    // 方法一 更加抽象，不需要定义setXXX函数 better
//                    field.set(userController, o); //

                    // 方法二 UserService需要动态传递，依赖setXXX方法，获取
                    Method method = clazz.getMethod("set"+type.getSimpleName(), UserService.class);
                    method.invoke(userController, o);

                    System.out.println(o);

                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println(userController.getUserService());
    }
}
