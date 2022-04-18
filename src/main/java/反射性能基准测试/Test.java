package 反射性能基准测试;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: sl
 * Date: 2021/10/22
 * Time: 13:51
 */
public class Test {

    public static void main(String[] args) throws Exception {
        Class<?> clz = Class.forName("reflect.ReflectionTest");
        Method method = clz.getMethod("foo");
        for (int i = 0; i < 20; i++) {
            method.invoke(null);
        }
    }

}