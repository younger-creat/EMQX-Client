package cn.younger.utils;

import cn.younger.pojo.MqttClientVO;
import cn.younger.service.MqttClientManager;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @Classname MqttClientUtil
 * @Description 客户端工具类
 * @Version 1.0.0
 * @Date 2022/11/19 15:53
 * @Created by younger
 */
public class MqttClientUtil {
    /**
     * 关闭连接
     * @param clientId
     * @param clientManager
     */
    public static String closeConnect(String clientId, MqttClientManager clientManager){
        MqttClient mqttClient = clientManager.getMqttClientMap().get(clientId);
        if(null != mqttClient){
            //判断客户端是否连接着
            if(mqttClient.isConnected()){
                try {
                    mqttClient.disconnect();
                    mqttClient.close();
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
            //去除集合里存储的客户端（避免内存泄露）
            clientManager.getMqttClientMap().remove(clientId);
            return "成功断开连接";
        }else{
            return "客户端并未进行连接，请检查";
        }
    }

    /**
     * 消息发布（String 类型消息）
     * @param pubTopic
     * @param message
     * @param qos
     */
    public static void publishMessage(String pubTopic, String message, int qos, String clientId, MqttClientManager clientManager){
        MqttClient mqttClient = clientManager.getMqttClientMap().get(clientId);
        if(null != mqttClient && mqttClient.isConnected()){
            MqttMessage mqttMessage = new MqttMessage();
            //设置通道
            mqttMessage.setQos(qos);
            mqttMessage.setPayload(message.getBytes());
            //根据主题进行消息发送
            try {
                mqttClient.publish(pubTopic, mqttMessage);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("客户端不存在或者已经断开连接");
        }
    }

    /**
     * 客户端重连
     * @param clientId
     * @param mqttClientManager
     */
    public static void reconnect(String clientId, MqttClientManager mqttClientManager){
        MqttClient mqttClient = mqttClientManager.getMqttClientMap().get(clientId);
        if(null != mqttClient){
            if(!mqttClient.isConnected()){

            }
        }else{
            System.out.println("客户端尚未创建，请先创建客户端");
        }
    }

    /**
     * 初始化连接对象
     * @param username
     * @param password
     * @param flag
     * @param timeout
     * @return
     */
    public static MqttConnectOptions createConnectOptions(String username, String password, boolean flag, int timeout, int keepAliveTime){
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        if (null != username && !"".equals(username)) {
            mqttConnectOptions.setUserName(username);
        }
        if (null != password && !"".equals(password)) {
            mqttConnectOptions.setPassword(password.toCharArray());
        }
        //设置是否清空session，如果设置为false表示服务器会保留客户端的连接记录
        //设置true则表示每次连接服务器都会以新身份连接
        mqttConnectOptions.setCleanSession(flag);
        //连接超时处理时间
        mqttConnectOptions.setConnectionTimeout(timeout);
        //心跳时间
        mqttConnectOptions.setKeepAliveInterval(keepAliveTime);
        return mqttConnectOptions;
    }

    /**
     * 判断连接是否已经被创建
     * @param mqttClientVO
     * @param mqttClientManager
     * @return
     */
    public static boolean isClientCreated(MqttClientVO mqttClientVO, MqttClientManager mqttClientManager){
        return true;
    }
}
