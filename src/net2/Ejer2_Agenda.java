package net2;

import java.util.HashMap;
import java.util.Map;

public class Ejer2_Agenda {
	private Map agenda = new HashMap();

	public void añadeTelefono(String nombre, String tfno) {
		agenda.put(nombre, tfno);
	}

	public String getTfno(String nombre) {
		return (String) agenda.get(nombre);
	}
}
