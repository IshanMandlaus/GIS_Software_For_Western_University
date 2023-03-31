import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Objects;

public class GUI extends JFrame{
    private JLabel userProfileImage;
    private JButton loginButton;
    private JPanel mainPanel;
    private JPanel loginPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel lockLabel;
    private JPanel mapPanel;
    private JLabel map;
    private JPanel mapMenu;
    private JPanel poiMenu;
    private JLabel middlesexThumbnail;
    private JButton logoutButton;
    private JLabel mapMenuLabel;
    private JLabel westminsterhallThumbnail;
    private JLabel afarThumbnail;
    private JLabel mapsMenuCloseButton;
    private JLabel poiMenuCloseButton;
    private JLabel poiMenuLabel;
    private JCheckBox classroomLayerCheckbox;
    private JLabel layerDisplayLabel;
    private JButton createNewPOIButton;
    private JCheckBox washroomsLayerCheckbox;
    private JCheckBox restauLayerCheckbox;
    private JCheckBox entryexitLayerCheckbox;
    private JCheckBox genlabLayerCheckbox;
    private JCheckBox complabLayerCheckbox;
    private JCheckBox collabLayerCheckbox;
    private JCheckBox usercreatedLayerCheckbox;
    private JList list1;
    private boolean poiCreateMode = false;
    private String thisPoiName;
    private int thisRmNum;
    private String thisDescription;
    private JPanel poiCreationPanel;
    private JTextField poiName;
    private JTextField poiRoomNumber;
    private JTextField poiDescription;
    private JButton submitButton;
    private JButton cancelButton;
    private JPanel mapContainer;
    private JScrollPane mapScrollPane;
    private JCheckBox builtinCheckbox;
    private JComboBox layerCombobox;
    private JLabel poiCreationLocationPrompt;
    private final Layer builtinPOIs;
    private final User currUser;
    private static final String mapPanelCardName = "mapPanel";
    private static final String loginPanelCardName = "loginPanel";
    private static final String poiCreationCardName = "poiCreationPanel";
    private String currMap;

    public GUI(String title) throws HeadlessException, IOException, ParseException {
        CardLayout cardLayout = (CardLayout)mainPanel.getLayout();

        builtinPOIs = new Layer("builtIn", 0,map);
        currUser = new User("paul", "password");
        currMap = "Maps/MC-BF/MC-BF-1.png";

        setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userProfileImage.setIcon(resizedImageIcon("Icons/user.png", 100, 100));
        middlesexThumbnail.setIcon(resizedImageIcon("Thumbnails/MC.jpg", 100, 100));
        westminsterhallThumbnail.setIcon(resizedImageIcon("Thumbnails/WH.jfif", 100, 100));
        afarThumbnail.setIcon(resizedImageIcon("Thumbnails/AFAR.jfif", 100, 100));
        mapsMenuCloseButton.setIcon(resizedImageIcon("Icons/left-arrow.png", 20, 20));
        poiMenuCloseButton.setIcon(resizedImageIcon("Icons/right-arrow.png", 20, 20));

        mapContainer.setLayout(null);
        mapContainer.setBounds(0, 0, mapScrollPane.getWidth(), mapScrollPane.getHeight());
        map = new JLabel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);


