package com.tc.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/8/29.
 */
public class RemoteInvocationHandler implements InvocationHandler{

    String host;
    int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RPCRequest rpcRequest = new RPCRequest();
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameter(args);

        RPCNetTransport transport = new RPCNetTransport(host,port);

        transport.sendRequest(rpcRequest);

        return null;
    }
}
