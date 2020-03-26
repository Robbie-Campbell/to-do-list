package com.ToDoList;

/*
Date: 25/03/2020
Author: Robbie Campbell

Description: A program which serves as a to do list where the user can enter and remove items from the list. The current
version does not use onClickListeners but that functionality will be coming soon, along with the ability to save the
current state of the list, and remove them from system memory as opposed to this applications temporary memory. Also
error handling will be implemented.

 */

import java.io.*;
import java.util.ArrayList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;


public class Main{

    // Create a function which is able to translate sound files into audio clips
    private static void PlaySound(File Sound){
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();

            Thread.sleep(clip.getMicrosecondLength()/1000);
        }catch (Exception e){
            System.out.println("Not a valid audio track");
        }
    }


    private ArrayList<String> tasks = new ArrayList<>();
    private int index;
    private String fileName = "todolist.sav";

// Create functions which saves and reads todos
    private void readFile() {

        //Where to save data


        // Start the method by creating a read object that's checks if there are any values in the file.
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));

            // Reads array list values from the file.
            ArrayList values = (ArrayList) is.readObject();

            // Always check the file before running the program to add saved data to the list

            if (tasks.size() == 0) {
                for (Object value : values) {
                    tasks.add(String.valueOf(value));
                }
            }


            // Close the file
            is.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
                private void saveFile () {
                // Write a new object into the file (This is done by the array increasing in size and the file being
                // overwritten)
                try {
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
                    os.writeObject(tasks);
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Check the function has been run.
                System.out.println("File saved");
            }


    private Main(){

        // A list of stale meme audioclips that play when the action listeners are used
        File greatJob = new File("C:\\Users\\robbi\\IdeaProjects\\Collections_framework\\src\\com\\ToDoList\\greatJob.wav");
        File noice = new File("C:\\Users\\robbi\\IdeaProjects\\Collections_framework\\src\\com\\ToDoList\\-click-nice_3.wav");
        File okay = new File("C:\\Users\\robbi\\IdeaProjects\\Collections_framework\\src\\com\\ToDoList\\my-song-2_2.wav");
////////////////////// CREATE A GUI FOR THE TO DO LIST APPLICATION /////////////////////////////////

        // Initialise these 2 variables to be added to within action listeners.
        index = 1;

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

        // Create the new task panel
        JPanel newTaskPanel = new JPanel();
        newTaskPanel.setBounds(20,50,500,480);
        newTaskPanel.setBackground(deepBlue);
        newTaskPanel.setLayout(null);
        todoListPanel.add(newTaskPanel);

        //Create a JTextArea
        JTextArea newTask = new JTextArea();
        newTask.setEditable(false);
        newTaskPanel.add(newTask);
        newTask.setBounds(0,0,500,480);
        newTask.setFont(mainFont);
        newTask.setBackground(deepBlue);
        newTask.setForeground(Color.WHITE);

        // Add the previously saved values to the to do list on load
        readFile();
        for (String task : tasks) {
            index++;
            newTask.append("\n\n        " + (index - 1) + ": " + task);
        }

        // Create the 2 textFields
        JTextField inputTask = new JTextField("Input a task here");
        JTextField removeTask = new JTextField("Remove a task here by entering the index value");
        inputTask.setBounds(20,550,380,30);
        todoListPanel.add(inputTask);
        removeTask.setBounds(20,590,380,30);
        todoListPanel.add(removeTask);

        // Create a key listener to remove to do's from the list
        removeTask.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //None
            }

            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    int key = e.getKeyCode();
                    if(key == KeyEvent.VK_ENTER) {
                        int remover = Integer.parseInt(removeTask.getText());
                        newTask.setText("");
                        tasks.remove(remover - 1);
                        index = 1;
                        PlaySound(noice);
                        // This loop displays an index value and any values stored in the ArrayList
                        for (String task : tasks) {
                            index++;
                            newTask.append("\n\n        " + (index - 1) + ": " + task);
                        }
                        saveFile();

                    }
                }catch(Exception z){
                    removeTask.setText("Please enter a valid input");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //None
            }
        });

        // Create a key listener to append to do's to the list
        inputTask.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //None
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if(key == KeyEvent.VK_ENTER){
                    tasks.add(inputTask.getText());
                    newTask.append("\n\n        " + index + ": " + tasks.get(tasks.size()-1));
                    index++;
                    inputTask.setText("");
                    saveFile();
                    PlaySound(okay);
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
            //none
            }
        });

        // Create the 3 buttons and add action listeners
        JButton enterTask = new JButton("Enter Task");
        JButton deleteTask = new JButton("Remove Task");
        enterTask.setBounds(400,550,130,30);
        deleteTask.setBounds(400,590,130,30);
        todoListPanel.add(deleteTask);
        todoListPanel.add(enterTask);

        // Create an button that clears todos
        JButton clearTodos = new JButton("Clear todos");
        clearTodos.setBounds(400,10,130,30);
        todoListPanel.add(clearTodos);

        // Implement an action listener
        clearTodos.addActionListener(e ->{
            if (e.getSource() == clearTodos) {
                index = 1;
                newTask.setText("");
                tasks.clear();
                saveFile();
                PlaySound(greatJob);
                }
        });


        // When the button is pressed a new to do will be appended to the list
        enterTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()== enterTask){
                    tasks.add(inputTask.getText());
                    newTask.append("\n\n        " + index + ": " + tasks.get(tasks.size()-1));
                    inputTask.setText("");
                    saveFile();
                    PlaySound(okay);
                }
                index++;
            }
        });

        // Removes the list item based on an integer value passed into the text field
        deleteTask.addActionListener(e -> {
            try {
                    int remover = Integer.parseInt(removeTask.getText());
                    newTask.setText("");
                    tasks.remove(remover - 1);
                    index = 1;
                    PlaySound(noice);
                    // This loop displays an index value and any values stored in the ArrayList
                    for (String task : tasks) {
                        index++;
                        newTask.append("\n\n        " + (index - 1) + ": " + task);
                    }
                saveFile();
                }catch(Exception z){
                removeTask.setText("Please enter a valid input");

            }
        });

        // Allow all features to load onto frame before setting it as visible
        main.setVisible(true);
    }

    // Runs the program
    public static void main(String[] args){
        Main run;
        run = new Main();
    }

}