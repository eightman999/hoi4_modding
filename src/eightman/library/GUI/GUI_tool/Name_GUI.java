package eightman.library.GUI.GUI_tool;

import eightman.library.GUI.Main_GUI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import static eightman.library.GUI.language.Title;

public class Name_GUI {

    public void name_GUI() {
        // This is a dummy method
    }

    public Name_GUI() {
        // This is a dummy method
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    new Main_GUI(Title).setVisible(true);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
