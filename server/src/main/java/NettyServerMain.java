import com.kris.annotation.RpcScan;
import com.kris.remote.transport.netty.server.NettyRpcServer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Program: kris-rpc
 * @Description:
 * @Author: kris
 * @Create: 2025-03-17 21:26
 **/

@RpcScan(basePackage = {"com.kris"})
public class NettyServerMain {
    public static void main(String[] args) {
        // 注册服务
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(NettyServerMain.class);
        NettyRpcServer nettyRpcServer = (NettyRpcServer) applicationContext.getBean("nettyRpcServer");
        nettyRpcServer.start();
    }
}
