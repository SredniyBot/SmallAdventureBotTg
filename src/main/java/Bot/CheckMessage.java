package Bot;

import org.telegram.telegrambots.meta.api.objects.Update;

public class CheckMessage {

    private final Player p;

    public CheckMessage(Player p, Update update){
        this.p=p;
        if(update.hasCallbackQuery()) {
            ButtonPressed(update.getCallbackQuery().getData());
        } else {
            String message = update.getMessage().getText();
            analiseMsg(message);
        }
    }

    public void analiseMsg(String message){
        if(p.getData().getWaitingStatus()){
            p.getData().setWaitingStatus(false);
        }
        System.out.println(message);
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
        History his=new History(p,Integer.parseInt(s));
        his.sendS();
    }

}
