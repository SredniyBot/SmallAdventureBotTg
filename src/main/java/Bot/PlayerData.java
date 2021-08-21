package Bot;

import java.io.*;

public class PlayerData extends Data {

    PlayerData(String chatId) {
        super(chatId);
        if(!new File(Path).exists()) {
            init();
        }
    }

    @Override
    public void init() {
        super.init();
        new File(Path+"/messages").mkdir();
        try {
            new File(Path+"/Status.txt").createNewFile();
            new File(Path+"/messages/log.txt").createNewFile();
            File f=new File(Path+"/chatId.txt");
            f.createNewFile();
            FileWriter Fw = new FileWriter(f);
            Fw.write(chatId);
            Fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setWaitingStatus(boolean s){
        File f=new File(Path+"/Status.txt");
        try {
            f.createNewFile();
            FileWriter Fw = new FileWriter(f);
            Fw.write(String.valueOf(s));
            Fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean getWaitingStatus(){
        try {
            FileReader fr=new FileReader(Path+"/Status.txt");
            BufferedReader b = new BufferedReader(fr);
            String s=b.readLine();
            b.close();
            return Boolean.valueOf(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
