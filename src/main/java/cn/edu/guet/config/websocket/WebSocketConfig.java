package cn.edu.guet.config.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
 
/**
 * @author baibing
 * @project: springboot-socket
 * @package: com.sailing.websocket.config
 * @Description: socket配置类,往 spring 容器中注入ServerEndpointExporter实例
 * @date 2018/12/20 09：46
 */
@Configuration
public class WebSocketConfig {
 
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
