import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Conexao{
    public static void main (String[] args) {
        try{
            Connection connection = DAO.createConnection();
                Usuario usuario = new Usuario("bob.esponja", "bob das esponjas", "012");
                
                /*INSERT Usuário */
                PreparedStatement stmt = connection.prapareStatement(
                    "INSERT INTO usuario(user_name, name, password) VALUES (?,?,?);"
                );
                stmt.setString(1, usuario.getUserName());
                stmt.setString(2, usuario.getName());
                stmt.setString(3, usuario.getPassword());
                stmt.execute();
                
                /* SELECT ALL USERS */
                imprimirUsuarios(connection);
                
                /*  DELETE id = 2 */
                PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM usuario WHERE id = ?;"
                );
                stmt.setInt(1, 2);
                stmt.execute();
                imprimirUsuarios(connection);
                
                /* UPDATE id = 1 */
                stmt = connection.prepareStatement(
                    "UPDATE usuario SET user_name = ?, name = ?, password = ? WHERE id = ?;"
                );
                stmt.setString(1, "calca.quadrada");
                stmt.setString(2, "Calça quadrada");
                stmt.setString(3, "1234");
                stmt.setInt(4, 1);
                stmt.execute();
                imprimirUsuarios(connection);
            }catch (Exception e){
                System.out.println(e);
            }
        }
        
        public static void imprimirUsuarios(Connection connection) throws Exception{
            ResultSet rs = connection.createStatement().executeQuery(
                "SELECT * FROM usuario;"
            );
            while(rs.next()){
                Usuario usuario2 = new Usuario(
                    rs.getInt("id"),
                    rs.getString("user_name"),
                    rs.getString("name"),
                    rs.getString("password")
                );
                System.out.println(usuario2);
                System.out.println("================================");
    }
}
