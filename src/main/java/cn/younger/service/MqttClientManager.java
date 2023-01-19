package cn.younger.service;

import cn.younger.inteface.AbsMqttCallBack;
import cn.younger.pojo.DefaultCallBack;
import cn.younger.pojo.MqttClientAndPersistence;
import cn.younger.utils.MqttClientUtil;
import cn.younger.utils.SpringBeanUtil;
import lombok.Data;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author younger
 * @version 1.0
 * @description: 客户端管理类
 * @date 2022/11/18 20:34
 */
@Service
@Data
public class MqttClientManager {
    @Autowired
    MqttCallBackContext mqttCallBackContext;
    @Autowired
    DefaultCallBack defaultCallBack;

    /**
     * 存放客户端
     */
    private Map<String, MqttClient> mqttClientMap = new ConcurrentHashMap<>();
    /**
     * 存放持久化方式和客户端
     */
    private Map<String, MqttClientAndPersistence> mqttClientAndPersistenceMap = new ConcurrentHashMap<>();

    /**
     * 客户端创建
     * @param clientId
     * @param subscribeTopic
     * @param username
     * @param password
     */
    public void creatMqttClient(String clientId, String subscribeTopic, String username, String password, int timeout, int keepAliveTime, String brokerIp, boolean flag){
        //System.out.println("客户端不存在，正在创建");
        //初始化连接对象
        MqttConnectOptions mqttConnectOptions = MqttClientUtil.createConnectOptions(username, password, flag, timeout, keepAliveTime);
        //设置持久化
        MemoryPersistence memoryPersistence = new MemoryPersistence();
        //创建客户端
        try {
            MqttClient mqttClient = new MqttClient(brokerIp, clientId, memoryPersistence);
            //回调函数
            AbsMqttCallBack mqttCallBack = mqttCallBackContext.getMqttCallBack(clientId);
            //如果没创建回调函数则使用默认回调函数
            if(null == mqttCallBack) {
                mqttCallBack = defaultCallBack;
            }

            mqttCallBack.setClientId(clientId);
            mqttCallBack.setMqttConnectOptions(mqttConnectOptions);
            //设置回调函数
            mqttClient.setCallback(mqttCallBack);

            //连接mqtt服务端
            mqttClient.connect(mqttConnectOptions);

            //进行订阅
            if (null != subscribeTopic && !"".equals(subscribeTopic)) {
                mqttClient.subscribe(subscribeTopic);
            }

            //将创建的客户端放入集合中
            mqttClientMap.put(clientId, mqttClient);

        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
}
