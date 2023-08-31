package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public static Connection conection() {
		Connection connection =  null;
		try {
		 connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/dbinfox","root" ,"");
			
		return	connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  return null;
		}
 
	}
}
