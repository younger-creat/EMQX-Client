package cn.younger.pojo;

import lombok.Data;

/**
 * @author younger
 * @version 1.0
 * @description: MQTT客户端数据类
 * @date 2022/11/18 19:17
 */
@Data
public class MqttClientVO {
    /**
     * 客户端ID
     */
    public String clientId;

    /**
     *订阅主题
     */
    public String subscribeTopic;

    /**
     * 用户名
     */
    public String username;

    /**
     * 密码
     */
    public String password;

    /**
     * 连接超时时间
     */
    public int timeout;

    /**
     * 心跳时间
     */
    public int keepAliveTime;

    /**
     * 服务器地址
     */
    public String broker;

    /**
     * 是否清空session
     */
    public boolean flag;


}
