package Creation;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Stage {

    private static String stagesText="";
    private String stageText;
    private boolean hasImage,hasText,hasChoice;
    private BufferedImage image;
    private String text;
    private int number;
    private boolean hasNumber=false;
    private static String URL="Stages";
    ArrayList<Object[]> choices;

    Stage(){
        if(hasNumber){
            getStage(number);
        }
        try {
            stagesText= Creation.Program.getStoryText();
        } catch (IOException e) {
            error();
        }
    }

    public Stage getStage(int number)  {
        this.number=number;
        hasNumber=true;
        stageText=stagesText.substring(stagesText.indexOf("***"+number+"***"));
        stageText=stageText.substring(stageText.indexOf("[[[")+3,stageText.indexOf("]]]"));

        getText();
        getImage();
        getChoices();
        return this;
    }

    public Stage createStage(int number){
        this.number=number;
        hasImage=false;
        hasChoice=false;
        hasText=false;
        try {
            FileWriter fl =new FileWriter(URL);
            stagesText=stagesText+"\n***"+number+"***[[[\n]]]";

            fl.write(stagesText);
            fl.flush();

            Thread.sleep(1000);
        } catch (IOException e) {
            error();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
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

    public BufferedImage getImage(){

        if(stageText.contains("img=<start>")){
            hasImage=true;
            String t=stageText.substring(stageText.indexOf("img=<start>")+11);
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
        return image;
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

    public void delChoice(Object[] ch){
        String t ="cho=<start>"+ch[0]+"~~~"+ch[1]+"<end>\n";
        choices.remove(ch);
        stageText=stageText.replace(t,"");
        correctStageText();
        if (stageText.contains("cho=<start>")){
            hasChoice=true;
        }else {
            hasChoice=false;
        }
    }

    public void addChoice(Object[] ch){
        choices.add(ch);
        stageText+="cho=<start>"+ch[0]+"~~~"+ch[1]+"<end>\n";
        correctStageText();
        hasChoice=true;
    }

    public void setText(String text){
        if(hasText){
            String start=stageText.substring(0,stageText.indexOf("txt=<start>")+11);
            String end=stageText.substring(stageText.indexOf("txt=<start>")+11);
            end=end.substring(end.indexOf("<end>"));
            stageText=start+text+end;
        }else{
            text="txt=<start>"+text+"<end>\n";
            stageText+=text;
        }
        correctStageText();
        hasText=true;
    }

    public void setImage(String text){
        getStage(number);
        if(hasImage){
            String start=stageText.substring(0,stageText.indexOf("img=<start>")+11);
            String end=stageText.substring(stageText.indexOf("img=<start>")+11);
            end=end.substring(end.indexOf("<end>"));
            stageText=start+text+end;
        }else{
            text="img=<start>"+text+"<end>\n";
            stageText+=text;
        }
        correctStageText();
        hasImage=true;
    }

    private void correctStageText(){
        String old=stagesText.substring(stagesText.indexOf("***"+number+"***"));
        old=old.substring(0,old.indexOf("]]]")+3);
        String n="***"+number+"***[[["+stageText+"]]]";
        stagesText=stagesText.replace(old,n);
        try {
            FileWriter fl =new FileWriter(URL);
            fl.write(stagesText);
            fl.flush();
            Thread.sleep(1000);
        } catch (IOException e) {
            error();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

    private void error(){
        hasImage=false;
        hasText=true;
        hasChoice=false;
        text="ERROR";
    }
}
