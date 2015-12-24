package com.acme;

import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

public class BlackBox {
   public static final int ANSWER;

   static {
       Properties properties = new Properties();
       InputStream inStream = BlackBox.class.getClassLoader().getResourceAsStream("answers.properties");
       try {
           properties.load(inStream);
       } catch (IOException ex) {
       } finally {
           try {
               inStream.close();
           } catch (IOException ex) {
           }
       }
       ANSWER = Integer.valueOf(properties.getProperty("answerToTheUltimateQuestionOfLifeTheUniverseAndEverything", "0"));
   }

    public static int getAnswerToTheUltimateQuestionOfLifeTheUniverseAndEverything() {
        return ANSWER;
    }
}
