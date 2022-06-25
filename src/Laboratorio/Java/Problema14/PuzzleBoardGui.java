package Laboratorio.Java.Problema14;

import puzzleboard.*;
public class PuzzleBoardGui {
public static void main(String[] args) {
	PuzzleBoard gui= new PuzzleBoard(4);
	Puzzle p= new Puzzle(4);
	gui = p.riempi(gui);
	
	

	while(!p.sorted()) {
		String k= ""+gui.get();
		int[] coord = p.trova(k);
		if(coord!=null) {
			p.move(coord[0], coord[1], gui);
		}
	}
	}
}
