import java.util.*;
import java.sql.*;

public class Musor_Muveletek {

	private Connection conn;
	
	public Musor_Muveletek() throws Exception {

        String url = "jdbc:mysql://localhost:3306/műsorújság?autoReconnect=true&useSSL=false";
        String user = "root";
        String password = "";
		
		// connect to database
		conn = DriverManager.getConnection(url, user, password);
		
		System.out.println("DB connection successful to: " + url);
	}

	public void RemoveMusor(String musorcime) throws SQLException {
		PreparedStatement stmt = null;
		try {
			//Prepare Statement
			stmt = conn.prepareStatement("DELETE FROM műsor WHERE címe=?");

			//paraméter
			stmt.setString(1,musorcime);

			//törlés
			stmt.executeUpdate();

		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public void updateMusor(Musor musor) throws SQLException {
		PreparedStatement stmt = null;

		try {
			//Prepared Statement
			stmt = conn.prepareStatement("UPDATE műsor SET forgatási_ország=?, kiadás_éve=?, " +
					"korhatár=?, stílus=?, idő_tartam=? WHERE címe=?");
			//paraméterek
			stmt.setString(1, musor.getForgatasi_orszag());
			stmt.setString(2, musor.getKiadas_eve());
			stmt.setInt(3, musor.getKorhatar());
			stmt.setString(4, musor.getStilus());
			stmt.setInt(5, musor.getIdo_tartam());
			stmt.setString(6, musor.getCime());

			//Update futtatása
			stmt.executeUpdate();
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
	}

	public void addMusor(Musor musor) throws Exception {
		PreparedStatement stmt = null;

		try {
			// prepare statement
			stmt = conn.prepareStatement("INSERT INTO műsor"
					+ " (címe, forgatási_ország, kiadás_éve, korhatár, stílus, idő_tartam)"
					+ " values (?, ?, ?, ?, ?, ?)");

			// set params
			stmt.setString(1, musor.getCime());
			stmt.setString(2, musor.getForgatasi_orszag());
			stmt.setString(3, musor.getKiadas_eve());
			stmt.setInt(4, musor.getKorhatar());
			stmt.setString(5, musor.getStilus());
			stmt.setInt(6, musor.getIdo_tartam());

			// execute SQL
			stmt.executeUpdate();
		}
		finally {
			if(stmt != null) {
				stmt.close();
			}
		}

	}
	
	public List<Musor> musorokListazasa() throws Exception {
		List<Musor> list = new ArrayList<>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from műsor");
			
			while (rs.next()) {
				Musor musor = sorokMusorraAlakitasa(rs);
				list.add(musor);
			}

			return list;		
		}
		finally {
			close(stmt, rs);
		}
}
	
	public List<Musor> musorKereso(String cime) throws Exception {
		List<Musor> list = new ArrayList<>();

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			cime = "%" + cime + "%";
			stmt = conn.prepareStatement("SELECT * FROM műsor WHERE címe LIKE ?");
			
			stmt.setString(1, cime);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Musor musor = sorokMusorraAlakitasa(rs);
				list.add(musor);
			}
			
			return list;
		}
		finally {
			close(stmt, rs);
		}
	}
	
	private Musor sorokMusorraAlakitasa(ResultSet rs) throws SQLException { //volt egy id getInt
		
		String cime = rs.getString("címe");
		String forgatasi_orszag= rs.getString("forgatási_ország");
		String kiadas_eve = rs.getString("kiadás_éve");
		int korhatar = rs.getInt("korhatár");
		String stilus = rs.getString("stílus");
		int idotartam = rs.getInt("idő_tartam");
		
		Musor musor = new Musor(cime, forgatasi_orszag, kiadas_eve, korhatar, stilus, idotartam);
		
		return musor;
	}

	
	private static void close(Connection conn, Statement stmt, ResultSet rs)
			throws SQLException {

		if (rs != null) {
			rs.close();
		}

		if (stmt != null) {
			
		}
		
		if (conn != null) {
			conn.close();
		}
	}

	private void close(Statement stmt, ResultSet rs) throws SQLException {
		close(null, stmt, rs);		
	}

	public static void main(String[] args) throws Exception {
		
		Musor_Muveletek dao = new Musor_Muveletek();
		System.out.println(dao.musorKereso("The"));
		System.out.println(dao.musorokListazasa());
	}
}
