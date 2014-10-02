
import javax.swing.JFrame;

public class DictionaryMain
{
   //-----------------------------------------------------------------
   //  Creates and displays the temperature converter GUI.
   //-----------------------------------------------------------------
   public static void main (String[] args)
   {
      JFrame frame = new JFrame ("Phone Dictionary");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      GraphicDriver panel = new GraphicDriver();

      frame.getContentPane().add(panel);
      frame.pack();
      frame.setVisible(true);
   }
}