import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{
    private JPanel login;
    private JLabel userProfileImage;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel usernameLabel;
    private JButton loginButton;
    private JLabel passwordLabel;
    private JLabel lockLabel;
    private JPanel mainPanel;

    private JTabbedPane mainTabbedPane;
    private JCheckBox checkBox1;

    public GUI(String title) throws HeadlessException {
        setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userProfileImage.setIcon(new ImageIcon(new ImageIcon("user.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        this.setContentPane(mainPanel);

        this.pack();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainTabbedPane.setSelectedIndex(1);
            }
        });
    }

    public static void main(String[] args) {
        GUI frame = new GUI("C.U.A.M.P.E.S.-A.C.G.I.S.A.N.T.");
        frame.setSize(1080, 720);
        frame.mainTabbedPane.setSize(1080, 720);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private boolean isValidCredentials(String username, char[] password){
        return true;
    }
}
