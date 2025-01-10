import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class gestion_calificaciones {
    private JTextField cedulaBTN;
    private JTextField m1;
    private JTextField m2;
    private JTextField m3;
    private JTextField m4;
    private JTextField m5;
    private JButton INSERTARDATOSButton;
    public JPanel mainPanel_2;
    private JButton VERREGISTROSButton;
    private JTextField nombreBTN;
    private JLabel confirmacionLabel;

    public gestion_calificaciones() {
        INSERTARDATOSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/Prueba";
                String user = "root";
                String password = "123456";

                String cali1 = m1.getText();
                String cali2 = m2.getText();
                String cali3 = m3.getText();
                String cali4 = m4.getText();
                String cali5 = m5.getText();
                String valNombre = nombreBTN.getText();
                String valCedula = cedulaBTN.getText();

                if(valCedula.isEmpty()){
                    confirmacionLabel.setText("Ingrese una cedula");
                    return;
                }

                if (valNombre.isEmpty()){
                    confirmacionLabel.setText("El nombre debe incluir letras y espacios");
                    return;
                }

                Double calificacion = 0.0;

                try {
                    calificacion = Double.parseDouble(cali1);
                    if (calificacion < 0 || calificacion > 20 ) {
                        confirmacionLabel.setText("La calificación debe estar ente 0 y 20, siguiendo el formato: 0.0");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    confirmacionLabel.setText("Por favor, la calificacion debe ser un número, siguiendo el formato: 0.0");
                    return;
                }
                try{
                    calificacion = Double.parseDouble(cali2);
                    if (calificacion < 0 || calificacion > 20 ) {
                        confirmacionLabel.setText("La calificación debe estar ente 0 y 20, siguiendo el formato: 0.0");
                        return;
                    }
                }catch (NumberFormatException ex){
                    confirmacionLabel.setText("Por favor, la calificacion debe ser un número, siguiendo el formato: 0.0");
                }
                try{
                    calificacion = Double.parseDouble(cali3);
                    if (calificacion < 0 || calificacion > 20 ) {
                        confirmacionLabel.setText("La calificación debe estar ente 0 y 20, siguiendo el formato: 0.0");
                        return;
                    }
                }catch (NumberFormatException ex){
                    confirmacionLabel.setText("Por favor, la calificacion debe ser un número, siguiendo el formato: 0.0");
                }
                try{
                    calificacion = Double.parseDouble(cali4);
                    if (calificacion < 0 || calificacion > 20 ) {
                        confirmacionLabel.setText("La calificación debe estar ente 0 y 20, siguiendo el formato: 0.0");
                        return;
                    }
                }catch (NumberFormatException ex){
                    confirmacionLabel.setText("Por favor, la calificacion debe ser un número, siguiendo el formato: 0.0");
                }
                try{
                    calificacion = Double.parseDouble(cali5);
                    if (calificacion < 0 || calificacion > 20 ) {
                        confirmacionLabel.setText("La calificación debe estar ente 0 y 20, siguiendo el formato: 0.0");
                        return;
                    }
                }catch (NumberFormatException ex){
                    confirmacionLabel.setText("Por favor, la calificacion debe ser un número, siguiendo el formato: 0.0");
                }

                Connection conn = null;
                PreparedStatement ps = null;

                try {
                    conn = DriverManager.getConnection(url, user, password);

                    String sql = "INSERT INTO estudiantes (cedula, nombre, materia1, materia2, materia3, materia4, materia5) VALUES (?,?,?,?,?,?,?)";
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, valCedula);
                    ps.setString(2, valNombre);
                    ps.setString(3, cali1);
                    ps.setString(4, cali2);
                    ps.setString(5, cali3);
                    ps.setString(6, cali4);
                    ps.setString(7, cali5);

                    int cantidadFilas = ps.executeUpdate();
                    if (cantidadFilas > 0) {
                        confirmacionLabel.setText("Se ha registrado con exito");
                    }
                    else{
                        confirmacionLabel.setText("Ha ocurrido un error");
                    }
                }catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        VERREGISTROSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame visualizacion = new JFrame("Visualizar Datos");
                visualizacion.setContentPane(new visualizacion().mainPanel_3);
                visualizacion.setSize(600, 600);
                visualizacion.setPreferredSize(new java.awt.Dimension(600, 600));
                visualizacion.pack();
                visualizacion.setVisible(true);
            }
        });
    }
}
