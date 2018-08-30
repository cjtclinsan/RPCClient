package com.tc.rpc2;

import java.net.InetSocketAddress;

/**
 * Created by Administrator on 2018/8/30.
 */
public class RPCTest {
    public static void main(String[] args) {
        IHelloRpc service = RpcClientProxy.getRemoteProxyObj(IHelloRpc.class,
                new InetSocketAddress("localhost",8080));
        service.sayHi("lilili");
    }
}
