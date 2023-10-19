
package simpleGUI;

import project.ProiectPOO;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;


public class simpleSwingGui{

    private static final String USERS_FILE = "users.csv";
    private static final String STREAMERS_FILE = "streamers.csv";
    private static final String STREAMS_FILE = "streams.csv";

    private static final String COMMANDS_FILE = "guiCommands.txt";


    public static void main(String args[]) {
        String pathTOCommands = "src/main/resources/gui/" + COMMANDS_FILE;
        cleanFile(pathTOCommands);

        //Creating the Frame
        JFrame frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Options");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);


        // Text Area at the Center
        GridBagLayout gridLayout = new GridBagLayout();
        GridBagConstraints labelConstraints = new GridBagConstraints();
        GridBagConstraints textConstraints = new GridBagConstraints();

        JPanel panelCenter = new JPanel(gridLayout);

        JLabel labelCenter = new JLabel("Commands to execute:");

        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.weightx = 0;
        labelConstraints.weighty = 0.01;
        labelConstraints.fill = GridBagConstraints.VERTICAL;
        labelConstraints.anchor = GridBagConstraints.NORTH;

        panelCenter.add(labelCenter, labelConstraints);

        JTextArea ta = new JTextArea();
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setEditable(false);

        textConstraints.gridx = 0;
        textConstraints.gridy = 1;
        textConstraints.weightx = 0.5;
        textConstraints.weighty = 0.5;
        textConstraints.fill = GridBagConstraints.BOTH;
        textConstraints.anchor = GridBagConstraints.NORTH;

        panelCenter.add(ta, textConstraints);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Commands");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        JButton add = new JButton("Add");
        add.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = tf.getText();

                writeToFile(COMMANDS_FILE, text);

                tf.setText("");

                ta.append(text + "\n");
            }
        });
        JButton delLast = new JButton("Delete last");
        delLast.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int lineCount = ta.getLineCount();
                if (lineCount > 0) {
                    try {
                        int start = ta.getLineStartOffset(lineCount - 2);
                        int end = ta.getLineEndOffset(lineCount - 2);
                        ta.replaceRange("", start, end);
                    } catch (BadLocationException badLocationException) {
                        badLocationException.printStackTrace();
                    }
                }

                try {
                    deleteLastLine(pathTOCommands);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        JButton delAll = new JButton("Delete all");
        delAll.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ta.setText("");
                cleanFile(pathTOCommands);
            }
        });

        JButton run = new JButton("Run");
        run.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputFolder = "inputs1/";
                String commandFolder = "gui/";
                ProiectPOO.main(getInputArgs(inputFolder, commandFolder));

                ta.setText("");
                cleanFile(pathTOCommands);
            }
        });

        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(add);
        panel.add(delLast);
        panel.add(delAll);
        panel.add(run);


        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, panelCenter);
        frame.setVisible(true);

    }

    private static void deleteLastLine(String pathToFile) throws IOException {
        // delete last line from file
        RandomAccessFile f = new RandomAccessFile(pathToFile, "rw");

        if (f.length() == 0) {
            return;
        }

        long length = f.length() - 1;
        byte b;
        do {
            length -= 1;
            f.seek(length);
            b = f.readByte();
        } while(b != 10 && length > 0);
        if (length == 0) {
            f.setLength(length);
        } else {
            f.setLength(length + 1);
        }
    }

    private static void cleanFile(String pathToFile) {
        try {
            PrintWriter writer = new PrintWriter(pathToFile);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile(String commandsFile, String text) {
        // write command to file

        File file = new File("src/main/resources/gui/" + commandsFile);

        // if file doesnt exists, then create it
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // write command to file
        try {
            FileWriter fw = new FileWriter(file, true);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(text + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static  String[] getInputArgs(String inputFolder, String testFolder) {
        return new String[]{inputFolder + STREAMERS_FILE,
                inputFolder + STREAMS_FILE,
                inputFolder + USERS_FILE,
                testFolder + COMMANDS_FILE};
    }
}