package 单例模式;

import java.lang.reflect.Field;

/**
 * 单例模式（饿汉式）
 * @since 2019/8/4
 */
public class SinglePatternHungry {
    /**
     * 1.懒汉式不存在线程安全问题
     * 2.由于要被静态方法调用所以必须是静态属性
     * 3.final也保证了不可修改性
     *   需要注意的是懒汉式不能用final修改，
     *   因为final修改的属性要求必须在类加载完成时候初始化完成
     *   而不是在调用的时候初始化完成
     */
    private static final SinglePatternHungry singlePatternHungry = new SinglePatternHungry();

    private SinglePatternHungry() {}

    public static SinglePatternHungry getInstance() {
        return singlePatternHungry;
    }

    //这里发现通过反射还是可以修改掉这个对象的值的
    public static void main(String[] args) throws Exception {
        Field declaredField = SinglePatternHungry.getInstance().getClass().getDeclaredField("sph");
        declaredField.setAccessible(true);
        Object sph = declaredField.get("sph");
        System.out.println(SinglePatternHungry.getInstance() == sph);
        sph = SinglePatternHungry.getInstance().getClass().getDeclaredConstructor();
        System.out.println(SinglePatternHungry.getInstance() == sph);
    }

}