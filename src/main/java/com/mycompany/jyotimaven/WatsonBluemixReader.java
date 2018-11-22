/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jyotimaven;

import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.SynthesizeOptions;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voices;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Biswajit Roy
 */
public class WatsonBluemixReader {
    public static void textToSpeech(String stringToVoicify){
        AudioInputStream sound = null;
        try {
            TextToSpeech service = new TextToSpeech();
            IamOptions options = new IamOptions.Builder()
                .apiKey(Keydata.BLUEMIX_KEY)
                .build();
            service.setIamCredentials(options);
            
            service.setEndPoint("https://gateway-wdc.watsonplatform.net/text-to-speech/api");
            
            System.out.println(service.getEndPoint());
//            Voices voices = service.listVoices().execute();
//            System.out.println(voices);  
            
            SynthesizeOptions synthesizeOptions = new SynthesizeOptions.Builder()
                    .text(stringToVoicify)
                    .voice(SynthesizeOptions.Voice.EN_US_LISAVOICE)
                    .accept(SynthesizeOptions.Accept.AUDIO_WAV)
                    .build();
            InputStream in = service.synthesize(synthesizeOptions).execute();
            InputStream reformatted_in = WaveUtils.reWriteWaveHeader(in);
            sound = AudioSystem.getAudioInputStream(new BufferedInputStream(reformatted_in));
            Clip clip = AudioSystem.getClip();
            clip.open(sound);
            clip.start();
            System.out.println("End of Stream !!!");
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(WatsonBluemixReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WatsonBluemixReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(WatsonBluemixReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                sound.close();
            } catch (IOException ex) {
                Logger.getLogger(WatsonBluemixReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
      
    public static void playAudioFeedback(String soundFile) {
        try {
            File f = new File("" + soundFile);
            InputStream playStream = new FileInputStream(f);
            InputStream reformattedStream = WaveUtils.reWriteWaveHeader(playStream);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new BufferedInputStream(reformattedStream));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(listennlearn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(listennlearn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(listennlearn.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
}
}
