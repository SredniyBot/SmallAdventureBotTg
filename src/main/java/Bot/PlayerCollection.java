package Bot;

import Creation.Program;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.File;
import java.util.HashMap;

public class PlayerCollection {
    private static String URL="";
    private static HashMap<String,Player> players;
    private static Bot bot;

    PlayerCollection(Bot bot){
        ClassLoader classLoader = Program.class.getClassLoader();

        System.out.println(this.getClass().getResource("/").getPath()+"Players/");
        URL = this.getClass().getResource("/").getPath()+"Players/";

        this.bot=bot;
        players=new HashMap<>();
        rememberPlayers();
    }

    public Player getPlayer(Update update){
        String chatId;
        if(update.hasCallbackQuery()) {
            chatId= String.valueOf(update.getCallbackQuery().getMessage().getChatId());
        } else {
            chatId=update.getMessage().getChatId().toString();
        }
        if(players.containsKey(chatId)) {
            return players.get(chatId);
        }else{
            Player p=new Player(bot,chatId);
            players.put(chatId,p);
            return p;
        }
    }

    private static void rememberPlayers(){
        File f = new File(URL);
        for(File file:f.listFiles()){
            System.out.println(file.getName());
            players.put(file.getName(),new Player(bot,file.getName()));
        }
    }

}
