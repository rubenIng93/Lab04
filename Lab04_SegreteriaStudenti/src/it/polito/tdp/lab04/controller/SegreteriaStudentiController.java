package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.*;

import it.polito.tdp.lab04.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	
	private Model model = new Model();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> selettoreCorsi;

    @FXML
    private Button btnTrovaIscritti;

    @FXML
    private TextField txtMatricola;
    
    @FXML
    private Button btnCompletatmento;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;
    
    @FXML
    void doAutoCompletamento(ActionEvent event) {
    	
    	String matricola = txtMatricola.getText();
    	try {
    		int nMatricola = Integer.parseInt(matricola);
    		if(nMatricola > 999999 || nMatricola < 100000) {
    			txtResult.setText("Non hai inserito il numero correttamente");
    			txtMatricola.clear();
    			return;
    		}
    		
    		Studente s = model.getStudenteMatricola(nMatricola);
    		if(s.getCognome().compareTo("") == 0) {
    			txtResult.setText("Non esistono studenti con quella matricola");
    			txtMatricola.clear();
    			return;
    		}else {
    			txtNome.setText(s.getNome());
    			txtCognome.setText(s.getCognome());
    			txtResult.setText("Studente trovato");
    		}
    	}catch(NumberFormatException nfe) {
    		txtResult.setText("Non hai inserito il numero correttamente");
    		txtMatricola.clear();
    		return;
    	}
    	
    	

    }

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	
    	txtResult.clear();
    	String matricola = txtMatricola.getText();
    	
    	try {
    		int nMatricola = Integer.parseInt(matricola);
    		
    		if(nMatricola > 999999 || nMatricola < 100000) {
    			txtResult.setText("Non hai inserito il numero correttamente");
    			txtMatricola.clear();
    			return;
    		}
    		if(!model.esisteMatricola(nMatricola)) {
    			txtResult.setText("La matricola inserita non è presente nel database");
        		txtMatricola.clear();
        		return;
    		}
    		List<Corso> corsi = model.getCorsiDataLaMatricola(nMatricola);
    		for(Corso c : corsi) {
    			txtResult.appendText(c.stampaCompleta() + "\n");
    		}
    		
    		
    		
    	}catch(NumberFormatException nfe) {
    		txtResult.setText("Non hai inserito il numero correttamente");
    		txtMatricola.clear();
    		return;
    	}
    	
    	

    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	String corso = selettoreCorsi.getValue();
    	if(corso.compareTo("") == 0 || corso.compareTo("Corsi") == 0) {
    		txtResult.appendText("Seleziona un corso");
    	}else {
    		List<Studente> iscritti = model.getStudentiIscritti(corso);
    		for(Studente s : iscritti) {
    			txtResult.appendText(s.toString() + "\n");
    		}
    	}
    		

    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {
    	
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtResult.clear();

    }

    @FXML
    void initialize() {
    	assert btnCompletatmento != null : "fx:id=\"btnCompletatmento\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert selettoreCorsi != null : "fx:id=\"selettoreCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnTrovaIscritti != null : "fx:id=\"btnTrovaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	//selettoreCorsi.getItems().addAll(); 
    	selettoreCorsi.getItems().add("Corsi");
    	selettoreCorsi.getItems().add("");
    	for(Corso c : model.getAllCorsi()) {
    		selettoreCorsi.getItems().add(c.toString());
    	}
    	
    	
    }
}
