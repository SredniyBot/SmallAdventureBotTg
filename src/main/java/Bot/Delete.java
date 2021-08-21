package Bot;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.TimerTask;

public class Delete {

    Player p;

    Delete(Player p){
            this.p=p;
    }

    public void all(){
        p.cancelTimer();
            for(String s:new MessageControl(p).getMessages()) {
                DeleteMessage dm = new DeleteMessage();
                dm.setChatId(p.getChatId());
                dm.setMessageId(Integer.valueOf(s));
                try {
                    p.getBot().execute(dm);
                } catch (TelegramApiException e) {
                    System.out.println("Не получилось удалить сообщение №"+s+" в диалоге "+p.getChatId());
                }
            }
            new MessageControl(p).clear();
    }

    public synchronized void allAfter() {
        p.cancelTimer();
        TimerTask action = new TimerTask() {
            public void run() {
                all();
            }
        };
        p.setTimer(action);
    }

}
