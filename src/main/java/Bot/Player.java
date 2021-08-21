package Bot;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Timer;
import java.util.TimerTask;

public class Player {

    private String chatId;
    private Bot bot;
    private PlayerData data;
    private boolean waitingForAnswer;
    private Timer timeOfLast;

    Player(Bot bot, String chatId){
        init(bot,chatId);
    }

    Player(Bot bot, Update update){
        if(update.hasCallbackQuery()) {
            chatId= String.valueOf(update.getCallbackQuery().getMessage().getChatId());
        } else {
            chatId=update.getMessage().getChatId().toString();
        }
        init(bot,chatId);
    }

    private void init(Bot bot,String chatId){
        this.bot=bot;
        timeOfLast=new Timer();
        this.chatId=chatId;
        data = new PlayerData(chatId);
    }

    public PlayerData getData() {
        return data;
    }

    public Bot getBot() {
        return bot;
    }

    public String getChatId(){
        return chatId;
    }

    public void setTimer(TimerTask tt){
        timeOfLast.schedule(tt,2400000);
    }

    public void cancelTimer(){
        timeOfLast.cancel();
        timeOfLast=new Timer();
    }

}
