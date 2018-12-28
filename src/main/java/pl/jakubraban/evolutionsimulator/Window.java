package pl.jakubraban.evolutionsimulator;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private JTextArea outputField = new JTextArea();

    public Window() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1200, 800));
        setTitle("Where Is My Judgment");
        setVisible(true);
        JScrollPane scrollPane = new JScrollPane(outputField);
        scrollPane.setSize(1200, 800);
        //scrollPane.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.WHITE));
        outputField.setBackground(Color.BLACK);
        outputField.setForeground(Color.WHITE);
        outputField.setEditable(false);
        outputField.setFont(new Font("Consolas", Font.PLAIN, 14));
        add(scrollPane, BorderLayout.CENTER);
    }

    public void setText(String t) {
        outputField.setText(t);
    }

}
