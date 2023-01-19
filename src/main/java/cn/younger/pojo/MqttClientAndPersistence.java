package cn.younger.pojo;

import lombok.Data;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;

/**
 * @Classname MqttClientAndPersistence
 * @Description TODO
 * @Version 1.0.0
 * @Date 2022/11/19 16:28
 * @Created by younger
 */
@Component
@Data
public class MqttClientAndPersistence {
    /**
     * 客户端
     */
    private MqttClient mqttClient;
    /**
     * 客户端持久化方式
     */
    private MemoryPersistence memoryPersistence;
}
