package Bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Bot extends TelegramLongPollingBot{

    private static PlayerCollection collection;
    Bot() {
        System.out.println("HORRAY2");

        collection = new PlayerCollection(this);

    }


    /**
     * Метод для приема сообщений.
     * @param update Содержит сообщение от пользователя.
     */

    @Override
    public void onUpdateReceived(Update update) {
        Player p =collection.getPlayer(update);
        CheckMessage ch = new CheckMessage(p,update);
    }

    @Override
    public String getBotUsername() {
        return "small_adventure_bot";
    }
    @Override
    public String getBotToken() {
        return "1016755744:AAG8EegXDyHfSUQfUJzihbd_GRrTLQPFS2M";
    }

}
