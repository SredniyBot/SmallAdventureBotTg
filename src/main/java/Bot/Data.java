package Bot;

import java.io.File;

public abstract class Data {

    public String chatId,Path;

    Data(String chatId){
        this.chatId=chatId;
        Path= this.getClass().getResource("/").getPath()+"Players/"+chatId;
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
