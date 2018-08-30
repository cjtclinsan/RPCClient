package com.tc.rpc2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Administrator on 2018/8/30.
 */
public class ClientInvocationHandler implements InvocationHandler {

    private InetSocketAddress addr;
    private Class<?> interfaceCls;

    public ClientInvocationHandler(InetSocketAddress addr, Class<?> interfaceCls) {
        this.addr = addr;
        this.interfaceCls = interfaceCls;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = sendMes(method, args);
        return obj;
    }

    private Object sendMes(Method method, Object[] args) {
        // 2.创建Socket客户端，根据指定地址连接远程服务提供者
        Socket socket = new Socket();
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try {
            socket.connect(addr);

            // 3.将远程服务调用所需的接口类、方法名、参数列表等编码后发送给服务提供者
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeUTF(interfaceCls.getName());
            outputStream.writeObject(method.getName());
            outputStream.writeObject(args);

            // 4.同步阻塞等待服务器返回应答，获取应答后返回
            inputStream = new ObjectInputStream(socket.getInputStream());
            return inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
