package Creation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoicePanel extends JPanel{

    private Stage s;
    private String text="";
    private int number,n;
    private JButton approveBtn;
    private JButton delBtn;
    private JTextArea textArea;
    private JTextArea numberArea;
    private Object[] ch;

    ChoicePanel(Stage s,int n){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setEnabled(false);
        this.n=n;
        this.s=s;
        ch= s.getChoices().get(n);
        this.text=String.valueOf(ch[0]);
        this.number=(Integer) ch[1];

        createTextArea();
        createNumberArea();
        createApproveBtn();
        createDelBtn();
    }

    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(0,0,getWidth()-1,getHeight()-1);

    }

    private void createTextArea(){
        textArea = new JTextArea(text,3, 10);
        JScrollPane scroll = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        textArea.setEnabled(true);
        scroll.setEnabled(true);
        add(scroll);
    }

    private void createNumberArea(){
        numberArea = new JTextArea(String.valueOf(number),3, 10);
        JScrollPane scroll = new JScrollPane(numberArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        numberArea.setEnabled(true);
        scroll.setEnabled(true);
        add(scroll);
    }

    private void createApproveBtn(){
        approveBtn=new JButton("change choice");
        approveBtn.setBackground(Color.RED);
        approveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s.delChoice(ch);
                ch[0]=textArea.getText();
                ch[1]=Integer.valueOf(numberArea.getText());
                s.addChoice(ch);
                approveBtn.setBackground(Color.GRAY);
            }
        });
        add(approveBtn);
    }

    private void createDelBtn(){
        delBtn=new JButton("delete choice");
        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s.delChoice(ch);
                removeAll();
                repaint();
                revalidate();
            }
        });
        add(delBtn);
    }

}
