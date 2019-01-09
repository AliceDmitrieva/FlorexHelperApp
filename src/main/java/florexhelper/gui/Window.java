package florexhelper.gui;

import com.sun.deploy.uitoolkit.impl.fx.ui.FXMessageDialog;
import sun.plugin2.message.Message;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Window {

    public static void showWindow() {
        ImageIcon icon = new ImageIcon("./flower-icon.png");
        Font font = new Font("Calibri", Font.BOLD, 26);
        JFrame frame = new JFrame();

        JPanel basePanel = new JPanel(new BorderLayout());
        basePanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel buttonCenter = new JPanel(new GridBagLayout());
        buttonCenter.setBorder(new EmptyBorder(40, 40, 40, 40));
        buttonCenter.setBackground(Color.getHSBColor(0.4f, 0.45f, 0.7f));

        JPanel buttonPanel = new JPanel(new GridLayout(0, 2, 15, 20));

        SwingUtilities.invokeLater(() -> {
            frame.setSize(900, 700);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Florex Helper");
            frame.setIconImage(icon.getImage());
            basePanel.setBackground(Color.getHSBColor(0.5f, 0.6f, 0.7f));
            frame.setResizable(false);

            JButton openOriginalFileButton = new JButton();
            String openOriginalButtonText = "OPEN ORIGINAL FILE";
            openOriginalFileButton.setText(openOriginalButtonText);
            openOriginalFileButton.setPreferredSize(new Dimension(300, 50));
            openOriginalFileButton.setFont(font);
            openOriginalFileButton.setFocusPainted(false);
            openOriginalFileButton.setBackground(Color.WHITE);
            openOriginalFileButton.addActionListener(new ButtonClickListener(openOriginalButtonText));

            JButton openResultFileButton = new JButton();
            String openResultButtonText = "OPEN RESULT FILE";
            openResultFileButton.setText(openResultButtonText);
            openResultFileButton.setPreferredSize(new Dimension(300, 50));
            openResultFileButton.setFont(font);
            openResultFileButton.setFocusPainted(false);
            openResultFileButton.setBackground(Color.WHITE);
            openResultFileButton.addActionListener(new ButtonClickListener(openResultButtonText));

            JButton albraButton = new JButton();
            String albraButtonText = "ALBRA";
            albraButton.setText(albraButtonText);
            albraButton.setPreferredSize(new Dimension(200, 50));
            albraButton.setFont(font);
            albraButton.setFocusPainted(false);
            albraButton.setBackground(Color.WHITE);
            albraButton.addActionListener(new ButtonClickListener(albraButtonText));

            JButton allegro1Button = new JButton();
            String allegro1ButtonText = "ALLEGRO 1";
            allegro1Button.setText(allegro1ButtonText);
            allegro1Button.setPreferredSize(new Dimension(200, 50));
            allegro1Button.setFont(font);
            allegro1Button.setFocusPainted(false);
            allegro1Button.setBackground(Color.WHITE);
            allegro1Button.addActionListener(new ButtonClickListener(allegro1ButtonText));

            JButton allegro2Button = new JButton();
            String allegro2ButtonText = "ALLEGRO 2";
            allegro2Button.setText(allegro2ButtonText);
            allegro2Button.setPreferredSize(new Dimension(200, 50));
            allegro2Button.setFont(font);
            allegro2Button.setFocusPainted(false);
            allegro2Button.setBackground(Color.WHITE);
            allegro2Button.addActionListener(new ButtonClickListener(allegro2ButtonText));

            JButton annirosesButton = new JButton();
            String annirosesButtonText = "ANNIROSES";
            annirosesButton.setText(annirosesButtonText);
            annirosesButton.setPreferredSize(new Dimension(200, 50));
            annirosesButton.setFont(font);
            annirosesButton.setFocusPainted(false);
            annirosesButton.setBackground(Color.WHITE);
            annirosesButton.addActionListener(new ButtonClickListener(annirosesButtonText));

            JButton edenButton = new JButton();
            String edenButtonText = "EDEN ROSES";
            edenButton.setText(edenButtonText);
            edenButton.setPreferredSize(new Dimension(200, 50));
            edenButton.setFont(font);
            edenButton.setFocusPainted(false);
            edenButton.setBackground(Color.WHITE);
            edenButton.addActionListener(new ButtonClickListener(edenButtonText));

            JButton everbloomButton = new JButton();
            String everbloomButtonText = "EVERBLOOM";
            everbloomButton.setText(everbloomButtonText);
            everbloomButton.setPreferredSize(new Dimension(200, 50));
            everbloomButton.setFont(font);
            everbloomButton.setFocusPainted(false);
            everbloomButton.setBackground(Color.WHITE);
            everbloomButton.addActionListener(new ButtonClickListener(everbloomButtonText));

            JButton instructionButton = new JButton();
            String instructionButtonText = "INSTRUCTION";
            instructionButton.setText(instructionButtonText);
            instructionButton.setPreferredSize(new Dimension(200, 50));
            instructionButton.setFont(font);
            instructionButton.setFocusPainted(false);
            instructionButton.setBackground(Color.WHITE);
            instructionButton.addActionListener(new ButtonClickListener(instructionButtonText));

            JButton aboutButton = new JButton();
            String aboutButtonText = "ABOUT";
            aboutButton.setText(aboutButtonText);
            aboutButton.setPreferredSize(new Dimension(200, 50));
            aboutButton.setFont(font);
            aboutButton.setFocusPainted(false);
            aboutButton.setBackground(Color.WHITE);
            aboutButton.addActionListener(new ButtonClickListener(aboutButtonText));

            buttonPanel.add(allegro1Button);
            buttonPanel.add(allegro2Button);
            buttonPanel.add(albraButton);
            buttonPanel.add(annirosesButton);
            buttonPanel.add(edenButton);
            buttonPanel.add(everbloomButton);
            buttonPanel.add(openOriginalFileButton);
            buttonPanel.add(openResultFileButton);
            buttonPanel.add(instructionButton);
            buttonPanel.add(aboutButton);
            buttonPanel.setBackground(Color.getHSBColor(0.4f, 0.45f, 0.7f));

            buttonCenter.add(buttonPanel);
            basePanel.add(buttonCenter);
            JScrollPane scrollBar = new JScrollPane(basePanel,
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            frame.add(scrollBar);

            frame.setVisible(true);
        });
    }

    public static void showPopUpWindow() {
        JOptionPane.showMessageDialog(null, "Файл готов!\n Алиса умочка :)");
    }
}
