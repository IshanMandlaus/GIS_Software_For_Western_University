import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{
    private JPanel Login;
    private JLabel userProfileImage;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel usernameLabel;
    private JButton loginButton;
    private JLabel passwordLabel;
    private JLabel lockLabel;

    public Login(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userProfileImage.setIcon(new ImageIcon(new ImageIcon("user.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        this.setContentPane(Login);
        this.pack();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new Login("C.U.A.M.P.E.S.-A.C.G.I.S.A.N.T.");
        frame.setSize(1080, 720);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
