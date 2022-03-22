package org.rnieto.Ahorcado.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.SortedMap;

public class PartidaPOJO {
	
	
	private String palabra;
	
	private char[] palabraSeleccionada;
	
	private int numIntentos = 1;
	private int numFallos = 6;

	private final static String FICHERO_PALABRAS = "./src/main/resources/fich/palabrasDisponibles.txt";
	
	private HashMap<Integer,String> listaIteraImagen = new HashMap<Integer,String>();

	// Cada vez que se crea el objeto se le añade una palabra la cual es pasada a guiones
	public PartidaPOJO() throws IOException {

		palabra = eligePalabra();
		palabraSeleccionada = new char[palabra.length()];

		for (int i = 0; i < palabraSeleccionada.length; i++) {
			palabraSeleccionada[i] = '-';
		}
		
		listaIteraImagen.put(1, "uno");
		listaIteraImagen.put(2, "dos");
		listaIteraImagen.put(3, "tres");
		listaIteraImagen.put(4, "cuatro");
		listaIteraImagen.put(5, "cinco");
		listaIteraImagen.put(6, "seis");
		listaIteraImagen.put(7, "siete");
	}

	public String eligePalabra() throws IOException {
		
		//Creo un br con un fr para leer sobre el fichero
		BufferedReader br = new BufferedReader(new FileReader(FICHERO_PALABRAS));

		int linea_palabra = 0;
		
		//Itero con la variable anterior para saber el numero de lineas
		while ((palabra = br.readLine()) != null) {
			linea_palabra++;
		}
		
		//Numero aleatorio para la linea
		//Probé a hacer un Random con rango de linea_palabra y el nextInt usarlo abajo pero me da error :(
		linea_palabra = (int) (Math.random() * linea_palabra);
		br.close();

		br = new BufferedReader(new FileReader(FICHERO_PALABRAS));

		for (int i = 0; i < linea_palabra; i++) {
			palabra = br.readLine().toLowerCase();
		}

		return palabra;
	}

	//Comporobamso si la letra esta en la palbra
	public void compruebaPalabra(String letra) {
		
		boolean letraExiste = false;


		if (palabra.contains(letra)) {

			//Se usa una booleana por que si una palabra se pone x veces se sumara x a los intentos, y queremos de uno en uno
			for (int i = 0; i < palabraSeleccionada.length; i++) {
				
				if (palabra.charAt(i) == letra.charAt(0)) {
					palabraSeleccionada[i] = palabra.charAt(i);
					letraExiste = true;
				}
				
			}

		}
		
		if (letraExiste) {
			numIntentos++;

		} else {

			numIntentos++;
			numFallos--;
		}
	}
	

	public HashMap<Integer, String> getListaIteraImagen() {
		return listaIteraImagen;
	}

	public void setListaIteraImagen(HashMap<Integer, String> listaIteraImagen) {
		this.listaIteraImagen = listaIteraImagen;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public char[] getArrayPalabra() {
		return palabraSeleccionada;
	}

	public void setArrayPalabra(char[] arrayPalabra) {
		this.palabraSeleccionada = arrayPalabra;
	}

	public int getNumIntentos() {
		return numIntentos;
	}

	public void setNumIntentos(int numIntentos) {
		this.numIntentos = numIntentos;
	}

	public int getNumFallos() {
		return numFallos;
	}

	public void setNumFallos(int numFallos) {
		this.numFallos = numFallos;
	}
}
