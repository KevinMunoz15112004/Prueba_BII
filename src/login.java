import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login {
    public JPanel mainPanel;
    private JTextField userText;
    private JPasswordField passwordField;
    private JButton INGRESARButton;

    public login() {
        INGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/Prueba";
                String user = "root";
                String password = "123456";

                String userName = userText.getText();
                String userPassword = new String(passwordField.getText());

                if(validarAcceso(userName, userPassword, url, user, password)) {
                    JOptionPane.showMessageDialog(mainPanel, "Login Exitoso");

                    JFrame loginFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                    loginFrame.dispose();

                    JFrame insertarFrame = new JFrame("Insertar Datos");
                    insertarFrame.setContentPane(new gestion_calificaciones().mainPanel_2);
                    insertarFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    insertarFrame.setSize(600, 600);
                    insertarFrame.setPreferredSize(new java.awt.Dimension(600, 600));
                    insertarFrame.pack();
                    insertarFrame.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(mainPanel, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }


            }
        });
    }

    public boolean validarAcceso(String usuario, String clave, String url, String userDB, String passwordDB) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = DriverManager.getConnection(url, userDB, passwordDB);
            String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, clave);
            rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally{
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
}
