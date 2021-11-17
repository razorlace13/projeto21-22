/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messaging_sub;

import static com.sun.glass.ui.Application.run;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
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
    ObjectMapper mapper = new ObjectMapper();
    List<Purchases> m_arrPurchases = new ArrayList<Purchases>();
    
    public void connectionLost(Throwable throwable) {
        System.out.println("Perda de ligação ao mosquitto"); 
    }
    
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        JsonNode rootNode = mapper.readTree(mqttMessage.getPayload());
        int Dados_id_purchase = rootNode.get("id_purchase").asInt();
        int Dados_mesa = rootNode.get("mesa").asInt();
        double Dados_valor = rootNode.get("valor").asDouble();
        Purchases purchases = new Purchases(Dados_id_purchase,Dados_mesa,Dados_valor);
        m_arrPurchases.add(purchases);
        System.out.println("Eu sou um teste:\n\t"+ m_arrPurchases);        
    }
    
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        // Não usado, para já…
    }
}
