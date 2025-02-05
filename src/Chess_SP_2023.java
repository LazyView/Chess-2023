import java.util.ArrayList;

/**
 *  @Author Filip Chlad - A22B0060P.
 * Tato tride slouzi pro definovani pole sachovnice, tvorbu pohybu a vraceni pohybu.
 */
public class Chess_SP_2023 {
	/*
	 * Dvojrozmerne pole, ktere predstavuje sachovnici.
	 * Figurky = WHITE/black
	 * Pesak = P/p
	 * Kun = K/k
	 * Strelec = B/b
	 * Vez = R/r
	 * Kralovna = Q/q
	 * Kral = A/a
	 */
	static String[][] chessBoard = {
			{"r", "k", "b", "q", "a", "b", "k", "r"},
			{"p", "p", "p", "p", "p", "p", "p", "p"},
			{" ", " ", " ", " ", " ", " ", " ", " "},
			{" ", " ", " ", " ", " ", " ", " ", " "},
			{" ", " ", " ", " ", " ", " ", " ", " "},
			{" ", " ", " ", " ", " ", " ", " ", " "},
			{"P", "P", "P", "P", "P", "P", "P", "P"},
			{"R", "K", "B", "Q", "A", "B", "K", "R"}};

	static boolean[][] chessBoard1 = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false}};
	static int kingPositionC, kingPositionL;
	static boolean pawn = false;
	static ArrayList<String> moves = new ArrayList<>();
	static boolean PCplay = false;
	static String lastMove;
	/**
	 * Metoda, pomoci ktere se vykona tah.
	 *
	 * @param move - format x1y1x2y2C... vykona pohyb z pozice x1,y1 na pozici x2,y2, a napise, kterou figurku sebere.
	 */
	public static void makeMove(String move) {
		moves.add(move);
		lastMove = move;
		//Urcuje, ktery hrac zrovna hraje
		String[] temp = new String[]{"A", "R", "Q", "B", "K", "P"};
		String found = chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))];
		isContainsWords(temp, found, PiecesMovement.player);
			try {
				//klasicky tah
				if ((!"R".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]) || !"A".equals(chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))])) && (!"r".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]) || !"a".equals(chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))]))) {
					chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))] = chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))];
					chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = " ";
					if ("A".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))])) {
						kingPositionC = 8 * Character.getNumericValue(move.charAt(2)) + Character.getNumericValue(move.charAt(3));
					} else if ("a".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))])) {
						kingPositionL = 8 * Character.getNumericValue(move.charAt(2)) + Character.getNumericValue(move.charAt(3));
					}
				}
			} catch (Exception ignored) {
			}
			try {
				//rosada pro bileho
				if ("R".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]) && "A".equals(chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))])) {
					//rosada prave veze
					if (move.charAt(3) == '7' && !PiecesMovement.rook2Cmoved) {
						chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = " ";
						chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))] = " ";
						chessBoard[7][6] = "A";
						chessBoard[7][5] = "R";
						kingPositionC = 8 * 7 + 6;
						PiecesMovement.kingCmoved = true;
					}
					//rosada leve veze
					else if (move.charAt(3) == '0' && !PiecesMovement.rook1Cmoved) {
						chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = " ";
						chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))] = " ";
						chessBoard[7][2] = "A";
						chessBoard[7][3] = "R";
						kingPositionC = 8 * 7 + 2;
						PiecesMovement.kingCmoved = true;
					}
				}
				//rosada pro cerneho hrace
				else if ("r".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]) && "a".equals(chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))])) {
					//rosada prave veze
					if (move.charAt(3) == '7' && !PiecesMovement.rook2Lmoved) {
						chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = " ";
						chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))] = " ";
						chessBoard[0][6] = "a";
						chessBoard[0][5] = "r";
						kingPositionL = 6;
						PiecesMovement.kingLmoved = true;
					}
					//rosada leve veze
					else if (move.charAt(3) == '0' && !PiecesMovement.rook1Lmoved) {
						chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = " ";
						chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))] = " ";
						chessBoard[0][2] = "a";
						chessBoard[0][3] = "r";
						kingPositionL = 2;
						PiecesMovement.kingLmoved = true;
					}
				}
			} catch (Exception ignored) {
			}


			//kontrola zda se vez bileho hrace pohla
			if ("R".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))])) {
				//prava
				if (move.charAt(1) == '7') {
					PiecesMovement.rook2Cmoved = true;
				}
				//leva
				else if (move.charAt(1) == '0') {
					PiecesMovement.rook1Cmoved = true;
				}
			}
			//kontrola zda se vez cerneho hrace pohla
			if ("r".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))])) {
				//leva
				if (move.charAt(1) == '7') {
					PiecesMovement.rook2Lmoved = true;
				}
				//prava
				else if (move.charAt(1) == '0') {
					PiecesMovement.rook1Lmoved = true;
				}
			}


			//kontrola zda se kral bileho hrace pohnul
			if ("A".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))])) {
				PiecesMovement.kingCmoved = true;
			}
			//kontrola zda se kral cerneho hrace pohnul
			if ("a".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))])) {
				PiecesMovement.kingLmoved = true;
			}

			try {
				//Pawn promotion
				for (int i = 0; i < 8; i++) {
					if (("P".equals(Chess_SP_2023.chessBoard[0][i]))) {
						//if pawn promotion
						pawn = true;
						chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = " ";
						chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))] = "Q";
					}
					if (("p".equals(Chess_SP_2023.chessBoard[7][i]))) {
						//if pawn promotion
						pawn = true;
						chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = " ";
						chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))] = "q";
					}
				}
			} catch (Exception ignored) {
			}
			for (int l = 0; l < 8; l++) {
				if (move.charAt(0) == '1' && "p".equals(chessBoard[Character.getNumericValue(move.charAt(2))][l])) {
					PiecesMovement.pawnLmove[l] = true;
				} else {
					PiecesMovement.pawnLmove[l] = false;
				}
				//en passant
//			try {
//				for (int k = -1; k < 2; k = k + 2) {
//					if ("P".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]) && PiecesMovement.pawnLmove[l] && " ".equals(chessBoard[Character.getNumericValue(move.charAt(2)) - 1][Character.getNumericValue(move.charAt(3))]) && Character.getNumericValue(move.charAt(3)) == l) {
//						System.out.println("tudy proslo");
//						chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = " ";
//						chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))] = " ";
//						chessBoard[Character.getNumericValue(move.charAt(2) - 1)][Character.getNumericValue(move.charAt(3))] = "P";
//					}
//				}
//			} catch (Exception ignored) {
//			}

		}
	}
		public static void undoMove (String move){
			move = moves.get(moves.size() - 1);
			Sachovnice_a_sachy.forMoves.add(moves.get(moves.size() - 1));
			moves.remove(moves.size() - 1);
			try {
				//rosada pro bileho
				if (" ".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]) && " ".equals(chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))]) && ("A".equals(chessBoard[7][2]) && "R".equals(chessBoard[7][3])) && PiecesMovement.kingCmoved) {
					//rosada leve veze
					if (move.charAt(3) == '0') {
						chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = "A";
						chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))] = "R";
						chessBoard[7][2] = " ";
						chessBoard[7][3] = " ";
						kingPositionC = 8 * Character.getNumericValue(move.charAt(0)) + Character.getNumericValue(move.charAt(1));
						PiecesMovement.kingCmoved = false;
						PiecesMovement.rook1Cmoved = false;
					}
				} else if (" ".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]) && " ".equals(chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))]) && ("A".equals(chessBoard[7][6]) && "R".equals(chessBoard[7][5])) && PiecesMovement.kingCmoved) {
					//rosada prave veze
					if (move.charAt(3) == '7') {
						chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = "A";
						chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))] = "R";
						chessBoard[7][6] = " ";
						chessBoard[7][5] = " ";
						kingPositionC = 8 * Character.getNumericValue(move.charAt(0)) + Character.getNumericValue(move.charAt(1));
						PiecesMovement.kingCmoved = false;
						PiecesMovement.rook2Cmoved = false;
					}


					//rosada pro cerneho hrace
				} else if (" ".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]) && " ".equals(chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))]) && "a".equals(chessBoard[0][6]) && "r".equals(chessBoard[0][5])) {
					//rosada prave veze
					if (move.charAt(3) == '7' && !PiecesMovement.rook2Lmoved) {
						chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = "a";
						chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))] = "r";
						chessBoard[0][6] = " ";
						chessBoard[0][5] = " ";
						kingPositionL = 8 * Character.getNumericValue(move.charAt(0)) + Character.getNumericValue(move.charAt(1));
						PiecesMovement.kingLmoved = false;
						PiecesMovement.rook2Lmoved = false;
					}
				}else if (" ".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]) && " ".equals(chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))]) && "a".equals(chessBoard[0][2]) && "r".equals(chessBoard[0][3])) {
					//rosada leve veze
					if (move.charAt(3) == '0') {
						chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = "a";
						chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))] = "r";
						chessBoard[0][2] = " ";
						chessBoard[0][3] = " ";
						kingPositionL = 8 * Character.getNumericValue(move.charAt(0)) + Character.getNumericValue(move.charAt(1));
						PiecesMovement.kingLmoved = false;
						PiecesMovement.rook1Lmoved = false;
					}
					//Pawn promotion
				} else if("Q".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]) && pawn){
					chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = "P";
					chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))] = String.valueOf(move.charAt(4));
					pawn = false;
				}else if("q".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]) && pawn){
					chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = "p";
					chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))] = String.valueOf(move.charAt(4));
					pawn = false;
				}else {
					chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))];
					chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))] = String.valueOf(move.charAt(4));
					if ("A".equals(chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))])) {
						kingPositionC = 8 * Character.getNumericValue(move.charAt(0)) + Character.getNumericValue(move.charAt(1));
					} else if ("a".equals(chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))])) {
						kingPositionL = 8 * Character.getNumericValue(move.charAt(1)) + Character.getNumericValue(move.charAt(0));
					}
				}
			}catch (Exception ignored) {}

			//kontrola zda se vez bileho hrace pohla
			if ("R".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))])) {
				//prava
				if (move.charAt(1) == '7') {
					PiecesMovement.rook2Cmoved = false;
				}
				//leva
				else if (move.charAt(1) == '0') {
					PiecesMovement.rook1Cmoved = false;
				}
			}
			//kontrola zda se vez cerneho hrace pohla
			if ("r".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))])) {
				//leva
				if (move.charAt(1) == '7') {
					PiecesMovement.rook2Lmoved = false;
				}
				//prava
				else if (move.charAt(1) == '0') {
					PiecesMovement.rook1Lmoved = false;
				}
			}


			//kontrola zda se kral bileho hrace pohnul
			if ("A".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))])) {
				PiecesMovement.kingCmoved = false;
			}
			//kontrola zda se kral cerneho hrace pohnul
			if ("a".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))])) {
				PiecesMovement.kingLmoved = false;
			}


			for (int l = 0; l < 8; l++) {
				if (move.charAt(0) == '1' && "p".equals(chessBoard[Character.getNumericValue(move.charAt(2))][l])) {
					PiecesMovement.pawnLmove[l] = false;
				} else {
					PiecesMovement.pawnLmove[l] = true;
				}
				//en passant
//				try {
//					for (int k = -1; k < 2; k = k + 2) {
//						if ("p".equals(chessBoard[Character.getNumericValue(move.charAt(2))][l]) && "P".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]) && PiecesMovement.pawnLmove[l]) {
//							chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = " ";
//							chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))] = " ";
//							chessBoard[Character.getNumericValue(move.charAt(2) - 1)][Character.getNumericValue(move.charAt(3) + k)] = "P";
//						}
//					}
//				} catch (Exception ignored) {
//				}
			}
	}

	public static boolean  isContainsWords(String[] words,String Target, boolean player){
		PiecesMovement.player = player;
		for(String buf : words){
			if(buf.equals(Target))
					return PiecesMovement.player = false;
		}
			return PiecesMovement.player = true;
	}
	public static boolean UIturnM(boolean player){
		boolean UIturn;
		if(player){
			UIturn = false;
		}else{
			UIturn = true;
		}
	return UIturn;
	}
}


