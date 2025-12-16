import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

// to display time 
import java.awt.event.*;
import java.text.*;
import java.util.Date;

// To play sound, audio files & throw exceptions
import java.io.*; // file & getAbsolutePath
import java.io.IOException;

import javax.sound.sampled.*;
// import java.net.URL;
import java.util.Scanner;

public class Main {
    private static final String IMG_PATH = "src/main/images/image01.jpg";

    //////////////////////////////////////////////////////////////////////////////
    //                                                                          //
    //                      Implements sound settings                           //
    //                                                                          //
    //////////////////////////////////////////////////////////////////////////////
    private static void SoundSettings() {
        JFrame jFrame_soundSettings = new JFrame("Sound Settings");
        jFrame_soundSettings.setLayout(new FlowLayout());

        jFrame_soundSettings.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame_soundSettings.setUndecorated(true);
        jFrame_soundSettings.setVisible(true);
        jFrame_soundSettings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Label for the frame
        JPanel left_panel = new JPanel();
        left_panel.setSize(new Dimension(250, 300));
        JLabel label = new JLabel("Sound Settings");
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        label.setBorder(border);

        // Vertical panel for sound settings
        JPanel Settings_panel = new JPanel();
        Settings_panel.setSize(200, 300);
        Settings_panel.setLayout(new GridLayout(6, 1, 10, 10));
        // For a display fully vertical, might work well if put in a pop-up window (?)
        // Settings_panel.setLayout(new BoxLayout(Settings_panel, BoxLayout.Y_AXIS));

        // Content of the Settings_panel
        JLabel BGM_label = new JLabel("Background Music");
        BGM_label.setSize(75, 120);
        BGM_label.add(Box.createVerticalGlue());
        JSlider BGM_Slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
        // event listener so the slider can be used
        /*
         * BGM_Slider.addChangeListener(new ChangeListener() {
         * 
         * @Override
         * public void stateChanged(ChangeEvent e) {
         * sound.currentVolume = BGM_Slider.getValue();
         * sound.fc.setValue(sound.currentVolume);
         * }
         * });
         */
        BGM_Slider.setBounds(50, 100, 200, 50);
        BGM_Slider.add(Box.createVerticalGlue());
        JLabel Effects_label = new JLabel("Voice // Sound effects");
        Effects_label.setSize(75, 120);
        Effects_label.add(Box.createVerticalGlue());
        JSlider Effects_Slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
        Effects_Slider.setBounds(150, 200, 200, 50);
        Effects_Slider.add(Box.createVerticalGlue());

        // Box with a grid layout to display object veretically
        GridBagConstraints gbc = new GridBagConstraints();
        // top padding of 10px
        gbc.insets = new Insets(10, 0, 0, 0);

        gbc.gridx = 0;
        gbc.gridy = 0;
        Settings_panel.add(BGM_label, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        Settings_panel.add(BGM_Slider, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        Settings_panel.add(Effects_label, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        Settings_panel.add(Effects_Slider, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        Settings_panel.add(Effects_Slider, gbc);

        gbc.gridx = 0;
        gbc.gridy++;

        // Button to reset the Slider values
        JButton sound_reset = new JButton("Reset");
        sound_reset.setSize(175, 125);

        // event listener
        sound_reset.addMouseListener(new MouseAdapter() {
            // @Override
            public void mouseClicked(MouseEvent e) {
                BGM_Slider.setValue(100);
                Effects_Slider.setValue(100);

            }
        });

        // Button to go back to the main menu
        JButton go_back = new JButton("Go back to main menu");
        go_back.setSize(175, 125);

        // event listener
        go_back.addMouseListener(new MouseAdapter() {
            // @Override
            public void mouseClicked(MouseEvent e) {
                jFrame_soundSettings.setVisible(false);
                jFrame_soundSettings.dispose();
                Main_menu();

            }
        });

        left_panel.add(label);
        jFrame_soundSettings.add(left_panel);
        jFrame_soundSettings.add(Settings_panel, BorderLayout.CENTER);
        Settings_panel.add(sound_reset, gbc);
        Settings_panel.add(go_back, gbc);
        jFrame_soundSettings.pack();
    }


    //////////////////////////////////////////////////////////////////////////////
    //                                                                          //
    //                      Implements the starting screen                      //
    //                                                                          //
    //////////////////////////////////////////////////////////////////////////////
    private static void start_screen() {

        JFrame jFrame_0 = new JFrame("Start screen");
        jFrame_0.setLayout(new FlowLayout());
        jFrame_0.setSize(530, 550);
        jFrame_0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // a text box that's used as a button that leads to the main menu
        
        JLabel label = new JLabel("<html>Starting screen<br/><br/> >>> Click to go to the main menu</html>");
        Border border = BorderFactory.createLineBorder(Color.BLACK, 0);
        label.setBorder(border);
        label.setPreferredSize(new Dimension(510, 450));
        // label.setSize(500, 300); // => le résultat est différent que celui du label
        // dans menu_1()
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jFrame_0.setVisible(false);
                jFrame_0.dispose();
                Main_menu();
            }
        });

        jFrame_0.add(label);
        jFrame_0.setVisible(true);
    }

    
    // Implements a jFrame to start the game
    private static void load_game() {
        JFrame menu_1 = new JFrame("Start game");
        menu_1.setLayout(new FlowLayout());
        menu_1.setExtendedState(JFrame.MAXIMIZED_BOTH);
        menu_1.setUndecorated(true);
        menu_1.setVisible(true);

        JLabel label = new JLabel("Menu to start a new game");
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        label.setBorder(border);
        label.setPreferredSize(new Dimension(150, 100));

        // Sets the layout of elements in the frame
        menu_1.setLayout(new GridLayout(1, 1, 20, 20));

        // Setting a label
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setSize(250, 150);

        // n buttons
        JButton button1 = new JButton("Slot 1");
        JButton button2 = new JButton("Slot 2");
        JButton button3 = new JButton("Slot 3");
        JButton button4 = new JButton("Slot 4");
        JButton button5 = new JButton("Slot 5");
        JButton button6 = new JButton("Go back");
        button1.setSize(175, 125);
        button2.setSize(175, 125);
        button3.setSize(175, 125);
        button4.setSize(175, 125);
        button5.setSize(175, 125);
        button6.setSize(175, 125);

        // event on click
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu_1.setVisible(false);
                menu_1.dispose();
                Main_menu();
            }
        });

        // Pannel for sub-menu
        JPanel sub_menu = new JPanel();
        menu_1.add(sub_menu, BorderLayout.CENTER);
        sub_menu.setLayout(new GridLayout(6, 1, 10, 10));

        sub_menu.setSize(200, 150);
        menu_1.add(label);
        sub_menu.add(button1);
        sub_menu.add(button2);
        sub_menu.add(button3);
        sub_menu.add(button4);
        sub_menu.add(button5);
        sub_menu.add(button6);

        menu_1.setVisible(true);

    }

    public static void copy_data(){
        JFrame menu = new JFrame("Copy data");
        menu.setLayout(new FlowLayout());
        menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
        menu.setUndecorated(true);
        menu.setVisible(true);

        JLabel label = new JLabel("Select the slot you want to copy");
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        label.setBorder(border);
        label.setPreferredSize(new Dimension(150, 100));

        // Sets the layout of elements in the frame
        menu.setLayout(new GridLayout(1, 1, 20, 20));

        // Setting a label
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setSize(250, 150);

        // n buttons
        JButton button1 = new JButton("Slot 1");
        JButton button2 = new JButton("Slot 2");
        JButton button3 = new JButton("Slot 3");
        JButton button4 = new JButton("Slot 4");
        JButton button5 = new JButton("Slot 5");
        JButton button6 = new JButton("Go back");
        button1.setSize(175, 125);
        button2.setSize(175, 125);
        button3.setSize(175, 125);
        button4.setSize(175, 125);
        button5.setSize(175, 125);
        button6.setSize(175, 125);

        // event on click
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Chose the slot you want to copy data to");
                /*
                button.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        // pseudo code de la forme:
                        // first-slot-chosen.data = second-slot-chosen.data;
                    }
                })
                */
            }
        });

        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                menu.dispose();
                Main_menu();
            }
        });

        // Pannel for sub-menu
        JPanel sub_menu = new JPanel();
        menu.add(sub_menu, BorderLayout.CENTER);
        sub_menu.setLayout(new GridLayout(6, 1, 10, 10));

        sub_menu.setSize(200, 150);
        menu.add(label);
        sub_menu.add(button1);
        sub_menu.add(button2);
        sub_menu.add(button3);
        sub_menu.add(button4);
        sub_menu.add(button5);
        sub_menu.add(button6);

        menu.setVisible(true);
    }

    // Menu to modify data slot
    private static void modify_data() {

        JFrame modification_choice = new JFrame("Modify data game");
        modification_choice.setLayout(new FlowLayout());
        modification_choice.setExtendedState(JFrame.MAXIMIZED_BOTH);
        modification_choice.setUndecorated(true);
        modification_choice.setVisible(true);

        JLabel pre_frame_label = new JLabel("<html>Select the modification <br></br> you want to make</html>");
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        pre_frame_label.setBorder(border);
        pre_frame_label.setPreferredSize(new Dimension(150, 100));

        // Sets the layout of elements in the frame
        modification_choice.setLayout(new GridLayout(2, 3, 20, 20));

        // Setting a label
        pre_frame_label.setHorizontalAlignment(JLabel.CENTER);
        pre_frame_label.setVerticalAlignment(JLabel.CENTER);
        pre_frame_label.setSize(250, 150);

        // n buttons
        JButton button1 = new JButton("Copy data");
        JButton button2 = new JButton("Modify name");
        JButton button3 = new JButton("Go back");
        button1.setSize(175, 125);
        button2.setSize(175, 125);
        button3.setSize(175, 125);

        // event on click
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modification_choice.setVisible(false);
                modification_choice.dispose();
                copy_data();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modification_choice.setVisible(false);
                modification_choice.dispose();
                modify_name();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modification_choice.setVisible(false);
                modification_choice.dispose();
                modify_data();
            }
        });
        
        // Pannel for sub-menu
        JPanel sub_menu = new JPanel();
        modification_choice.add(sub_menu, BorderLayout.CENTER);
        sub_menu.setLayout(new GridLayout(1, 2, 10, 10));

        sub_menu.setSize(200, 150);
        modification_choice.add(pre_frame_label);
        sub_menu.add(button1);
        sub_menu.add(button2);
        sub_menu.add(button3);

        modification_choice.setVisible(true);
    }

    

    private static void modify_name() {
        JFrame menu = new JFrame("Modify name");
        menu.setLayout(new FlowLayout());
        menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
        menu.setUndecorated(true);
        menu.setVisible(true);

        JLabel label = new JLabel("Select the slot you want to modify");
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        label.setBorder(border);
        label.setPreferredSize(new Dimension(150, 100));

        // Sets the layout of elements in the frame
        menu.setLayout(new GridLayout(1, 1, 20, 20));

        // Setting a label
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setSize(250, 150);

        // n buttons
        JButton button1 = new JButton("Slot 1");
        JButton button2 = new JButton("Slot 2");
        JButton button3 = new JButton("Slot 3");
        JButton button4 = new JButton("Slot 4");
        JButton button5 = new JButton("Slot 5");
        JButton button6 = new JButton("Go back");
        button1.setSize(175, 125);
        button2.setSize(175, 125);
        button3.setSize(175, 125);
        button4.setSize(175, 125);
        button5.setSize(175, 125);
        button6.setSize(175, 125);

        // event on click
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // PlayerData.get_userName();
            }
        });

        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                menu.dispose();
                Main_menu();
            }
        });

        // Pannel for sub-menu
        JPanel sub_menu = new JPanel();
        menu.add(sub_menu, BorderLayout.CENTER);
        sub_menu.setLayout(new GridLayout(6, 1, 10, 10));

        sub_menu.setSize(200, 150);
        menu.add(label);
        sub_menu.add(button1);
        sub_menu.add(button2);
        sub_menu.add(button3);
        sub_menu.add(button4);
        sub_menu.add(button5);
        sub_menu.add(button6);

        menu.setVisible(true);
    }

    // Menu to delete data slot
    private static void delete_data() {

        JFrame modification_choice = new JFrame("Delete game slot");
        modification_choice.setLayout(new FlowLayout());
        modification_choice.setExtendedState(JFrame.MAXIMIZED_BOTH);
        modification_choice.setUndecorated(true);
        modification_choice.setVisible(true);

        JLabel pre_frame_label = new JLabel("<html>Select the game slot <br></br> you want to delete</html>");
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        pre_frame_label.setBorder(border);
        pre_frame_label.setPreferredSize(new Dimension(150, 100));

        // Sets the layout of elements in the frame
        modification_choice.setLayout(new GridLayout(2, 3, 20, 20));

        // Setting a label
        pre_frame_label.setHorizontalAlignment(JLabel.CENTER);
        pre_frame_label.setVerticalAlignment(JLabel.CENTER);
        pre_frame_label.setSize(250, 150);

        // n buttons
        JButton button1 = new JButton("Copy data");
        JButton button2 = new JButton("Modify name");
        JButton button3 = new JButton("Go back");
        button1.setSize(175, 125);
        button2.setSize(175, 125);
        button3.setSize(175, 125);

        // event on click
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modification_choice.setVisible(false);
                modification_choice.dispose();
                copy_data();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modification_choice.setVisible(false);
                modification_choice.dispose();
                modify_name();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modification_choice.setVisible(false);
                modification_choice.dispose();
                modify_data();
            }
        });

        // Pannel for sub-menu
        JPanel sub_menu = new JPanel();
        modification_choice.add(sub_menu, BorderLayout.CENTER);
        sub_menu.setLayout(new GridLayout(1, 2, 10, 10));

        sub_menu.setSize(200, 150);
        modification_choice.add(pre_frame_label);
        sub_menu.add(button1);
        sub_menu.add(button2);
        sub_menu.add(button3);

        modification_choice.setVisible(true);
    }

    // Frame for the main menu
    private static void Main_menu() {
        JFrame main_menu = new JFrame("Main menu");
        // main_menu.setLayout(new FlowLayout());
        main_menu.setLayout(new GridLayout(1, 1, 20, 20));
        // jFrame.setSize(500, 360);
        main_menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        main_menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
        main_menu.setUndecorated(true);
        main_menu.setVisible(true);

        JLabel label = new JLabel("");
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        label.setBorder(border);
        label.setPreferredSize(new Dimension(150, 100));

        // a text box
        label.setText("<html>Hello World<br/><br/> Swing</html>");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        // 4 buttons implementation
        JButton button1 = new JButton("Start game");
        JButton button2 = new JButton("Modify data");
        JButton button3 = new JButton("Delete data");
        JButton button4 = new JButton("Sound Settings");
        button1.setSize(175, 125);
        button2.setSize(175, 125);
        button3.setSize(175, 125);
        button4.setSize(175, 125);

        // Pannel for main menu and title
        JPanel menu_title = new JPanel();
        menu_title.add(label, BorderLayout.CENTER);

        JPanel main_panel = new JPanel();
        main_panel.setLayout(new BoxLayout(menu_title, BoxLayout.X_AXIS));

        main_panel.setLayout(new GridLayout(6, 1, 10, 10));

        // event on cick
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main_menu.setVisible(false);
                main_menu.dispose();
                load_game();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main_menu.setVisible(false);
                main_menu.dispose();
                modify_data();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                 * main_menu.setVisible(false);
                 * main_menu.dispose();
                 * menu_3();
                 */
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main_menu.setVisible(false);
                main_menu.dispose();
                SoundSettings();
            }
        });

        JButton go_back = new JButton("Go back to starting screen");
        go_back.setSize(175, 125);
        go_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main_menu.setVisible(false);
                main_menu.dispose();
                start_screen();
            }
        });

        // Label that displays the time
        final JLabel timeLabel = new JLabel();
        final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        ActionListener timerListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                String time = timeFormat.format(date);
                timeLabel.setText(time);
            }
        };
        Timer timer = new Timer(1000, timerListener);
        // to make sure it doesn't wait one second at the start
        timer.setInitialDelay(0);
        timer.start();

        // Adding all elements to the JPanels, then JFrame
        main_panel.add(button1);
        main_panel.add(button2);
        main_panel.add(button3);
        main_panel.add(button4);
        main_panel.add(go_back);
        main_panel.add(timeLabel);
        main_menu.add(menu_title);
        main_menu.add(main_panel);

        main_menu.setVisible(true);
    }

    /*
     * // TODO: import URL class
     * public class SoundPlayer(){
     * Clip clip;
     * // etc...
     * public void init();
     * public void play();
     * public void loop(){
     * clip. setMicrosecondPosition(n);
     * }
     * public void stop(){
     * clip.stop();
     * }
     * }
     * 
     * public static void PlayMusic(String location) {
     * try {
     * File musicPath = new File(location);
     * if (musicPath.exists()) {
     * AudioInputStream sound = AudioSystem.getAudioInputStream(musicPath);
     * Clip clip = AudioSystem.getClip();
     * clip.open(sound);
     * clip.start();
     * }
     * } catch (Exception e) {
     * System.out.println("Error:" + e);
     * }
     * }
     */

    /*
     * File file = new File("/home/runner/Interfacevisuelle/Paragon.wav"); // file:
     * Paragon.wav
     * System.out.println(file.getPath() + "\n \n");
     * // absolute path: /home/runner/Interfacevisuelle/Paragon.wav
     * Scanner scanner = new Scanner(System.in);
     * AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
     * Clip clip = AudioSystem.getClip();
     * clip.open(audioStream);
     * String response ="";
     * 
     * 
     * 
     * while(!response.equals("Q")){
     * System.out.println("P: Play, S: Stop, R: reset, Q: Quit");
     * System.out.print("Enter your choice :");
     * response = scanner.next();
     * response = response.toUpperCase();
     * 
     * switch(response){
     * case("S"): clip.stop(); System.out.println("BGM stopped playing");
     * break;
     * case("P"): clip.start(); System.out.println("BGM is playing");
     * break;
     * case("R"): clip.setMicrosecondPosition(0);
     * System.out.println("BGM is reset");
     * default: System.out.println("Invalid input");
     * }
     * }
     * 
     * clip.start();
     */

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        start_screen();
    }

}
