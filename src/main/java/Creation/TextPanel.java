package Creation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextPanel extends JPanel{

    private JTextArea area;
    private JButton b1;
    private Stage s;

    TextPanel(Stage s){
        this.s=s;
        createScrollArea();
        createBtnSaveText();
    }

    private void createScrollArea(){
        area = new JTextArea("",15, 60);
        JScrollPane scroll = new JScrollPane(area,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        area.setEnabled(true);
        if(s.isHasText()){
            area.setText(s.getText());
        }
        scroll.setEnabled(true);
        add(scroll);
    }

    private void createBtnSaveText(){
        b1=new JButton("save text");
        b1.setBackground(Color.RED);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s.setText(area.getText());
                b1.setBackground(Color.GRAY);
            }
        });
        add(b1);
    }

}
