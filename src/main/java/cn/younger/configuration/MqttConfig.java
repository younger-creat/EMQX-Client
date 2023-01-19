package cn.younger.configuration;

import cn.younger.pojo.MqttClientVO;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author younger
 * @version 1.0
 * @description: MQTT配置类
 * @date 2022/11/18 19:29
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "customer.mqtt")
public class MqttConfig {
    /**
     * 需要创建的MQTT客户端
     */
    List<MqttClientVO> clientVOList;
}
