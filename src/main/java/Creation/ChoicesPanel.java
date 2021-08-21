package Creation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ChoicesPanel extends JPanel {

    Stage s;
    JPanel pan = new JPanel();

    ChoicesPanel(Stage s){
        setLayout(new FlowLayout(FlowLayout.CENTER));

        this.s=s;
        createChangesBtn();
    }

    private void createChangesBtn(){

        if(s.isHasChoice()) {
            for(int i=0;i<s.getChoices().size();i++) {
                ChoicePanel b = new ChoicePanel(s, i);
                pan.add(b);
            }
        }
        add(pan);
        createAddBtn();
    }

    private void createAddBtn(){
        JButton b = new JButton("add choice");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] o = new Object[2];
                o[0] = "";
                o[1] = 0;
                s.addChoice(o);
                ChoicePanel n = new ChoicePanel(s, s.choices.size()-1);
                pan.add(n);
                pan.updateUI();
                updateUI();
            }
        });


        add(b);
    }

}
