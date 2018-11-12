/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jyotimaven;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.*; 
import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Biswajit Roy
 */
public class listennlearn extends javax.swing.JFrame {

    /**
     * Creates new form listennlearn
     */
    String content = "";
    Process proc;
    static int nextNews = 0;
      
    public listennlearn() throws ParseException, LineUnavailableException {
            initComponents();
            worker(nextNews++);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        context = new javax.swing.JLabel();
        info = new javax.swing.JLabel();
        nextnewsbtn = new javax.swing.JButton();
        infotitle = new javax.swing.JLabel();
        gesturevalL = new javax.swing.JLabel();
        statusL = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jyoti - Listen and Learn");
        setResizable(false);

        title.setFont(new java.awt.Font("HP Simplified Light", 0, 24)); // NOI18N
        title.setForeground(new java.awt.Color(102, 102, 102));
        title.setText("Jyoti - Listen and Learn");

        context.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        info.setForeground(new java.awt.Color(102, 102, 102));
        info.setText("Daily news report:");

        nextnewsbtn.setText("Next news");
        nextnewsbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextnewsbtnActionPerformed(evt);
            }
        });

        infotitle.setForeground(new java.awt.Color(102, 102, 102));
        infotitle.setText("powered by The Times of India and NewsAPI.org");

        gesturevalL.setFont(new java.awt.Font("HP Simplified Light", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(title)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(infotitle)
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(info, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(context, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(statusL, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 778, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(gesturevalL, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nextnewsbtn)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title)
                    .addComponent(infotitle))
                .addGap(9, 9, 9)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(info)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(statusL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(context, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE))
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nextnewsbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gesturevalL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextnewsbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextnewsbtnActionPerformed
        
        try {
            dispose();
            new listennlearn().setVisible(true);
            
        } catch (ParseException | LineUnavailableException ex) {
            Logger.getLogger(listennlearn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_nextnewsbtnActionPerformed

    private void worker(int i) {
        try {
            //The Times of India News API Call...
            String urlString = "https://newsapi.org/v2/top-headlines?country=in&apiKey=" + Keydata.NEWS_API_KEY;
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            JsonElement element = new JsonParser().parse(new InputStreamReader(is));
            JsonElement articles = element.getAsJsonObject().get("articles");
            
            String title = articles.getAsJsonArray().get(i).getAsJsonObject().get("title").toString();
            String description = articles.getAsJsonArray().get(i).getAsJsonObject().get("description").toString();
            String newscontent = articles.getAsJsonArray().get(i).getAsJsonObject().get("content").toString();
            
            //refining strings
            title = title.substring(1, title.length()-1);
            description = description.substring(1, description.length()-1);
            newscontent = newscontent.substring(1, newscontent.length()-1);
            
            this.content = title + description + newscontent;
            
            //formatting the json string...
            String formattedContent  = "<html>" + title + "<br>" + description + "<br><br>" + newscontent + "</html>";
            
            context.setText(formattedContent);
            ImageIcon statusImg = new ImageIcon(new ImageIcon("images\\status_wait.gif").getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
            statusL.setIcon(statusImg);
            
            /* Plays intro feedback just after clicking Listen and Learn.
             * Sound: Jyoti will run a device test now.
             * Please indicate a finger gesture.
            */
            
            WatsonBluemixReader.playAudioFeedback("audio\\start.wav");
            
            
            CompletableFuture.runAsync(new Runnable() {
                @Override
                public void run() {
                    WatsonBluemixReader.textToSpeech(content);
                    ImageIcon statusImg = new ImageIcon(new ImageIcon("images\\status_playing.gif").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                    statusL.setIcon(statusImg);
                }
            });
            
            
            
//            Runtime runtime = Runtime.getRuntime();
//            proc = runtime.exec("powershell.exe python scripts\\fingerdetect.py");
//            InputStream processStream = proc.getInputStream();
//            InputStream errorStream = proc.getErrorStream();
//            BufferedReader processBuffer = new BufferedReader(new InputStreamReader(processStream), 1);
//                String line;
//                int total = 0, eachValue, counter = 1;
//                while ((line = processBuffer.readLine()) != null) {
//                    eachValue = Integer.parseInt(line);
//                    total = total + eachValue;
//                    counter++;
//                }
//                
//                //Average value obtained by streaming gesture...
//                double averageValue = (double)total/counter;
//                
//                gesturevalL.setText(""+ Math.round(averageValue));
//                processStream.close();
//                processBuffer.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(listennlearn.class.getName()).log(Level.SEVERE, null, ex);
            context.setText("You are not connected to the internet");
        } catch (IOException ex) {
            Logger.getLogger(listennlearn.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"There was an error parsing the JSON");
        }

    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Windows look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Windows (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(listennlearn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(listennlearn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(listennlearn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(listennlearn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new listennlearn().setVisible(true);
                } catch (ParseException | LineUnavailableException ex) {
                    Logger.getLogger(listennlearn.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel context;
    private javax.swing.JLabel gesturevalL;
    private javax.swing.JLabel info;
    private javax.swing.JLabel infotitle;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton nextnewsbtn;
    private javax.swing.JLabel statusL;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