        this.setContentPane(mainPanel);
        this.pack();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isValidCredentials(usernameField.getText(), passwordField.getPassword())) {

                    if (isValidCredentials(usernameField.getText(), passwordField.getPassword())) {

                        usernameField.setText("");
                        passwordField.setText("");
                        cardLayout.show(mainPanel, mapPanelCardName);
                    }
                }
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, loginPanelCardName);
            }
        });
        middlesexThumbnail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                currMap = "Maps/MC-BF/MC-BF-1.png";
                map.setIcon(resizedImageIcon(currMap, mapContainer.getWidth(), mapContainer.getHeight()));
            }
        });
        westminsterhallThumbnail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                currMap = "Maps/WH-BF/WH-BF-1.png";
                map.setIcon(resizedImageIcon(currMap, mapContainer.getWidth(), mapContainer.getHeight()));
            }
        });
        afarThumbnail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                currMap = "Maps/AFAR-BF/AFAR-BF-1.png";
                map.setIcon(resizedImageIcon(currMap, mapContainer.getWidth(), mapContainer.getHeight()));
            }
        });

        mapsMenuCloseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!poiCreateMode) {
                    super.mouseClicked(e);
                    if (mapMenu.isVisible()) {
                        mapMenu.setVisible(false);
                        mapsMenuCloseButton.setIcon(resizedImageIcon("Icons/right-arrow.png", 20, 20));
                    } else {
                        mapMenu.setVisible(true);
                        mapsMenuCloseButton.setIcon(resizedImageIcon("Icons/left-arrow.png", 20, 20));
                    }
                }
            }
        });
        poiMenuCloseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!poiCreateMode) {
                    super.mouseClicked(e);
                    if (poiMenu.isVisible()) {
                        poiMenu.setVisible(false);
                        poiMenuCloseButton.setIcon(resizedImageIcon("Icons/left-arrow.png", 20, 20));
                    } else {
                        poiMenu.setVisible(true);
                        poiMenuCloseButton.setIcon(resizedImageIcon("Icons/right-arrow.png", 20, 20));
                    }
                }
            }
        });
        map.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (poiCreateMode) {
                    super.mouseClicked(e);
                    //POI(int builtin, String name, int rmNum, String description, int buildingID, int floorID, int layerID, double relative_x, double relative_y, JLabel parent)
                    new POI(builtinCheckbox.isSelected(), thisPoiName, thisRmNum, thisDescription,1,3,3, (e.getPoint().getX() - 12) / mapContainer.getSize().getWidth(), (e.getPoint().getY() - 12) / mapContainer.getSize().getHeight(), map);
                    // Clear text fields in the creation menu after the POI is created
                    poiRoomNumber.setText("");
                    poiDescription.setText("");
                    poiName.setText("");

                    map.repaint();
                    poiCreateMode = false;
                    mapMenu.setVisible(true);
                    poiMenu.setVisible(true);
                }
            }
        });
        mapContainer.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                map.setIcon(resizedImageIcon(currMap, mapContainer.getWidth(), mapContainer.getHeight()));
                for(Component c : map.getComponents()){
                    if (c.getClass() == POI.class) ((POI) c).updatePosition();
                }
                map.repaint();
            }
        });
        createNewPOIButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cardLayout.show(mainPanel, poiCreationCardName);
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                thisPoiName = poiName.getText();
                try {
                    thisRmNum = Integer.parseInt(poiRoomNumber.getText());
                    thisDescription = poiDescription.getText();

                    if (Objects.equals(thisPoiName, "") || Objects.equals(thisDescription, "")) {
                        // text fields can't be blank
                        JOptionPane.showMessageDialog(poiCreationPanel, "Invalid Name or Description:\nAll fields must be filled.", "Error" ,JOptionPane.ERROR_MESSAGE);
                    }
                    else if (thisRmNum <= 0) {
                        // room number can't be equal to zero
                        JOptionPane.showMessageDialog(poiCreationPanel, "Invalid Room Number:\nRoom number can't be less than or equal to zero", "Error" ,JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        // Return to the map in POI creation mode, with the menus disabled
                        mapMenu.setVisible(false);
                        poiMenu.setVisible(false);
                        poiCreateMode = true;
                        cardLayout.show(mainPanel, mapPanelCardName);
                        JOptionPane.showMessageDialog(mapPanel, "Click on the map where you would like your new POI to be placed!", "Select POI Location", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(poiCreationPanel, "Invalid Room Number:\nRoom number must be numeric", "Error" ,JOptionPane.ERROR_MESSAGE);
                    poiRoomNumber.setText("");
                }

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(mainPanel, mapPanelCardName);
                // Clear text fields when a POI is created
                poiRoomNumber.setText("");
                poiDescription.setText("");
                poiName.setText("");
            }
        });
        builtinCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                layerCombobox.setVisible(builtinCheckbox.isSelected());
            }
        });
    }

    public static void main(String[] args) throws IOException, ParseException {
        GUI frame = new GUI("C.U.A.M.P.E.S - A.C.G.I.S.A.N.T.");

        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();
        frame.setResizable(false);
        device.setFullScreenWindow(frame);

        frame.setVisible(true);
        ImageIcon logo = new ImageIcon("Icons/logo.png");
        frame.setIconImage(logo.getImage());
    }

    private ImageIcon resizedImageIcon(String imagePath, int width, int height){
        return new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    private boolean isValidCredentials(String username, char[] password){
        return true;
    }
}
