import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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
    private JButton userHelpButton;
    private JLabel userLoginHelp;
    private JButton upButton;
    private JButton downButton;
    private JLabel currFloorLabel;
    private JLabel poiCreationLocationPrompt;
    private final Layer builtinPOIs;
    public static User currUser;
    private static final String mapPanelCardName = "mapPanel";
    private static final String loginPanelCardName = "loginPanel";
    private static final String poiCreationCardName = "poiCreationPanel";
    private String currMap;
    private Building currBuilding;
    private Floor currFloor;
    private Building[] buildings = new Building[3];
    private String[][] maps = {
            {"Maps/MC-BF/MC-BF-1.png", "Maps/MC-BF/MC-BF-2.png", "Maps/MC-BF/MC-BF-3.png", "Maps/MC-BF/MC-BF-4.png", "Maps/MC-BF/MC-BF-5.png"},
            {"Maps/WH-BF/WH-BF-1.png", "Maps/WH-BF/WH-BF-2.png", "Maps/WH-BF/WH-BF-3.png", "Maps/WH-BF/WH-BF-4.png"},
            {"Maps/AFAR-BF/AFAR-BF-1.png", "Maps/AFAR-BF/AFAR-BF-2.png"}};
    private int currFloorNum;

    public GUI(String title) throws HeadlessException, IOException, ParseException, UnsupportedAudioFileException, LineUnavailableException {
        CardLayout cardLayout = (CardLayout)mainPanel.getLayout();

        builtinPOIs = new Layer("builtIn", 0,1, 1, map);
        //currUser = new User("paul", "password");
        currMap = "Maps/MC-BF/MC-BF-1.png";

        setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userProfileImage.setIcon(resizedImageIcon("Icons/lw.png", 100, 100));
        middlesexThumbnail.setIcon(resizedImageIcon("Thumbnails/MC.jpg", 100, 100));
        westminsterhallThumbnail.setIcon(resizedImageIcon("Thumbnails/WH.jfif", 100, 100));
        afarThumbnail.setIcon(resizedImageIcon("Thumbnails/AFAR.jfif", 100, 100));
        mapsMenuCloseButton.setIcon(resizedImageIcon("Icons/left-arrow.png", 20, 20));
        poiMenuCloseButton.setIcon(resizedImageIcon("Icons/right-arrow.png", 20, 20));
        userLoginHelp.setIcon(resizedImageIcon("Icons/help.png", 40, 40));

        upButton.setIcon(resizedImageIcon("Icons/up-arrow.png", 48,48));
        downButton.setIcon(resizedImageIcon("Icons/down-arrow.png", 48, 48));

        upButton.setFocusPainted(false);
        upButton.setContentAreaFilled(false);
        downButton.setFocusPainted(false);
        downButton.setContentAreaFilled(false);

        mapContainer.setLayout(null);
        mapContainer.setBounds(0, 0, mapScrollPane.getWidth(), mapScrollPane.getHeight());
        map = new JLabel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);
        //////////////////////////////FREE YE//////////////////DONT DELETE THIS NECESSARY FOR THINGS TO WORK/////////////////////////////////////////////////////////
        int offset = 11111-11111+11111-11111+11111-11111+11111-11111+11111-11111+11111-11110;///////////////////////////
        //////////////////////////////INITIALIZE 3 BUILDING OBJECTS/////////////////////////////////////////////////////
        /*///////////////////////////////////////////
        $$\           $$\     $$\ $$\                                       $$\
        $$ |          $$ |    $$ |\__|                                      \__|
                $$ | $$$$$$\  $$ |    $$ |$$\ $$$$$$\$$$$\   $$$$$$\  $$\  $$\  $$\ $$\  $$$$$$\   $$$$$$\
        $$ |$$  __$$\ $$ |    $$ |$$ |$$  _$$  _$$\ $$  __$$\ $$ | $$ | $$ |$$ |$$  __$$\ $$  __$$\
        $$ |$$ /  $$ |$$ |    $$ |$$ |$$ / $$ / $$ |$$$$$$$$ |$$ | $$ | $$ |$$ |$$ |  \__|$$$$$$$$ |
                $$ |$$ |  $$ |$$ |    $$ |$$ |$$ | $$ | $$ |$$   ____|$$ | $$ | $$ |$$ |$$ |      $$   ____|
                $$ |\$$$$$$  |$$ |$$\ $$ |$$ |$$ | $$ | $$ |\$$$$$$$\ \$$$$$\$$$$  |$$ |$$ |      \$$$$$$$\
\__| \______/ \__|$  |\__|\__|\__| \__| \__| \_______| \_____\____/ \__|\__|       \_______|
                  \_/
                */
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////COMMENT///v/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////TO///////v/////////////////////////////////////MOOZIK///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////MUTE////v//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////v/////v///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////COMMENT THIS OUT TO SILENCE MUSIC////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //AudioInputStream audioStream = null; try {File file = new File("Icons/li.wav");audioStream = AudioSystem.getAudioInputStream(file);Clip clip = AudioSystem.getClip();clip.open(audioStream);clip.start();} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {System.err.println(ex);} finally {try {if (audioStream != null) {audioStream.close();}} catch (IOException ex) {System.err.println(ex);}}
        ///////////////////////^/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////^/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////^//////////////////////////////////////////////////////////RIGHT HERE///////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        Building middlesex = new Building("Middlesex College", 5, 1, map);
        Building westminsterHall = new Building("Westminster Hall", 4, 2, map);
        Building afar = new Building("Avian Research", 2, 3, map);
        buildings[0] = middlesex;
        buildings[1] = westminsterHall;
        buildings[2] = afar;
        currBuilding = middlesex;
        currFloorNum = 1;
        currFloor = middlesex.getFloor(currFloorNum);
        //System.out.println("Current user is " + currUser.getUsername());
        setBuilding(middlesex, currUser, currFloorNum);
        currFloorLabel.setText("Floor: " + currFloorNum);
        //initUsrSettings(thisUser, middlesex);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////NOW WHEN WE WANT TO ADD A NEW POI, WE CAN DO SO BY CALLING THE FOLLOWING METHOD////

     /*   if (middlesex.getFloor(1-offset) == (null)) {
            System.out.println("Floor 1 is null"); //shouldn't print
        }
        if (middlesex.getFloor(1).getLayer(2-offset) == (null)) {
            System.out.println("Floor 1 MX layer 2 is null"); //shouldn't print
        }
        //else print out the layer object attributes

        System.out.println(middlesex.getFloor(1).getLayer(2-offset).getLayerID()); //should print 2
        System.out.println(middlesex.getFloor(1).getLayer(2-offset).getFloorID()); //should print 1
        if (middlesex.getFloor(1).getLayer(2-offset).getPOI("A") == (null)) {
            System.out.println("POI A @ Floor 1 MX layer 2 :: is null"); //shouldnt print
        }
        //System.out.println(middlesex.getFloor(1).getLayer(2));

        System.out.println(middlesex.getFloor(1).getLayer(2-offset).getPOI("A")); //shouldnt print null
        //for testing, print out all the POI attributes
        System.out.println(middlesex.getFloor(1).getLayer(2-offset).getPOI("A").getBuiltin()); //should print true
        System.out.println(middlesex.getFloor(1).getLayer(2-offset).getPOI("A").getName()); //should print A
        System.out.println(middlesex.getFloor(1).getLayer(2-offset).getPOI("A").getRoomNumber()); //should print 333
        System.out.println(middlesex.getFloor(1).getLayer(2-offset).getPOI("A").getDescription());
        System.out.println(middlesex.getFloor(1).getLayer(2-offset).getPOI("A").getLayerID()); //should print 2
        System.out.println(middlesex.getFloor(1).getLayer(2-offset).getPOI("A").getFloorID()); //should print 1
        System.out.println(middlesex.getFloor(1).getLayer(2-offset).getPOI("A").getBuildingID()); //should print 1*/
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////


        this.setContentPane(mainPanel);
        this.pack();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);
                boolean check = false;
                try {
                    check = User.isValidCredentials(username, password.toCharArray());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                if (check) {//Login successful
                    usernameField.setText("");
                    passwordField.setText("");
                    cardLayout.show(mainPanel, mapPanelCardName); //show map panel
                    //create new user object
                    currUser = new User(username, password); //create new user object matching the username and password
                    //terminal prompt for testing
                    System.out.println("WELCOME TO CUAMPES-ACGISANT, " + currUser.getUsername().toUpperCase() + "!");
                    //print current time in all caps
                    System.out.println("CURRENT TIME IS: " + new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime()).toUpperCase());
                    System.out.println("Could I get you anything? Coffee or tea perhaps?");

                }
                else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }


