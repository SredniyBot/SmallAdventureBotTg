package Bot;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MessageControl {

    private final boolean first = true;
    Player p;
    Delete del;
    private String URL = "";
    private String text;


    MessageControl(Player p) {
        this.p = p;
        del = new Delete(p);
        URL = this.getClass().getResource("/").getPath() + "Players/" + p.getChatId() + "/messages/log.txt";
    }


    public void setNewMessage(String mid) {
        text = getText();

        try (FileWriter fl = new FileWriter(URL)) {
            text = text + "[" + mid + "]\n";
            fl.write(text);
            fl.flush();
        } catch (IOException ignored) {

        }
    }

    public void clear() {
        text = getText();
        try {
            FileWriter fl = new FileWriter(URL);
            fl.write("");
            fl.flush();
        } catch (IOException e) {

        }
    }

    public ArrayList<String> getMessages() {
        text = getText();
        ArrayList<String> s = new ArrayList<>();
        String ts = text;
        while (ts.contains("[")) {
            s.add(ts.substring(ts.indexOf("[") + 1, ts.indexOf("]")));
            ts = ts.substring(ts.indexOf("]") + 1);
        }
        return s;
    }

    private String getText() {
        String t = "";
        FileReader tfr = null;
        try {
            tfr = new FileReader(URL);
            char[] buffer = new char[8096];
            int chars = tfr.read(buffer);
            while (chars != -1) {
                t = String.valueOf(buffer, 0, chars);
                chars = tfr.read(buffer);
            }
            tfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    public void deletePrev() {
        del.all();
    }

    public void deleteAfter() {
        del.allAfter();
    }
}
