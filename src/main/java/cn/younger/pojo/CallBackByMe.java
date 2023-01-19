package cn.younger.pojo;

import cn.younger.inteface.AbsMqttCallBack;
import org.springframework.stereotype.Component;

/**
 * @author younger
 * @version 1.0
 * @description: 自己写的回调函数
 * @date 2022/11/18 21:28
 */
@Component
public class CallBackByMe extends AbsMqttCallBack {
    /**
     * 根据接收的信息进行处理
     * @param topic
     * @param messageStr
     */
    @Override
    public void handleReceiveMessage(String topic, String messageStr){
        System.out.println("younger自己的回调函数接收到的信息是：" + messageStr);
    }
}
