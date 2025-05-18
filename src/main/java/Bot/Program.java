package Bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;

public class Program {

    public final static String split = "/";
    private static String stagesText = "";
    private static String Url = "";

    public static void main(String[] args) throws TelegramApiException {
        File f = new File(Program.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        Url = f.getPath().replace("SmallAdventureBotTg.jar", "");
        Url = Url.replace("SmallAdventureBotTg\\target\\classes", "");
        System.out.println(Url);
        start();
    }

    public static void start() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public static String getMainPath() {
        return Url;
    }

}
