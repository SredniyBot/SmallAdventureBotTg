package Bot;

import Bot.Bot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Stage {


    private String stageText;
    private boolean hasImage,hasText,hasChoice;
    private BufferedImage image;
    private String text;
    private ArrayList<Object[]> choices;

    Stage(String stageText){
        if(stageText.equals(null)){
            hasImage=false;
            hasText=true;
            hasChoice=false;
            text="ERROR";
        }else {
            this.stageText = stageText;
            getText();
            getImage();
            getChoices();
        }
    }

    public String getText(){
        if(stageText.contains("txt=<start>")){
            hasText=true;
            String t=stageText.substring(stageText.indexOf("txt=<start>")+11);
            text=t.substring(0,t.indexOf("<end>"));
        }else {
            hasText=false;
        }
        return text;
    }

    public String getImage(){
        String t="";
        if(stageText.contains("img=<start>")){
            hasImage=true;
            t=stageText.substring(stageText.indexOf("img=<start>")+11);
            t=t.substring(0,t.indexOf("<end>"));
            try {

                image= ImageIO.read(new File(t));
            } catch (IOException e) {
                hasImage=false;
            }
        }else {
            hasImage=false;
            return null;
        }
        return t;
    }

    public ArrayList<Object[]> getChoices(){
        choices=new ArrayList();
        if(stageText.contains("cho=<start>")){
            hasChoice=true;
            String t=stageText;
            while (t.contains("cho=<start>")){
                t=t.substring(t.indexOf("cho=<start>")+11);
                String name=t.substring(0,t.indexOf("~~~"));
                Integer num=Integer.valueOf(t.substring(t.indexOf("~~~")+3,t.indexOf("<end>")));
                Object[] o = new Object[2];
                o[0]=name;
                o[1]=num;
                choices.add(o);
            }
        }else {
            hasChoice=false;
        }
        return choices;
    }

    public boolean isHasImage() {
        return hasImage;
    }

    public boolean isHasText() {
        return hasText;
    }

    public boolean isHasChoice() {
        return hasChoice;
    }
}
