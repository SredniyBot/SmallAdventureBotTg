package Bot;

import Bot.Bot;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.ArrayList;


public class Send {

    private String chatId;
    private Bot bot;
    private Player player;
    private MessageControl control;
    Send(Player p){
        control =new MessageControl(p);
        control.getMessages();
        control.deletePrev();
        this.player=p;
        chatId=p.getChatId();
        bot=p.getBot();
    }

    public void text(String s){
        SendMessage sm = new SendMessage();
        sm.setChatId(chatId);
        sm.setText(s);
        try {
            Message m=bot.execute(sm);
            control.setNewMessage(String.valueOf(m.getMessageId()));

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void image(String path){
        SendPhoto s = new SendPhoto();
        s.setPhoto(new File(path));
        s.setChatId(chatId);
        SendMediaGroup snmg=new SendMediaGroup();
        InputMedia im=new InputMediaPhoto();
        im.setMedia(new File(path),path);
        ArrayList l = new ArrayList();
        l.add(im);
        snmg.setMedia(l);
        snmg.setChatId(chatId);
        try {
            //bot.execute(snmg);
            Message m=bot.execute(s);
            control.setNewMessage(String.valueOf(m.getMessageId()));

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void imageList(ArrayList<String> ImagePaths){
        ArrayList<InputMedia> ar = new ArrayList<>();
        SendMediaGroup mg = new SendMediaGroup();
        for(int i=0;i<ImagePaths.size();i++) {
            InputMedia ph1 = new InputMediaPhoto();
            ph1.setMedia(new File(ImagePaths.get(i)), String.valueOf(i));//ImagePaths.get(i).substring(ImagePaths.get(i).lastIndexOf("/"))+
            ar.add(ph1);
        }
        mg.setMedia(ar);
        mg.setChatId(chatId);
        try {
            bot.execute(mg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void choice(ArrayList<Object[]> cho){
        InlineKeyboardMarkup mark = new InlineKeyboardMarkup();
        ArrayList rows = new ArrayList<>();

            for(int i=0;i<cho.size();i++){
                ArrayList<InlineKeyboardButton> row1 = new ArrayList<>();
                row1.add(getBtn(cho.get(i)));
                rows.add(row1);
            }


        mark.setKeyboard(rows);
        SendMessage sm = new SendMessage();
        sm.setText("Ты выбираешь:");
        sm.setReplyMarkup(mark);
        sm.setChatId(chatId);
        try {
            Message m =bot.execute(sm);
            control.setNewMessage(String.valueOf(m.getMessageId()));

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private InlineKeyboardButton getBtn(Object[] o){
        InlineKeyboardButton b = new InlineKeyboardButton();
        b.setCallbackData(String.valueOf(o[1]));
        b.setText(String.valueOf(o[0]));
        return b;
    }

    public void delAfter(){
        control.deleteAfter();
    }

}
