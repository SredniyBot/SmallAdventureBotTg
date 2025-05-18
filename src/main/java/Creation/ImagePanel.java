package Creation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel{

    private JLabel l;
    private JButton b;
    private ImageIcon errorl;
    private Stage s;
    private int Size =300;
    private String URL= "Images";
    {
        ClassLoader classLoader = Program.class.getClassLoader();
        URL = classLoader.getResource(URL).getPath();

    }

    ImagePanel(Stage s){
        this.s=s;
        createLabel();
        createBtnChangeImg();
    }

    private void createLabel(){
        try {
            errorl=new ImageIcon(resizeImage(ImageIO.read(new File(URL+"/error.png")),Size,Size));
        } catch (IOException e) {
            System.out.println("Error, no error image");
        }

        if(s.isHasImage()){
            l= new JLabel(new ImageIcon(resizeImage(s.getImage(),Size,Size)));
        }else{
            l=new JLabel(errorl);
        }
        add(l);
    }

    private void createBtnChangeImg(){
        b=new JButton("change image");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    changeImage();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        add(b);
    }

    private void changeImage() throws IOException {
        JFileChooser fc = new JFileChooser();
        if(fc.showDialog(null,"Открыть файл")==JFileChooser.APPROVE_OPTION){
            BufferedImage buf= ImageIO.read(fc.getSelectedFile());
            File c=new File(URL+"/"+fc.getSelectedFile().getName());
            ImageIO.write(resizeImage(buf,300,buf.getHeight()*300/buf.getWidth()),"png",c);
            l.setIcon(new ImageIcon(resizeImage(buf,Size,Size)));
            s.setImage(URL+"/"+fc.getSelectedFile().getName());
        }

    }

    private BufferedImage resizeImage(Image img, int newW, int newH) {
        BufferedImage scaledBI = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = scaledBI.createGraphics();
        g.drawImage(img, 0, 0, newW, newH, null);
        g.dispose();
        return scaledBI;
    }

}
