/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messaging_sub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ListView;
import javax.swing.DefaultListModel;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author claud
 */
public class form_visual extends javax.swing.JFrame {
    MqttClient Client;
    public ListView<String> listview;
    ObjectMapper mapper = new ObjectMapper();
    DefaultListModel<String> cModel = new DefaultListModel<>();
    DefaultListModel<String> cModelConsumo = new DefaultListModel<>();
    ArrayList<Purchases> m_arrListaPurchases;
    /**
     * Creates new form form_visual
     */
    public form_visual() {
        initComponents();
        cModel = new DefaultListModel();
        cModelConsumo = new DefaultListModel();
        m_arrListaPurchases = new ArrayList();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listPurchases = new javax.swing.JList<>();
        bt_ligar = new javax.swing.JButton();
        jTextAreaMsg = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ListConsumo = new javax.swing.JList<>();
        bt_remover = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        listPurchases.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listPurchasesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listPurchases);

        bt_ligar.setText("Ligar");
        bt_ligar.setName(""); // NOI18N
        bt_ligar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_ligarMouseClicked(evt);
            }
        });

        jScrollPane2.setViewportView(ListConsumo);

        bt_remover.setText("Remover");
        bt_remover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_removerMouseClicked(evt);
            }
        });
        bt_remover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_removerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bt_ligar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextAreaMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_remover))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_ligar)
                        .addComponent(bt_remover))
                    .addComponent(jTextAreaMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_ligarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_ligarMouseClicked
        try{
            Client = new MqttClient("tcp://localhost:1884",MqttClient.generateClientId(), null);
            Client.setCallback(new MqttCallback() {
            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                JsonNode rootNode = mapper.readTree(mqttMessage.getPayload());
                int Dados_id_purchase = rootNode.get("id_purchase").asInt();
                int Dados_mesa = rootNode.get("mesa").asInt();
                double Dados_valor = rootNode.get("valor").asDouble();
                Purchases purchases = new Purchases(Dados_id_purchase,Dados_mesa,Dados_valor);
                addtoList(purchases);
            }
            
            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                jTextAreaMsg.setText("");
                jTextAreaMsg.setText("mensagem enviada" + "\r\n" + jTextAreaMsg.getText());
            }
            
            @Override
            public void connectionLost(Throwable exception) {
                jTextAreaMsg.setText("");
                jTextAreaMsg.setText("Impossível enviar mensagem" + "\r\n" + jTextAreaMsg.getText());
            }

                private void addtoList(Purchases purchases) {
                    m_arrListaPurchases.add(purchases);
                    cModel.clear();
                    listPurchases.setModel(cModel);
                    Purchases purc = new Purchases();
                    for (int i = 0; i < m_arrListaPurchases.size(); i++)
                    {
                        purc = m_arrListaPurchases.get(i);
                        String pep = purc.toString();
                        cModel.addElement(pep);
                    }
                    listPurchases.setModel(cModel);
                }
            });
            Client.connect();
            Client.subscribe("INSERT");
            jTextAreaMsg.setText("");
            jTextAreaMsg.setText("Ligado ao Mosquitto" + "\r\n" + jTextAreaMsg.getText());
        }
        catch(MqttException ex)
        {
            jTextAreaMsg.setText("");
            jTextAreaMsg.setText("Impossível ligar ao Mosquitto" + "\r\n" + jTextAreaMsg.getText());
        }
    }//GEN-LAST:event_bt_ligarMouseClicked

    private void getProducts(int id_purchase) throws MalformedURLException, IOException {
                    String url = "http://10.80.226.92:1884/v1/consumo/consumopedido/" + id_purchase + "?access-token=F_Fu2do9PM8hdn0LCX4_YPpTtDgsJIZi";
                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");
                    int responseCode = con.getResponseCode();
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())); 
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine())!= null){
                        response.append(inputLine);
                    }
                    in.close();
                        JSONArray Consumos = new JSONArray(response.toString());
                        System.out.println("array consumos: " + Consumos);
                        for (int i = 0; i != Consumos.length(); i++){
                            try{
                                JSONObject myResponse = new JSONObject(Consumos.getJSONObject(i).toString());
                                int Dados_id_consumo = myResponse.getInt("id_consumo");
                                String Dados_name = myResponse.getString("name");
                                int Dados_id_pedido = myResponse.getInt("id_pedido");
                                int Dados_quantidade = myResponse.getInt("quantidade");
                                Consumo consumo = new Consumo(Dados_id_consumo,Dados_name,Dados_id_pedido,Dados_quantidade);
                                addtoListConsumo(consumo);
                            }catch(Exception e){
                                System.out.println("nao recebeu");
                            }
                        }
                }
    
    private void addtoListConsumo(Consumo consumo) {
                    
                    System.out.println(consumo);                    
                    cModelConsumo.addElement(consumo.toString());
                    ListConsumo.setModel(cModelConsumo);
                }
    
    private void listPurchasesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listPurchasesMouseClicked
        if (listPurchases.getSelectedIndex()!= -1)
        {
            int index = listPurchases.getSelectedIndex();
            Purchases purchases = m_arrListaPurchases.get(index);
            cModelConsumo.clear();
            try {
                getProducts(purchases.getId_purchase());
            } catch (IOException ex) {
                Logger.getLogger(form_visual.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_listPurchasesMouseClicked

    private void bt_removerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_removerActionPerformed
            
    }//GEN-LAST:event_bt_removerActionPerformed

    private void bt_removerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_removerMouseClicked
       if (listPurchases.getSelectedIndex()!= -1)
        {
            int index = listPurchases.getSelectedIndex();
            System.out.println("index: " + index);
            m_arrListaPurchases.remove(index);
            cModel.remove(index);
            listPurchases.setModel(cModel);
            cModelConsumo.clear();
            cModelConsumo.clear();
        }
    }//GEN-LAST:event_bt_removerMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(form_visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_visual().setVisible(true);
                
            }
        });
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> ListConsumo;
    private javax.swing.JButton bt_ligar;
    private javax.swing.JButton bt_remover;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jTextAreaMsg;
    private javax.swing.JList<String> listPurchases;
    // End of variables declaration//GEN-END:variables

}
