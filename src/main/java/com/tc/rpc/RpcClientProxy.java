package com.tc.rpc;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2018/8/29.
 */
public class RpcClientProxy {

    //动态代理  java原生proxy/cglib/javassit/asm...
    public<T> T clientProxy(Class<T> intefaceCls, String host, int port){
        return (T) Proxy.newProxyInstance(intefaceCls.getClassLoader(),
                new Class<?>[] {intefaceCls},
                new RemoteInvocationHandler(host,port));
    }

}
