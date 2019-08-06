package 代理模式;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyPattern implements InvocationHandler, Father {

    private static ProxyPattern proxyPattern = new ProxyPattern();

    public void p() {
        System.out.println("ok go go go");
    }




    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
        System.out.println("what should i do");
        Object invoke = method.invoke(proxyPattern, args);
        System.out.println("roger that");
        return invoke;
    }

    public static void main(String[] args) {
        Father o = (Father)Proxy.newProxyInstance(ProxyPattern.class.getClassLoader(), ProxyPattern.class.getInterfaces(), proxyPattern);
        o.p();
    }
}
