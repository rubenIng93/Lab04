package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	public List<Corso> getAllCorsi(){
		
		CorsoDAO dao = new CorsoDAO();
		
		List<Corso> corsi = dao.getTuttiICorsi();
		
		return corsi;
	}
	
	public Studente getStudenteMatricola(int matricola) {
		
		StudenteDAO dao = new StudenteDAO();
		
		Studente s = dao.getStudenteByMatricola(matricola);
		
		return s;
	}

	public List<Studente> getStudentiIscritti(String corso) {
		
		StudenteDAO dao = new StudenteDAO();
		
		List<Studente> iscritti = dao.getIscrittiCorso(corso);
		
		return iscritti;
	}

}
