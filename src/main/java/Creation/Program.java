package Creation;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public  class Program {
    private static String stagesText="";
    private static String URL="Stages";


    public static void main(String[] args) {
        window();
    }

    private static void window(){
        JFrame w = new JFrame("Creation");
        Panel p =new Panel();
        w.add(p);
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setSize(1000, 1000);
        //w.setResizable(false);
        w.setVisible(true);
    }

    public static String getStoryText() throws IOException {

            ClassLoader classLoader = Program.class.getClassLoader();
            URL = classLoader.getResource("Stages").getPath();
            FileReader tfr = new FileReader(URL);
            char[] buffer = new char[8096];
            int chars = tfr.read(buffer);
            while (chars != -1) {
                stagesText = String.valueOf(buffer, 0, chars);
                chars = tfr.read(buffer);
            }
            tfr.close();
        return stagesText;
    }


}
