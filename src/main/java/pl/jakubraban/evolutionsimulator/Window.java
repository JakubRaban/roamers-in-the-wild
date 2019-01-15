package pl.jakubraban.evolutionsimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame implements KeyListener {

    private JTextArea outputField = new JTextArea();
    private JTextField inputField;

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

        inputField = new JTextField();
        inputField.setVisible(true);
        inputField.setBackground(Color.BLACK);
        inputField.setForeground(Color.white);
        inputField.setFont(new Font("Consolas", Font.PLAIN, 16));
        inputField.setCaretColor(Color.WHITE);
        inputField.setBorder(null);
        inputField.addKeyListener(this);
        add(inputField, BorderLayout.SOUTH);
    }

    public void setText(String t) {
        outputField.setText(t);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            String typed = inputField.getText();
            try {
                int number = Integer.parseInt(typed);
                World.setDelay(number);
            } catch(NumberFormatException nfe) {
                System.out.println("Zła liczba");
            }
        }
    }
}
