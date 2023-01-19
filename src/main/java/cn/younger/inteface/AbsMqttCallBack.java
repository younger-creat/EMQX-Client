package cn.younger.inteface;

import lombok.Data;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author younger
 * @version 1.0
 * @description: 客户端消息回调函数抽象类
 * @date 2022/11/18 19:56
 */
@Data
public abstract class AbsMqttCallBack implements MqttCallback {
    /**
     * 客户端Id
     */
    private String clientId;
    /**
     * 连接对象
     */
    private MqttConnectOptions mqttConnectOptions;

    /**
     * 断连操作
     * @param cause
     */
    @Override
    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        System.out.println("连接断开，可以做重连");
    }

    /**
     * 消息接收成功
     * @param token
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }

    /**
     * 接收订阅消息
     * @param topic
     * @param message
     * @throws Exception
     */
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
        System.out.println("接收消息主题 : " + topic);
        System.out.println("接收消息Qos : " + message.getQos());
        String messageStr = new String(message.getPayload());
        //调用默认或自定义回调函数
        handleReceiveMessage(topic, messageStr);
    }

    /**
     * 自定义消息处理
     * @param topic
     * @param messageStr
     */
    public abstract void handleReceiveMessage(String topic, String messageStr);
}
