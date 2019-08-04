package 单例模式;

import java.lang.reflect.Field;

/**
 * 单例模式（饿汉式）
 * @since 2019/8/4
 * 饿汉式单例模式需要注意两个点
 * 1.static 保证了不是new的情况也能调用
 */
public class SinglePatternHungry {

    private static final SinglePatternHungry sph = new SinglePatternHungry();

    private SinglePatternHungry() {}

    public static SinglePatternHungry getInstance() {
        return sph;
    }

    public static void main(String[] args) throws Exception {
        Field declaredField = SinglePatternHungry.getInstance().getClass().getDeclaredField("sph");
        declaredField.setAccessible(true);
        Object sph = declaredField.get("sph");
        System.out.println(SinglePatternHungry.getInstance() == sph);
        sph = SinglePatternHungry.getInstance().getClass().getDeclaredConstructor();
        System.out.println(SinglePatternHungry.getInstance() == sph);
    }

}