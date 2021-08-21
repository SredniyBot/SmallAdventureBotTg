package Bot;

import java.io.IOException;


public class Story {

    private static String stagesText="";
    private boolean hasNumber=false;
    private int number;

    Story(){
        if(hasNumber){
            getStage(number);
        }
        try {
            stagesText= Creation.Program.getStoryText(); /// ЗАМЕНИТЬ ВСЕ К ЧЕРТЯМ

        } catch (IOException e) {
            getStage(null);
        }
    }

    public Stage getStage(Integer number)  {
        this.number=number;
        hasNumber=true;
        Stage s=new Stage(stageText(number));
        return s;
    }

    private String stageText(Integer number){
        if(number.equals(null)){
            return null;
        }
        String t=stagesText.substring(stagesText.indexOf("***"+number+"***"));
        t=t.substring(t.indexOf("[[[")+3,t.indexOf("]]]"));
        return t;
    }

}