//                if (isValidCredentials(usernameField.getText(), passwordField.getPassword())) {
//
//                    if (isValidCredentials(usernameField.getText(), passwordField.getPassword())) {
//
//                        usernameField.setText("");
//                        passwordField.setText("");
//                        cardLayout.show(mainPanel, mapPanelCardName);
//                    }
//                }
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
                currFloorNum = 1;
                currFloorLabel.setText("Floor: " + currFloorNum);
                setBuilding(middlesex, currUser, 1);
                //initUsrSettings(currUser,middlesex);
                map.setIcon(resizedImageIcon(currMap, mapContainer.getWidth(), mapContainer.getHeight()));
            }
        });
        westminsterhallThumbnail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                currMap = "Maps/WH-BF/WH-BF-1.png";
                currFloorNum = 1;
                currFloorLabel.setText("Floor: " + currFloorNum);
                setBuilding(westminsterHall, currUser, 1);
                //initUsrSettings(thisUser,westminsterHall);
                map.setIcon(resizedImageIcon(currMap, mapContainer.getWidth(), mapContainer.getHeight()));
            }
        });
        afarThumbnail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                currMap = "Maps/AFAR-BF/AFAR-BF-1.png";
                currFloorNum = 1;
                currFloorLabel.setText("Floor: " + currFloorNum);
                setBuilding(afar, currUser, 1);
                //initUsrSettings(thisUser,afar);
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
                    ArrayList<String> empty = new ArrayList<String>(); //empty array list of strings for favorites list
                    new POI(builtinCheckbox.isSelected(), thisPoiName, thisRmNum, thisDescription,1,3,3, (e.getPoint().getX() - 12) / mapContainer.getSize().getWidth(), (e.getPoint().getY() - 12) / mapContainer.getSize().getHeight(), map, currUser.getUsername(), empty);
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

        userLoginHelp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(null, "Help: \nC.U.A.M.P.E.S.-A.C.G.I.S.A.N.T. is a campus navigational aid with many built-in features to optimize the user experience. \nLogin with username and password.");
            }
        });
        userHelpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(null, "How to use C.U.A.M.P.E.S - A.C.G.I.S.A.N.T.: \nUse the maps bar on the left side to change buildings. Below that is the log out button, which takes the user back to the login screen. \nThe map in the center of the GUI can be scrolled through using the mouse and scroll bars. \nActive POIs are listed on the right side menu. The Create New POI button allows the user to create their own POI, provided they offer their own POI name, room number and description. The checkboxes underneath that button filter which categories of POIs are currently visible.");
            }
        });
        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (currBuilding.getBuildingName().equals("Middlesex College")){
                    if (currFloorNum < maps[0].length){
                        currFloorNum++;
                        setBuilding(middlesex, currUser, currFloorNum);
                        currMap = maps[0][currFloorNum - 1];
                        map.setIcon(resizedImageIcon(currMap, mapContainer.getWidth(), mapContainer.getHeight()));
                    }
                }
                else if (currBuilding.getBuildingName().equals("Westminster Hall")) {
                    if (currFloorNum < maps[1].length) {
                        currFloorNum++;
                        setBuilding(westminsterHall, currUser, currFloorNum);
                        currMap = maps[1][currFloorNum - 1];
                        map.setIcon(resizedImageIcon(currMap, mapContainer.getWidth(), mapContainer.getHeight()));
                    }
                }
                else if (currBuilding.getBuildingName().equals("Avian Research")) {
                    if (currFloorNum < maps[2].length) {
                        currFloorNum++;
                        setBuilding(afar, currUser, currFloorNum);
                        currMap = maps[2][currFloorNum - 1];
                        map.setIcon(resizedImageIcon(currMap, mapContainer.getWidth(), mapContainer.getHeight()));
                    }
                }
                currFloorLabel.setText("Floor: " + currFloorNum);
            }
        });
        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (currBuilding.getBuildingName().equals("Middlesex College")){
                    if (currFloorNum > 1){
                        currFloorNum--;
                        setBuilding(middlesex, currUser, currFloorNum);
                        currMap = maps[0][currFloorNum - 1];
                        map.setIcon(resizedImageIcon(currMap, mapContainer.getWidth(), mapContainer.getHeight()));
                    }
                }
                else if (currBuilding.getBuildingName().equals("Westminster Hall")) {
                    if (currFloorNum > 1) {
                        currFloorNum--;
                        setBuilding(westminsterHall, currUser, currFloorNum);
                        currMap = maps[1][currFloorNum - 1];
                        map.setIcon(resizedImageIcon(currMap, mapContainer.getWidth(), mapContainer.getHeight()));
                    }
                }
                else if (currBuilding.getBuildingName().equals("Avian Research")) {
                    if (currFloorNum > 1) {
                        currFloorNum--;
                        setBuilding(afar, currUser, currFloorNum);
                        currMap = maps[2][currFloorNum - 1];
                        map.setIcon(resizedImageIcon(currMap, mapContainer.getWidth(), mapContainer.getHeight()));
                    }
                }
                currFloorLabel.setText("Floor: " + currFloorNum);
            }
        });
    }

    public static void main(String[] args) throws IOException, ParseException, UnsupportedAudioFileException, LineUnavailableException {
        currUser = new User("YEEZY", "YEEZY");
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

    private void setBuilding(Building building, User currUser, int floorNum){
        //print current user
        //TESTING -- System.out.println("SETBUILDING()))))current user is: " + currUser.getUsername());
        currBuilding =  building;
        currFloor = building.getFloor(floorNum - 1); //may need to change to be dynamic
        String currUsername = currUser.getUsername();
        for (Building b : buildings){
            for (Floor f : b.getFloors()){
                for (Layer l : f.getLayers()){
                    l.hideLayer();
                }
            }
        }

        for (Layer l : currFloor.getLayers()){
            l.showLayer();

            for (POI p : l.getPOIList()){
                //System.out.println("looking at POI made by: " + p.getCreatingUsr());
                if (p.getCreatingUsr() == null){
                    //null creating user. if its not builtin, hide it
                    if (p.getBuiltin() == false) {
                        p.setVisible(false);
                    }
                    if (p.getBuiltin() == true) { //built-in POIs don't need a user association
                        //System.out.println("builtin POI found");
                        p.setVisible(true);
                    }
                }
                else{ //not null creating user
                    if(currUsername.equals(p.getCreatingUsr())){
                        p.setVisible(true);
                    }
                    else{
                        p.setVisible(false);
                        if (p.getBuiltin() == true) { //built-in POIs don't need a user association
                            //System.out.println("builtin POI found");
                            p.setVisible(true);
                        }
                    }
                }
            }
        }
    }
}
