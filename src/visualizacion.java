import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class visualizacion {
    private JTable table1;
    public JPanel mainPanel_3;
    private JScrollPane Scroll;

    public visualizacion() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Cédula");
        model.addColumn("Nombre");
        model.addColumn("Materia 1");
        model.addColumn("Materia 2");
        model.addColumn("Materia 3");
        model.addColumn("Materia 4");
        model.addColumn("Materia 5");

        table1.setModel(model);

        String url = "jdbc:mysql://localhost:3306/Prueba";
        String user = "root";
        String password = "123456";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT * FROM estudiantes";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getDouble("materia1"),
                        rs.getDouble("materia2"),
                        rs.getDouble("materia3"),
                        rs.getDouble("materia4"),
                        rs.getDouble("materia5")
                });
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException er) {
                er.printStackTrace();
            }
        }
    }
}
