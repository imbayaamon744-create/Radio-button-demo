import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
   
    private JRadioButton  birdButton, catButton, dogButton, rabbitButton, pigButton;
    private ButtonGroup group;
    private JLabel imageLabel;

    public GUI() {
        
        setTitle("RadioButtonDemo");
        setSize(550, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); 

        
        birdButton = new JRadioButton("Bird");
        catButton = new JRadioButton("Cat");
        dogButton = new JRadioButton("Dog");
        rabbitButton = new JRadioButton("Rabbit");
        pigButton = new JRadioButton("Pig");

        
        pigButton.setSelected(true);

        
        group = new ButtonGroup();
        group.add(birdButton);
        group.add(catButton);
        group.add(dogButton);
        group.add(rabbitButton);
        group.add(pigButton);

       
        birdButton.addActionListener(this);
        catButton.addActionListener(this);
        dogButton.addActionListener(this);
        rabbitButton.addActionListener(this);
        pigButton.addActionListener(this);

       
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 5, 5)); 
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
        buttonPanel.add(birdButton);
        buttonPanel.add(catButton);
        buttonPanel.add(dogButton);
        buttonPanel.add(rabbitButton);
        buttonPanel.add(pigButton);

        
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        updateImage("pig.png");

        
        add(buttonPanel, BorderLayout.WEST);
        add(imageLabel, BorderLayout.CENTER);

        
        setLocationRelativeTo(null); 
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        String petSelected = "";
        String imageName = "";

        
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

       
        updateImage(imageName);

        
        JOptionPane.showMessageDialog(this, "You selected: " + petSelected, "Selection Changed", JOptionPane.INFORMATION_MESSAGE);
    }

    
    private void updateImage(String filename) {
        try {
           
            java.net.URL imgURL = getClass().getResource(filename);
            
            if (imgURL != null) {
                ImageIcon icon = new ImageIcon(imgURL);
                
                Image scaledImage = icon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
                imageLabel.setText(""); 
            } else {
                imageLabel.setIcon(null);
                imageLabel.setText("Image not found: " + filename);
            }
        } catch (Exception ex) {
            imageLabel.setText("Error loading: " + filename);
        }
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }
}