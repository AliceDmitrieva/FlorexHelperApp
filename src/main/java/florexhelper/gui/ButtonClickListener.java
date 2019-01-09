package florexhelper.gui;

import florexhelper.DataFormatter;
import florexhelper.Launcher;
import florexhelper.fileutils.FileOutputCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ButtonClickListener implements ActionListener {

    private String buttonTitle;

    public ButtonClickListener(String buttonTitle) {
        this.buttonTitle = buttonTitle;
    }

    private File originalFile = new File("C:/Users/PC/Desktop/Florex/supplyfile.xlsx");
    private File resultFile = new File("C:/Users/PC/Desktop/Florex/supplyfile.xlsx");

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (buttonTitle) {
            case "ALBRA":
            case "EVERBLOOM":
                Launcher.start(buttonTitle, true);
                break;
            case "ALLEGRO 1":
            case "ALLEGRO 2":
            case "ANNIROSES":
            case "EDEN ROSES":
                Launcher.start(buttonTitle, false);
                break;
            case "OPEN ORIGINAL FILE":
                openFile(originalFile);
                break;
            case "OPEN RESULT FILE":
                openFile(new File(FileOutputCreator.fileName));
                break;
            case "ABOUT":
                openAboutWindow();
                break;
        }
    }

    public void openFile(File file) {
        try {
            Desktop.getDesktop().open(file);
            System.out.println("File " + file + " is opened");
        } catch (IOException e1) {
            System.out.println("File is not existed or can't be opened");
        }
    }

    public void openAboutWindow() {
        JFrame frame = new JFrame();
        frame.setVisible(true);
    }
}
