package Bot;

import org.telegram.telegrambots.meta.api.objects.Update;

public class CheckMessage {

    private String chatId,message;
    private Player p;

    public CheckMessage(Player p, Update update){
        this.p=p;
        this.chatId=p.getChatId();
        if(update.hasCallbackQuery()) {
            ButtonPressed(update.getCallbackQuery().getData());
        } else {
            this.message = update.getMessage().getText();
            analiseMsg(message);
        }
    }

    public void analiseMsg(String message){
        if(p.getData().getWaitingStatus()){
            System.out.println(message);
            p.getData().setWaitingStatus(false);
        }
        switch (message){
            case("/createPlanet"):
                //p.getData().setWaitingStatus(true);
                break;
            case("/MyPlanets"):

                break;
            default:
                new SendStage(p,new Story().getStage(0));
                break;
        }
    }

    private void ButtonPressed(String s){
        History his=new History(p,Integer.valueOf(s));
        his.sendS();
    }

}
