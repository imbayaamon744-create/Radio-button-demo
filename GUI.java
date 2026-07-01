import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    // Declare UI components globally
    private JRadioButton birdButton, catButton, dogButton, rabbitButton, pigButton;
    private ButtonGroup group;
    private JLabel imageLabel;

    public GUI() {
        // 1. Set up the window frame properties
        setTitle("RadioButtonDemo");
        setSize(550, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Splits window into Left (WEST) and Center

        // 2. Initialize the Radio Buttons
        birdButton = new JRadioButton("Bird");
        catButton = new JRadioButton("Cat");
        dogButton = new JRadioButton("Dog");
        rabbitButton = new JRadioButton("Rabbit");
        pigButton = new JRadioButton("Pig");

        // Pre-select the Pig button to match the assignment screenshot
        pigButton.setSelected(true);

        // 3. Group the buttons logically so only one can be checked at a time
        group = new ButtonGroup();
        group.add(birdButton);
        group.add(catButton);
        group.add(dogButton);
        group.add(rabbitButton);
        group.add(pigButton);

        // 4. Register action listeners to detect clicks
        birdButton.addActionListener(this);
        catButton.addActionListener(this);
        dogButton.addActionListener(this);
        rabbitButton.addActionListener(this);
        pigButton.addActionListener(this);

        // 5. Create a vertical panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 5, 5)); // 5 rows, 1 column
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
        buttonPanel.add(birdButton);
        buttonPanel.add(catButton);
        buttonPanel.add(dogButton);
        buttonPanel.add(rabbitButton);
        buttonPanel.add(pigButton);

        // 6. Set up the Image display area (Center)
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        updateImage("pig.png"); // Load initial default image

        // 7. Attach components to the frame window regions
        add(buttonPanel, BorderLayout.WEST);
        add(imageLabel, BorderLayout.CENTER);

        // Center the window on your computer monitor screen
        setLocationRelativeTo(null); 
    }

    // Handles the click actions for all radio buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        String petSelected = "";
        String imageName = "";

        // Check which button triggered the click action
        if (e.getSource() == birdButton) {
            petSelected = "Bird";
            imageName = "bird.png";
        } else if (e.getSource() == catButton) {
            petSelected = "Cat";
            imageName = "cat.png";
        } else if (e.getSource() == dogButton) {
            petSelected = "Dog";
            imageName = "dog.png";
        } else if (e.getSource() == rabbitButton) {
            petSelected = "Rabbit";
            imageName = "rabbit.png";
        } else if (e.getSource() == pigButton) {
            petSelected = "Pig";
            imageName = "pig.png";
        }

        // Update the picture displayed on the right side
        updateImage(imageName);

        // ASSIGNMENT REQUIREMENT: Display selection using a pop-up message box
        JOptionPane.showMessageDialog(this, "You selected: " + petSelected, "Selection Changed", JOptionPane.INFORMATION_MESSAGE);
    }

    // Helper method to look for and scale images dynamically
    private void updateImage(String filename) {
        try {
            // Force Java to look directly inside the exact folder where GUI class lives
            java.net.URL imgURL = getClass().getResource(filename);
            
            if (imgURL != null) {
                ImageIcon icon = new ImageIcon(imgURL);
                // Scale image down to 180x180 pixels smoothly so it doesn't skew layouts
                Image scaledImage = icon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
                imageLabel.setText(""); // Clear out any previous error text
            } else {
                imageLabel.setIcon(null);
                imageLabel.setText("Image not found: " + filename);
            }
        } catch (Exception ex) {
            imageLabel.setText("Error loading: " + filename);
        }
    }

    // Main executable entry point
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }
}