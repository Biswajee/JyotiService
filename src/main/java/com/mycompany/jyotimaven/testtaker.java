/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jyotimaven;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import net.thegreshams.firebase4j.service.Firebase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Biswajit Roy
 */
public class testtaker extends javax.swing.JFrame {

    /**
     * Creates new form testtaker
     */
    
    static int nextQues = 0, CorrectAns = 0;
    QuizParser refinedData;
    double averageValue;
    public testtaker() {
        try {
            initComponents();
            
            String urlString = "https://jyotiserver.firebaseapp.com/quiz.json";
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            // parsing file "sampleQuizData.json"
            Object obj = new JSONParser().parse(new InputStreamReader(is));
            quizProcessor(nextQues++, obj);
        } catch (MalformedURLException ex) {
            Logger.getLogger(testtaker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(testtaker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(testtaker.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    public void quizProcessor(int qno, Object obj){
        //Setting user id...
        userID.setText(UserData.email);
        // typecasting obj to JSONObject
        JSONObject jo = (JSONObject) obj;
        // getting body array...
        JSONArray bodyData = (JSONArray) jo.get("body");
        System.out.println(bodyData.toArray()[qno]);
        JSONObject result = (JSONObject) bodyData.toArray()[qno];
        Gson gson = new Gson();
        refinedData = gson.fromJson(result.toString(), QuizParser.class);
        //Setting values according the quiz set...
        option1.setText(refinedData.option1);
        option2.setText(refinedData.option2);
        option3.setText(refinedData.option3);
        context.setText("<html>" + refinedData.title + "</html>");
        
        CompletableFuture.runAsync(new Runnable() {
                @Override
                public void run() {
                    try {
                        WatsonBluemixReader.textToSpeech(refinedData.title);
                        Thread.sleep(4000);
                        WatsonBluemixReader.playAudioFeedback("audio\\instruction_options.wav");
                        Thread.sleep(4000);
                        WatsonBluemixReader.textToSpeech(refinedData.option1);
                        WatsonBluemixReader.textToSpeech(refinedData.option2);
                        WatsonBluemixReader.textToSpeech(refinedData.option3);
                        Thread.sleep(2500);
                        WatsonBluemixReader.playAudioFeedback("audio\\starting_service.wav");
                        
                        Runtime runtime = Runtime.getRuntime();
                        Process proc = runtime.exec("powershell.exe python scripts\\fingerdetect.py");
                        InputStream processStream = proc.getInputStream();
                        InputStream errorStream = proc.getErrorStream();
                        BufferedReader processBuffer = new BufferedReader(new InputStreamReader(processStream), 1);
                            String line;
                            int total = 0, eachValue, counter = 1;
                            while ((line = processBuffer.readLine()) != null) {
                                eachValue = Integer.parseInt(line);
                                total = total + eachValue;
                                counter++;
                            }

                            //Average value obtained by streaming gesture...
                            double averageValue = (double)total/counter;

                            int gestureResult = (int)Math.round(averageValue) + 1;
                            processStream.close();
                            processBuffer.close();
                            
                            if(gestureResult == 1){
                                option1.setSelected(true);
                                WatsonBluemixReader.playAudioFeedback("audio\\alert_result.wav");
                                WatsonBluemixReader.textToSpeech(refinedData.option1);
                                
                            }
                            else if(gestureResult == 2) {
                                option2.setSelected(true);
                                WatsonBluemixReader.playAudioFeedback("audio\\alert_result.wav");
                                WatsonBluemixReader.textToSpeech(refinedData.option2);
                            }
                            else if(gestureResult == 3) {
                                option3.setSelected(true);
                                WatsonBluemixReader.playAudioFeedback("audio\\alert_result.wav");
                                WatsonBluemixReader.textToSpeech(refinedData.option3);
                            }
                        
                    } catch (InterruptedException ex) {
                        Logger.getLogger(testtaker.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(testtaker.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        title = new javax.swing.JLabel();
        quit = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        labeluserID = new javax.swing.JLabel();
        userID = new javax.swing.JLabel();
        context = new javax.swing.JLabel();
        nextquesbtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        option1 = new javax.swing.JRadioButton();
        option2 = new javax.swing.JRadioButton();
        option3 = new javax.swing.JRadioButton();
        infoLabel = new javax.swing.JLabel();
        novoicebtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jyoti - Quizzing Service");
        setResizable(false);

        title.setFont(new java.awt.Font("HP Simplified Light", 0, 24)); // NOI18N
        title.setForeground(new java.awt.Color(51, 51, 51));
        title.setText("Jyoti - Quizera Platform");

        quit.setText("Quit");
        quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitActionPerformed(evt);
            }
        });

        labeluserID.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        labeluserID.setForeground(new java.awt.Color(51, 51, 51));
        labeluserID.setText("User ID:");

        userID.setForeground(new java.awt.Color(102, 102, 102));

        nextquesbtn.setText("Next Question");
        nextquesbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextquesbtnActionPerformed(evt);
            }
        });

        buttonGroup1.add(option1);
        option1.setText("Option");

        buttonGroup1.add(option2);
        option2.setText("Option");

        buttonGroup1.add(option3);
        option3.setText("Option");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(option1)
                    .addComponent(option2)
                    .addComponent(option3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(option1)
                .addGap(28, 28, 28)
                .addComponent(option2)
                .addGap(35, 35, 35)
                .addComponent(option3)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        infoLabel.setForeground(new java.awt.Color(102, 102, 102));
        infoLabel.setText("<html>*Note: You can quit the test any time by clicking on quit button. Your test will not be counted towards score.</html>");

        novoicebtn.setText("Disable voice feedback");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(title)
                        .addGap(169, 169, 169)
                        .addComponent(quit))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(infoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nextquesbtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(labeluserID)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(userID, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(context, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(novoicebtn)
                                .addGap(14, 14, 14)))))
                .addContainerGap())
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quit)
                    .addComponent(title))
                .addGap(15, 15, 15)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(novoicebtn)
                            .addComponent(userID, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(context, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(labeluserID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nextquesbtn, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(infoLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextquesbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextquesbtnActionPerformed
        System.out.println(CorrectAns);
        dispose();
        if(nextQues<4){
            new testtaker().setVisible(true);    
        }
            else{
            try {
                //Upload results...
                JSONObject sessionData = new JSONObject();
                sessionData.put("userid", UserData.email);
                sessionData.put("score", CorrectAns+"");
                
                Firebase firebase = new Firebase("https://jyotiserver.firebaseio.com/");
                System.out.println(firebase);
                firebase.post(sessionData);
                JOptionPane.showMessageDialog(null, "Thank you for taking the quiz, we will upload the results shortly !");
                new scoreCard().setVisible(true);
            } catch (FirebaseException ex) {
                Logger.getLogger(testtaker.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JacksonUtilityException ex) {
                Logger.getLogger(testtaker.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(testtaker.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
    }//GEN-LAST:event_nextquesbtnActionPerformed

    private void quitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitActionPerformed

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
            java.util.logging.Logger.getLogger(testtaker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(testtaker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(testtaker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(testtaker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

//        testtaker ts = new testtaker();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if(nextQues<4)
                   new testtaker().setVisible(true);
            else{
                JOptionPane.showMessageDialog(null, "Thank you for taking the quiz, we will upload the results shortly !");
                new dashboard().setVisible(true);
            }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel context;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labeluserID;
    private javax.swing.JButton nextquesbtn;
    private javax.swing.JButton novoicebtn;
    private javax.swing.JRadioButton option1;
    private javax.swing.JRadioButton option2;
    private javax.swing.JRadioButton option3;
    private javax.swing.JButton quit;
    private javax.swing.JLabel title;
    private javax.swing.JLabel userID;
    // End of variables declaration//GEN-END:variables
}
