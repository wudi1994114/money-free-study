package 单例模式;

import java.lang.reflect.Field;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 单例模式（懒汉式）
 *
 * @since 2019/8/4
 */
public class SinglePatternLazy {

    static int i = 0;

    private static SinglePatternLazy singlePatternLazy;

    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
        Field singlePatternLazy;

        try {
            singlePatternLazy = SinglePatternLazy.getInstance().getClass().getDeclaredField("singlePatternLazy");
            singlePatternLazy.setAccessible(true);
            singlePatternLazy.set("singlePatternLazy", null);
            System.out.println(Thread.currentThread().getName() + " set zero");

            if (i < 2) i = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    });



    private SinglePatternLazy() {
    }

    public static SinglePatternLazy getInstance() {
        if (singlePatternLazy == null) {
            synchronized (SinglePatternLazy.class) {
                if (singlePatternLazy == null) {
                    singlePatternLazy = new SinglePatternLazy();
                    i++;
                    System.out.println(Thread.currentThread().getName() + " true");
                } else {
                    System.out.println(Thread.currentThread().getName() + "2");
                }
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " false");
        }
        return singlePatternLazy;
    }

    public static void main(String[] args) throws Exception {
        int count = 0;
        while (true) {
            count++;

            for (int j = 0; j < 10; j++) {
                Thread thread = new Thread(() -> {
                    getInstance();
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                });
                thread.setName("count" + count + ", " + j);
                thread.start();
            }

            if (i >= 2) {
                break;
            }
            System.out.println(count + "次");
            while (i != 0) {
                Thread.sleep(10L);
            }
        }

    }


}
