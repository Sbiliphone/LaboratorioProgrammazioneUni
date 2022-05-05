package Problema13;

import queens.*;

public class QueensGUI {
/*
 * Applicando il package queens, scrivi un metodo statico che, data una lista di tipo SList<Board> relativa a scacchiere
   tutte della stessa dimensione, visualizzi in successione l�immagine di ciascuna scacchiera che fa parte della lista sulla
   stessa GUI. Utilizza infine questo programma per visualizzare tutte le soluzioni del rompicapo delle N regine per una
   data dimensione della scacchiera.
 */
	
	public static void main(String[] args) {
		showGUI(5);
		//queens.ChessboardView view = new queens.ChessboardView( 8 );
		//view.setQueens( " b1 e2 g3 d4 a5 h6 f7 c8 " );
	}
	
	public static void showGUI(int n) {
		ChessboardView gui = new ChessboardView(n); //crea la rappresentazione grafica di una scacchiera n x n
		SList<Board> board = Queens.listOfAllSolutions(n);
		for(int i = 0; i <= board.length(); i++) {
			if(board.listRef(i) == null) break; //quando finiscono le configurazioni termina
			gui.setQueens(board.listRef(i).arrangement());
			//visualizza la configurazione (disposizione di regine)
			//codificata dalla stringa attraverso coppie letteracifra separate da spazi bianchi
		}
		
	}
}
