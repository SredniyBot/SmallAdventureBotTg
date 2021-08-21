package Bot;

public class SendStage {

    Send send;
    Stage s;

    SendStage(Player p, Stage s){
        send = new Send(p);
        this.s=s;
        st();
    }

    private void st(){
        if(s.isHasText()){
            send.text(s.getText());
        }
        if(s.isHasImage()){
            send.image(s.getImage());
        }
        if(s.isHasChoice()){
            send.choice(s.getChoices());
        }
        send.delAfter();

    }

}
