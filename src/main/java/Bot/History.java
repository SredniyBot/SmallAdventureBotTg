package Bot;

public class History {

    private int step=0;
    Player p;

    History(Player p, int step){
        this.p=p;
        this.step=step;
    }

    public void sendS(){
        new SendStage(p,new Story().getStage(step));
    }

}
