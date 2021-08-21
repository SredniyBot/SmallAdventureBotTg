package Creation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class Panel extends JPanel {

        private String stagesText;

        private ArrayList<Stage> stages = new ArrayList<>();
        private int step=0;
        private int maxStep;

        private final JComboBox combo= new JComboBox();
        private JPanel panel;
        private TextPanel tp;
        private ImagePanel ip;
        private ChoicesPanel cp;

    Panel(){
        panel=new JPanel();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        try {
            stagesText=Program.getStoryText();
            System.out.println(Program.getStoryText());
        } catch (IOException e) {
            System.out.println("Error, no stages text");
        }

        getAllStages();
        createCombo();
        createBtnNew();
        add(panel);
        tp=new TextPanel(stages.get(step).getStage(step));
        ip=new ImagePanel(stages.get(step).getStage(step));
        cp=new ChoicesPanel(stages.get(step).getStage(step));
        add(tp);
        add(ip);
        add(cp);
    }

    private void createCombo(){
        combo.setEditable(true);
        for(int i=0;i<stages.size();i++){
            combo.addItem(i);
        }
        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                step=Integer.valueOf(String.valueOf(combo.getSelectedItem()));
                change(step);
            }
        });
        panel.add(combo);
    }

    private void createBtnNew(){
        JButton b1= new JButton("new");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maxStep++;
                step=maxStep;
                Stage s =new Stage().createStage(step);
                stages.add(s);
                combo.addItem(String.valueOf(step));
                combo.setSelectedItem(step);
            }
        });
        panel.add(b1);
    }

    public void change(int number){
        remove(tp);
        remove(ip);
        remove(cp);
        tp=new TextPanel(stages.get(number).getStage(number));
        ip=new ImagePanel(stages.get(number).getStage(number));
        cp=new ChoicesPanel(stages.get(number).getStage(number));
        add(tp);
        add(ip);
        add(cp);
        updateUI();
    }

    private void getAllStages(){
        int i=0;
        while(stagesText.contains("***"+i+"***")){
            Stage a = new Stage().getStage(i);
            stages.add(a);
            i++;
        }
        maxStep=i-1;
    }

}
