import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


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

    private static final String mapPanelCardName = "mapPanel";
    private static final String loginPanelCardName = "loginPanel";

    public GUI(String title) throws HeadlessException {
        CardLayout cardLayout = (CardLayout)mainPanel.getLayout();


        setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userProfileImage.setIcon(resizedImageIcon("Icons/user.png", 100, 100));
        middlesexThumbnail.setIcon(resizedImageIcon("Thumbnails/MC.jpg", 100, 100));
        westminsterhallThumbnail.setIcon(resizedImageIcon("Thumbnails/WH.jfif", 100, 100));
        afarThumbnail.setIcon(resizedImageIcon("Thumbnails/AFAR.jfif", 100, 100));
        mapsMenuCloseButton.setIcon(resizedImageIcon("Icons/left-arrow.png", 20, 20));
        poiMenuCloseButton.setIcon(resizedImageIcon("Icons/right-arrow.png", 20, 20));
        this.setContentPane(mainPanel);
        this.pack();


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isValidCredentials(usernameField.getText(), passwordField.getPassword())){
                    usernameField.setText("");
                    passwordField.setText("");
                    cardLayout.show(mainPanel, mapPanelCardName);
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
                map.setIcon(new ImageIcon("Maps/MC-BF/MC-BF-1.png"));
            }
        });
        westminsterhallThumbnail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                map.setIcon(new ImageIcon("Maps/WH-BF/WH-BF-1.png"));
            }
        });
        afarThumbnail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                map.setIcon(new ImageIcon("Maps/AFAR-BF/AFAR-BF-1.png"));
            }
        });

        mapsMenuCloseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(mapMenu.isVisible()){
                    mapMenu.setVisible(false);
                    mapsMenuCloseButton.setIcon(resizedImageIcon("Icons/right-arrow.png", 20, 20));
                }
                else {
                    mapMenu.setVisible(true);
                    mapsMenuCloseButton.setIcon(resizedImageIcon("Icons/left-arrow.png", 20, 20));
                }
            }
        });
        poiMenuCloseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(poiMenu.isVisible()){
                    poiMenu.setVisible(false);
                    poiMenuCloseButton.setIcon(resizedImageIcon("Icons/left-arrow.png", 20, 20));
                }
                else {
                    poiMenu.setVisible(true);
                    poiMenuCloseButton.setIcon(resizedImageIcon("Icons/right-arrow.png", 20, 20));
                }
            }
        });
    }

    public static void main(String[] args) {
        GUI frame = new GUI("C.U.A.M.P.E.S.-A.C.G.I.S.A.N.T.");
        frame.setMinimumSize(new Dimension(1080, 720));
        frame.setVisible(true);
    }

    private ImageIcon resizedImageIcon(String imagePath, int width, int height){
        return new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    }
    private boolean isValidCredentials(String username, char[] password){
        return true;
    }

}
