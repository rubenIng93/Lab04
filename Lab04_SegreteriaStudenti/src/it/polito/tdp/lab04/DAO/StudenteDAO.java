package it.polito.tdp.lab04.DAO;


import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.lab04.model.*;

public class StudenteDAO {
	
	
	/**Data la matricola restituisce lo studente
	 * 
	 * @param matricola
	 * @return Studente s con tutti i dati se presente; altrimenti solo con matricola
	 */
	public Studente getStudenteByMatricola(int matricola) {
		
		Studente s = new Studente(matricola, "", "", "");
		
		
		
		try {
			String sql = "SELECT * FROM studente WHERE matricola = ? ";
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			while (rs.next()) { //parametri studente

				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("CDS");

				s.setCognome(cognome);
				s.setNome(nome);
				s.setCDS(cds);
			
			}
			conn.close();

			return s;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	public List<Studente> getIscrittiCorso(String nomeCorso){
			
		String sql = "SELECT * FROM studente WHERE matricola IN (SELECT matricola FROM iscrizione i, corso c WHERE c.codins = i.codins AND c.nome = ? )";
		List<Studente> iscritti = new ArrayList<Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nomeCorso);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) { //parametri studente
				
				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("CDS");
				
				Studente s = new Studente(matricola, cognome, nome,cds);
				iscritti.add(s);				
			
			}
			conn.close();
			
			return iscritti;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
