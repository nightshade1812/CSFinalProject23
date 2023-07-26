import javax.sound.sampled.*;
import java.io.*;

public class SoundEffect
{
   private Clip clip;
   private File file;
   
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
   
   public void loop()
   {
      clip.loop(Clip.LOOP_CONTINUOUSLY);
   }
   
   public void play()
   {
      clip.start();
   }
}