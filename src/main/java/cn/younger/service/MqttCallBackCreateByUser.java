package cn.younger.service;

import cn.younger.inteface.AbsMqttCallBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author younger
 * @version 1.0
 * @description: TODO
 * @date 2022/11/18 21:24
 */
@Component
public class MqttCallBackCreateByUser {
    @Autowired
    MqttCallBackContext mqttCallBackContext;

    /**
     * 创建自己的回调函数
     * @param clientId
     * @param mqttCallBack
     */
    public void createMyCallBack(String clientId, AbsMqttCallBack mqttCallBack){
        mqttCallBackContext.getCallBackMap().put(clientId, mqttCallBack);
    }
}
