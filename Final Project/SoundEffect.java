//Created by Sarah Trainer and Satik Karki, final version completed 7/26/2023

import javax.sound.sampled.*;
import java.io.*;

/**
*The SoundEffect class is used to manage and play all of the sound clips in the game, using the AudioSystem class
@see  javax.sound.sampled.AudioSystem
*/
public class SoundEffect
{
   /**
   *A generic placeholder for an audio clip
   @see  javax.sound.sampled.Clip
   */
   private Clip clip;

   /**
   *The file from which the clip audio is sourced
   @see  java.io.File
   */
   private File file;

   /**
   *Creates a new SoundEffect from the name of a file
   @param   filename - The name of the audio file
   */
   public SoundEffect(String filename)
   {
      try {
         file = new File(filename);
         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
         clip = AudioSystem.getClip();
         clip.open(audioInputStream);
      }
      catch(UnsupportedAudioFileException e) {
         System.out.println(">:(");
         System.exit(0);
      }
      catch(IOException e) {
         System.out.println(">:(((");
         System.exit(0);
      }
      catch(LineUnavailableException e) {
         System.out.println(">:)");
         System.exit(0);
      }
   }

   /**
   *Loops a sound clip continuously
   */
   public void loop()
   {
      clip.loop(Clip.LOOP_CONTINUOUSLY);
   }

   /**
   *Plays a sound clip
   */
   public void play()
   {
      clip.start();
   }
}
