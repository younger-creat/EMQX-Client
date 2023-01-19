package cn.younger.controller;

import cn.younger.pojo.CallBackByMe;
import cn.younger.pojo.TestBack;
import cn.younger.service.MqttCallBackContext;
import cn.younger.service.MqttCallBackCreateByUser;
import cn.younger.service.MqttClientCreate;
import cn.younger.service.MqttClientManager;
import cn.younger.utils.MqttClientUtil;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author younger
 * @version 1.0
 * @description: TODO
 * @date 2022/11/18 19:29
 */
@RestController
public class TesetMqttCllientController {
    /**
     * 自定义回调类创建
     */
    @Autowired
    MqttCallBackCreateByUser backCreateByUser;
    /**
     * 客户端创建
     */
    @Autowired
    MqttClientCreate mqttClientCreate;
    /**
     * 自定义回调类
     */
    @Autowired
    CallBackByMe callBackByMe;
    /**
     * 客户端管理
     */
    @Autowired
    MqttClientManager mqttClientManager;

    @Autowired
    MqttCallBackContext mqttCallBackContext;

    @Autowired
    TestBack testBack;

    /**
     * 创建客户端接口
     */
    @GetMapping("/")
    public void testClientLinked(){
        //设置自己的回调函数
        //backCreateByUser.createMyCallBack("younger9527", callBackByMe);
        System.out.println(mqttCallBackContext.getCallBackMap());
        //创建客户端并连接（注：这里）
        mqttClientCreate.createMqttClient();
    }

    /**
     * 获取客户端连接信息
     * @return
     */
    @GetMapping("/getClientInfo")
    public String getClientInfo(){
        Map<String, MqttClient> clientMap = mqttClientManager.getMqttClientMap();
        if(clientMap.size() != 0){
            for(Map.Entry<String, MqttClient> clientInfo : clientMap.entrySet()){
                System.out.println("当前连接的客户端Id为：" + clientInfo.getKey());
            }
        }else{
            System.out.println("当前无客户端连接");
        }
        return "当前连接数为：" + clientMap.size();
    }

    /**
     * 关闭连接
     */
    @GetMapping("/close")
    public void closeConnect(){
        MqttClientUtil.closeConnect("younger9527", mqttClientManager);
    }

    /**
     * 发送消息
     */
    @GetMapping("/sendMessage")
    public void sendMessageTest(){
        MqttClientUtil.publishMessage("test/1", "你好", 0, "younger9527", mqttClientManager);
    }
}
