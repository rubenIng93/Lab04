package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;

public class Model {
	
	public List<Corso> getAllCorsi(){
		
		CorsoDAO dao = new CorsoDAO();
		
		List<Corso> corsi = dao.getTuttiICorsi();
		
		return corsi;
	}

}
