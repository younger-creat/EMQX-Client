package cn.younger.pojo;

import cn.younger.inteface.AbsMqttCallBack;
import org.springframework.stereotype.Component;

/**
 * @Classname TestBack
 * @Description TODO
 * @Version 1.0.0
 * @Date 2022/11/24 11:04
 * @Created by younger
 */
@Component
public class TestBack extends AbsMqttCallBack {
    @Override
    public void handleReceiveMessage(String topic, String messageStr){
        System.out.println("younger自己的回调函数接收到的信息是：" + messageStr);
    }
}
