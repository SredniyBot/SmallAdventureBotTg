package Bot;

import java.io.File;
import java.util.Objects;

public abstract class Data {

    public String chatId,Path;

    Data(String chatId){
        this.chatId=chatId;
//        Path= this.getClass().getResource("/").getPath()+"Players/"+chatId;
        Path="F:\\resources/Players/"+chatId;
    }

    public void init(){
        if(!new File(Path).exists()){
            new File(Path).mkdir();
        }
    }

    public String getChatId(){
        return chatId;
    }

}
