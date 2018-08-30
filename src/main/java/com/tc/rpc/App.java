package com.tc.rpc;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {

        RpcClientProxy proxy = new RpcClientProxy();

        IHelloService service = proxy.clientProxy(IHelloService.class, "localhost", 8080);
        String result1 = service.sayHello("zhangzz");
        System.out.println(result1);
        User user = new User();
        user.setName("wulili");
        String result2 = service.saveUser(user);
        System.out.println(result2);
    }
}
