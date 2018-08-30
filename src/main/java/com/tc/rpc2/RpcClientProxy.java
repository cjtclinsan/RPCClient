package com.tc.rpc2;

import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;

/**
 * Created by Administrator on 2018/8/30.
 */
public class RpcClientProxy {
    public static<T> T getRemoteProxyObj(final Class<?> interfaceCls, final InetSocketAddress address){
        // 1.将本地的接口调用转换成JDK的动态代理，在动态代理中实现接口的远程调用
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(),
                new Class<?>[] {interfaceCls},
                new ClientInvocationHandler(address,interfaceCls));
    }
}
