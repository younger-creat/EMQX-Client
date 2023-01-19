package cn.younger.service;

import cn.younger.configuration.MqttConfig;
import cn.younger.pojo.MqttClientVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author younger
 * @version 1.0
 * @description: 根据配置文件创建客户端
 * @date 2022/11/18 21:09
 */
@Component
public class MqttClientCreate {
    @Autowired
    private MqttClientManager mqttClientManager;
    @Autowired
    private MqttConfig mqttConfig;

    //@PostConstruct
    public void createMqttClient(){
        //读取配置里的客户端信息
        List<MqttClientVO> mqttClientVOList = mqttConfig.getClientVOList();
        //根据客户端信息进行客户端创建
        for(MqttClientVO mqttClientVO : mqttClientVOList){
            mqttClientManager.creatMqttClient(mqttClientVO.getClientId(), mqttClientVO.getSubscribeTopic(), mqttClientVO.getUsername(),
                    mqttClientVO.getPassword(), mqttClientVO.getTimeout(), mqttClientVO.getKeepAliveTime(), mqttClientVO.getBroker(), mqttClientVO.isFlag());
        }
    }
}
