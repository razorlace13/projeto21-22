/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messaging_sub;

import static com.sun.glass.ui.Application.run;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author claud
 */
public class MosquittoCallBack implements MqttCallback {
    public Purchases purchases;
    public void connectionLost(Throwable throwable) {
        System.out.println("Perda de ligação ao mosquitto"); 
    }
    
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("Mensagem recebida:\n\t"+ new String(mqttMessage.getPayload())
        +"topico:"+s);
        private String mq = mqttMessage.getPayload();
        purchases = mqttMessage.getPayload();
    }
    
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        // Não usado, para já…
    }
}
