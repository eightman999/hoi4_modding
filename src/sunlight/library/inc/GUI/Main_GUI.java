package sunlight.library.inc.GUI;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Main_GUI extends JFrame {
    public static void main(String[] args) {
        Main_GUI frame = new Main_GUI("Hoi4 modding tool");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        try {
            frame.setIconImage(ImageIO.read(new File("GUI/icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    Main_GUI (String title){
        setTitle(title);
        setBounds(100, 100, 600, 400);
        Panel p = new Panel();
        Button btn1 = new Button("Save");
        Button btn2 = new Button("Cancel");
        Button btn3 = new Button("Help");
        p.add(btn1);
        p.add(btn2);
        p.add(btn3);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
