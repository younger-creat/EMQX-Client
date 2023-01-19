package cn.younger.service;

import cn.younger.inteface.AbsMqttCallBack;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author younger
 * @version 1.0
 * @description: TODO
 * @date 2022/11/18 20:15
 */
@Component
@Data
public class MqttCallBackContext {
    /**
     * 客户端回调集合
     */
    private Map<String, AbsMqttCallBack> callBackMap = new ConcurrentHashMap<>();

    /**
     * 构造函数
     * @param callBackMap
     */
    public MqttCallBackContext(Map<String, AbsMqttCallBack> callBackMap){
        this.callBackMap.clear();
        //遍历传入的集合并添加到类属性里
        callBackMap.forEach((k ,v) -> this.callBackMap.put(k, v));
    }

    /**
     * 获得对呀客户端的回调函数
     * @param clientId
     * @return
     */
    public AbsMqttCallBack getMqttCallBack(String clientId){
        return callBackMap.get(clientId);
    }
}
