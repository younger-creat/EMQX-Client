package cn.younger.pojo;

import cn.younger.inteface.AbsMqttCallBack;
import org.springframework.stereotype.Component;

/**
 * @author younger
 * @version 1.0
 * @description: 默认回调函数
 * @date 2022/11/18 20:09
 */
@Component
public class DefaultCallBack extends AbsMqttCallBack {
    @Override
    public void handleReceiveMessage(String topic, String messageStr){
        System.out.println("接收到的信息主题是：" + topic +"   " + "接收到的信息是：" + messageStr);
    }
}
