package com.company;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;


public abstract class Main implements ActionListener{

// Initialise some GUI variables
JFrame main;
JPanel mainPanel, headerPanel, todoListPanel, newTaskPanel;
JEditorPane JTextArea;
JTextField inputTask, removeTask;
JButton enterTask, deleteTask;
JLabel header, instructions;
Border raisedBorder;
Font headerFont, mainFont;
Color deepBlue, blue;
String tasks;
    public static void  main(String[] args) {

////////////////////// CREATE A GUI FOR THE TO DO LIST APPLICATION /////////////////////////////////

        // Create all style variables here
        Color deepBlue = new Color(6,18,38);
        Color blue = new Color(4,113,166);
        Border raisedBorder = BorderFactory.createRaisedBevelBorder();
        Font headerFont = new Font("SansSerif",Font.BOLD, 32);
        Font mainFont = new Font("SansSerif", Font.BOLD, 14);

        // Create the JFrame object and add a main Panel to it
        JFrame main = new JFrame("To Do List");
        main.setBounds(100,100,600,800);

        JPanel mainPanel = new JPanel();
        main.add(mainPanel);
        mainPanel.setLayout(null);
        mainPanel.setBackground(deepBlue);

        // Create the header panel
        JPanel headerPanel = new JPanel();
        mainPanel.add(headerPanel);
        headerPanel.setBounds(20,20, 545,60);
        headerPanel.setBorder(raisedBorder);
        headerPanel.setBackground(blue);

        // Create and add the header label
        JLabel header = new JLabel("To Do List");
        header.setFont(headerFont);
        header.setForeground(Color.WHITE);
        headerPanel.add(header);

        // Create the panel for the todoList
        JPanel todoListPanel = new JPanel();
        mainPanel.add(todoListPanel);
        todoListPanel.setBorder(raisedBorder);
        todoListPanel.setBackground(blue);
        todoListPanel.setBounds(20,100,545,650);
        todoListPanel.setLayout(null);

        // Create a label with basic instructions for the user
        JLabel instructions = new JLabel("Create or remove options for your to do list!");
        instructions.setForeground(Color.WHITE);
        instructions.setFont(mainFont);
        instructions.setBounds(20,20,380,30);
        todoListPanel.add(instructions);

        // Create the 2 textFields
        JTextField inputTask = new JTextField("Input a task here");
        JTextField removeTask = new JTextField("Remove a task here");
        inputTask.setBounds(20,550,380,30);
        todoListPanel.add(inputTask);
        removeTask.setBounds(20,590,380,30);
        todoListPanel.add(removeTask);
        inputTask.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            return;
            }

            @Override
            public void keyPressed(KeyEvent e) {
            return;
            }

            @Override
            public void keyReleased(KeyEvent e) {
            return;
            }
        });


        // Create the 2 input/ delete buttons and add action listeners
        JButton enterTask = new JButton("Enter Task");
        JButton deleteTask = new JButton("Delete Task");
        enterTask.setBounds(410,550,130,30);
        deleteTask.setBounds(410,590,130,30);
        todoListPanel.add(deleteTask);
        todoListPanel.add(enterTask);



        // Create the new task panel
        JPanel newTaskPanel = new JPanel();
        newTaskPanel.setBounds(20,50,500,480);
        newTaskPanel.setBackground(deepBlue);
        newTaskPanel.setLayout(null);
        todoListPanel.add(newTaskPanel);

        //Create a JEditorPane
        JTextArea newTask = new JTextArea();
        newTask.setEditable(false);
        newTaskPanel.add(newTask);
        newTask.setBounds(0,0,500,480);
        newTask.setFont(mainFont);
        newTask.setBackground(deepBlue);
        newTask.setForeground(Color.WHITE);


        enterTask.addActionListener(new ActionListener() {
            //Create the array list variable
            ArrayList<String> tasks = new ArrayList<>();
            int index = 1;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()== enterTask){
                    tasks.add(enterTask.getText());
                    newTask.append("\n\n        " + index + ": " + tasks.get(tasks.size()-1));
                }
                index++;
            }
        });





        // Allow all features to load onto frame before setting it as visible
        main.setVisible(true);

    }

}

