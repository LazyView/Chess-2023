import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.util.ArrayList;

/**
 * Tato trida slouzi pro naprogramovani pohybo pro jednotlive figurky, a pro vyhodnoceni, ktere mozne pohyby ma hrac k dispozici.
 */
public class PiecesMovement extends Chess_SP_2023 {
    public static boolean player = true;
    static boolean kingCmoved = false;
    static boolean kingLmoved = false;
    static boolean rook1Cmoved = false;
    static boolean rook2Cmoved = false;
    static boolean rook1Lmoved = false;
    static boolean rook2Lmoved = false;
    static boolean[] pawnLmove = new boolean[]{false, false, false, false, false, false, false, false};
    static  StringBuilder list1 = new StringBuilder();
    static StringBuilder listUI = new StringBuilder();

    /**
     * Metoda projizdi sachovnici a vyhodnocuje mozne pohyby.
     *
     * @return Vraci vsechny mozne tahy pro jednotlive figurky.
     */
    public static String possibleMoves() {
        StringBuilder list = new StringBuilder(" ");
        list1 = list;
        if(!player){
            listUI = list;
        }
        for (int i = 0; i < 64; i++) {
                switch (chessBoard[i / 8][i % 8]) {
                    case "P" -> {
                        if(player) {
                            list.append(possibleP(i));
                        }
                    }
                    case "R" -> {
                        if(player) {
                            list.append(possibleR(i));
                        }
                    }
                    case "K" -> {
                        if (player) {
                            list.append(possibleK(i));
                        }
                    }
                    case "B" -> {
                        if (player) {
                            list.append(possibleB(i));
                        }
                    }
                    case "Q" -> {
                        if (player) {
                            list.append(possibleQ(i));
                        }
                    }
                    case "A" -> {
                        if (player) {
                            list.append(possibleA(i));
                        }
                    }
                    case "p" -> {
                        if (!player) {
                            list.append(possiblep(i));
                        }
                    }
                    case "r" -> {
                        if (!player) {
                            list.append(possibler(i));
                        }
                    }
                    case "k" -> {
                        if (!player) {
                            list.append(possiblek(i));
                        }
                    }
                    case "b" -> {
                        if (!player) {
                            list.append(possibleb(i));
                        }
                    }
                    case "q" -> {
                        if (!player) {
                            list.append(possibleq(i));
                        }
                    }
                    case "a" -> {
                        if (!player) {
                            list.append(possiblea(i));
                        }
                    }
                }
        }
        return list.toString();
    }

    /**
     * Pomoci teto metody se vytvari mozne pohyby pro bile pesaky.
     *
     * @param i projizdi jednoliva policka sachovnice.
     * @return vraci String pohybu ve formatu x1,y1,x2,y2,C - x1,y1 pocatecni pozice.. x2,y2 koncova pozice... C figurka, ktera bude sebrana.
     */
    public static String possibleP(int i) {
        StringBuilder list = new StringBuilder(new StringBuilder(new StringBuilder()));
        String oldPiece;
        int r = i / 8, c = i % 8;
        for (int j = -1; j <= 1; j += 2) {
            try {//brani
                if (Character.isLowerCase(chessBoard[r - 1][c + j].charAt(0)) && i >= 0) {
                    oldPiece = chessBoard[r - 1][c + j];
                    chessBoard[r][c] = " ";
                    chessBoard[r - 1][c + j] = "P";
                    if (kingSafeC()) {
                        list.append(r).append(c).append(r - 1).append(c + j).append(oldPiece);
                    }
                    chessBoard[r][c] = "P";
                    chessBoard[r - 1][c + j] = oldPiece;
                }
            } catch (Exception ignored) {
            }
            try {//pohyb o 1
                if (" ".equals(chessBoard[r - 1][c]) && i >= 0) {
                    oldPiece = chessBoard[r - 1][c];
                    chessBoard[r][c] = " ";
                    chessBoard[r - 1][c] = "P";
                    if (kingSafeC()) {
                        list.append(r).append(c).append(r - 1).append(c).append(oldPiece);
                    }
                    chessBoard[r][c] = "P";
                    chessBoard[r - 1][c] = oldPiece;
                }
            } catch (Exception ignored) {
            }
            try {//pohyb o 2 nahoru
                if (" ".equals(chessBoard[r - 1][c]) && " ".equals(chessBoard[r - 2][c]) && i >= 48) {
                    oldPiece = chessBoard[r - 2][c];
                    chessBoard[r][c] = " ";
                    chessBoard[r - 2][c] = "P";
                    if (kingSafeC()) {
                        list.append(r).append(c).append(r - 2).append(c).append(oldPiece);
                    }
                    chessBoard[r][c] = "P";
                    chessBoard[r - 2][c] = oldPiece;
                }
            } catch (Exception ignored) {
            }
            try {//en passant
                for (int k = -1; k < 2; k = k + 2) {
                    for (int f = 0; f < 8; f++) {
                        if ("p".equals(chessBoard[r][c + k]) && pawnLmove[f] && c+k == f) {
                            oldPiece = chessBoard[r][c + k];
                            chessBoard[r][c] = " ";
                            chessBoard[r - 1][c + k] = "P";
                            if (kingSafeC()) {
                                list.append(r).append(c).append(r).append(c + k).append(oldPiece);
                            }
                            chessBoard[r - 1][c + k] = " ";
                            chessBoard[r][c] = "P";
                            chessBoard[r][c + k] = oldPiece;
                        }
                    }
                }
            }catch (Exception ignored){}
        }
        return list.toString();
    }

    /**
     * Pomoci teto metody se vytvari mozne pohyby pro bile veze.
     *
     * @param i projizdi jednoliva policka sachovnice.
     * @return vraci String pohybu ve formatu x1,y1,x2,y2,C - x1,y1 pocatecni pozice.. x2,y2 koncova pozice... C figurka, ktera bude sebrana.
     */
    public static String possibleR(int i) {
        StringBuilder list = new StringBuilder();
        String oldPiece;
        int r = i / 8, c = i % 8;
        int temp = 1;
        for (int j = -1; j <= 1; j += 2) {
            try {
                while (" ".equals(chessBoard[r][c + temp * j])) {
                    oldPiece = chessBoard[r][c + temp * j];
                    chessBoard[r][c] = " ";
                    chessBoard[r][c + temp * j] = "R";
                    if (kingSafeC()) {
                        list.append(r).append(c).append(r).append(c + temp * j).append(oldPiece);
                    }
                    chessBoard[r][c] = "R";
                    chessBoard[r][c + temp * j] = oldPiece;
                    temp++;
                }
                if (Character.isLowerCase(chessBoard[r][c + temp * j].charAt(0))) {
                    oldPiece = chessBoard[r][c + temp * j];
                    chessBoard[r][c] = " ";
                    chessBoard[r][c + temp * j] = "R";
                    if (kingSafeC()) {
                        list.append(r).append(c).append(r).append(c + temp * j).append(oldPiece);
                    }
                    chessBoard[r][c] = "R";
                    chessBoard[r][c + temp * j] = oldPiece;
                }
            } catch (Exception ignored) {
            }
            temp = 1;
            try {
                while (" ".equals(chessBoard[r + temp * j][c])) {
                    oldPiece = chessBoard[r + temp * j][c];
                    chessBoard[r][c] = " ";
                    chessBoard[r + temp * j][c] = "R";
                    if (kingSafeC()) {
                        list.append(r).append(c).append(r + temp * j).append(c).append(oldPiece);
                    }
                    chessBoard[r][c] = "R";
                    chessBoard[r + temp * j][c] = oldPiece;
                    temp++;
                }
                if (Character.isLowerCase(chessBoard[r + temp * j][c].charAt(0))) {
                    oldPiece = chessBoard[r + temp * j][c];
                    chessBoard[r][c] = " ";
                    chessBoard[r + temp * j][c] = "R";
                    if (kingSafeC()) {
                        list.append(r).append(c).append(r + temp * j).append(c).append(oldPiece);
                    }
                    chessBoard[r][c] = "R";
                    chessBoard[r + temp * j][c] = oldPiece;
                }
            } catch (Exception ignored) {
            }
            temp = 1;

        }
        return list.toString();
    }

    /**
     * Pomoci teto metody se vytvari mozne pohyby pro bile kone.
     *
     * @param i projizdi jednoliva policka sachovnice.
     * @return vraci String pohybu ve formatu x1,y1,x2,y2,C - x1,y1 pocatecni pozice.. x2,y2 koncova pozice... C figurka, ktera bude sebrana.
     */
    public static String possibleK(int i) {
        StringBuilder list = new StringBuilder();
        String oldPiece;
        int r = i / 8, c = i % 8;
        for (int j = -1; j <= 1; j += 2) {
            for (int k = -1; k <= 1; k += 2) {
                try {
                    if (Character.isLowerCase(chessBoard[r + j][c + k * 2].charAt(0)) || " ".equals(chessBoard[r + j][c + k * 2])) {
                        oldPiece = chessBoard[r + j][c + k * 2];
                        chessBoard[r][c] = " ";
                        if (kingSafeC()) {
                            list.append(r).append(c).append(r + j).append(c + k * 2).append(oldPiece);
                        }
                        chessBoard[r][c] = "K";
                        chessBoard[r + j][c + k * 2] = oldPiece;
                    }
                } catch (Exception ignored) {
                }
                try {
                    if (Character.isLowerCase(chessBoard[r + j * 2][c + k].charAt(0)) || " ".equals(chessBoard[r + j * 2][c + k])) {
                        oldPiece = chessBoard[r + j * 2][c + k];
                        chessBoard[r][c] = " ";
                        if (kingSafeC()) {
                            list.append(r).append(c).append(r + j * 2).append(c + k).append(oldPiece);
                        }
                        chessBoard[r][c] = "K";
                        chessBoard[r + j * 2][c + k] = oldPiece;
                    }
                } catch (Exception ignored) {
                }
            }
        }
        return list.toString();
    }

    /**
     * Pomoci teto metody se vytvari mozne pohyby pro bile strelce.
     *
     * @param i projizdi jednoliva policka sachovnice.
     * @return vraci String pohybu ve formatu x1,y1,x2,y2,C - x1,y1 pocatecni pozice.. x2,y2 koncova pozice... C figurka, ktera bude sebrana.
     */
    public static String possibleB(int i) {
        StringBuilder list = new StringBuilder();
        String oldPiece;
        int r = i / 8, c = i % 8;
        int temp = 1;
        for (int j = -1; j <= 1; j += 2) {
            for (int k = -1; k <= 1; k += 2) {
                try {
                    while (" ".equals(chessBoard[r + temp * j][c + temp * k])) {
                        oldPiece = chessBoard[r + temp * j][c + temp * k];
                        chessBoard[r][c] = " ";
                        chessBoard[r + temp * j][c + temp * k] = "B";
                        if (kingSafeC()) {
                            list.append(r).append(c).append(r + temp * j).append(c + temp * k).append(oldPiece);
                        }
                        chessBoard[r][c] = "B";
                        chessBoard[r + temp * j][c + temp * k] = oldPiece;
                        temp++;
                    }
                    if (Character.isLowerCase(chessBoard[r + temp * j][c + temp * k].charAt(0))) {
                        oldPiece = chessBoard[r + temp * j][c + temp * k];
                        chessBoard[r][c] = " ";
                        chessBoard[r + temp * j][c + temp * k] = "B";
                        if (kingSafeC()) {
                            list.append(r).append(c).append(r + temp * j).append(c + temp * k).append(oldPiece);
                        }
                        chessBoard[r][c] = "B";
                        chessBoard[r + temp * j][c + temp * k] = oldPiece;
                    }
                } catch (Exception ignored) {
                }
                temp = 1;
            }
        }
        return list.toString();
    }

    /**
     * Pomoci teto metody se vytvari mozne pohyby pro bilou kralovnu.
     *
     * @param i projizdi jednoliva policka sachovnice.
     * @return vraci String pohybu ve formatu x1,y1,x2,y2,C - x1,y1 pocatecni pozice.. x2,y2 koncova pozice... C figurka, ktera bude sebrana.
     */
    public static String possibleQ(int i) {
        StringBuilder list = new StringBuilder();
        String oldPiece;
        int r = i / 8, c = i % 8;
        int temp = 1;
        for (int j = -1; j <= 1; j++) {
            for (int k = -1; k <= 1; k++) {
                if (j != 0 || k != 0) {
                    try {
                        while (" ".equals(chessBoard[r + temp * j][c + temp * k])) {
                            oldPiece = chessBoard[r + temp * j][c + temp * k];
                            chessBoard[r][c] = " ";
                            chessBoard[r + temp * j][c + temp * k] = "Q";
                            if (kingSafeC()) {
                                list.append(r).append(c).append(r + temp * j).append(c + temp * k).append(oldPiece);
                            }
                            chessBoard[r][c] = "Q";
                            chessBoard[r + temp * j][c + temp * k] = oldPiece;
                            temp++;
                        }
                        if (Character.isLowerCase(chessBoard[r + temp * j][c + temp * k].charAt(0))) {
                            oldPiece = chessBoard[r + temp * j][c + temp * k];
                            chessBoard[r][c] = " ";
                            chessBoard[r + temp * j][c + temp * k] = "Q";
                            if (kingSafeC()) {
                                list.append(r).append(c).append(r + temp * j).append(c + temp * k).append(oldPiece);
                            }
                            chessBoard[r][c] = "Q";
                            chessBoard[r + temp * j][c + temp * k] = oldPiece;
                        }
                    } catch (Exception ignored) {
                    }
                    temp = 1;
                }
            }
        }
        return list.toString();
    }

    /**
     * Pomoci teto metody se vytvari mozne pohyby pro bileho krale.
     *
     * @param i projizdi jednoliva policka sachovnice.
     * @return vraci String pohybu ve formatu x1,y1,x2,y2,C - x1,y1 pocatecni pozice.. x2,y2 koncova pozice... C figurka, ktera bude sebrana.
     */
    public static String possibleA(int i) {
        StringBuilder list = new StringBuilder();
        String oldPiece;
        int temp = 1;
        int r = i / 8, c = i % 8;
        for (int j = 0; j < 9; j++) {
            if (j != 4) {
                try {
                    if (Character.isLowerCase(chessBoard[r - 1 + j / 3][c - 1 + j % 3].charAt(0)) || " ".equals(chessBoard[r - 1 + j / 3][c - 1 + j % 3])) {
                        oldPiece = chessBoard[r - 1 + j / 3][c - 1 + j % 3];
                        chessBoard[r][c] = " ";
                        chessBoard[r - 1 + j / 3][c - 1 + j % 3] = "A";
                        int kingTemp = kingPositionC;
                        kingPositionC = i + (j / 3) * 8 + j % 3 - 9;

                        //column1,column2,captured-piece,new-piece,P
                        if (kingSafeC ()) {
                            list.append(r).append(c).append(r-1+j/3).append(c-1+j%3).append(oldPiece);
                        }
                        chessBoard[r][c] = "A";
                        chessBoard[r - 1 + j / 3][c - 1 + j % 3] = oldPiece;
                        kingPositionC = kingTemp;
                    }
                } catch (Exception ignored) {
                }
            }   //rosada
            for (int k = -1; k <= 1; k += 2) {
                try {
                    if (k == -1) {
                        while (" ".equals(chessBoard[r][c + k * temp])) {
                            temp++;
                        }
                        if ("R".equals(chessBoard[r][c + temp * k]) && !kingCmoved && (c + temp * k) == 0) {

                            oldPiece = chessBoard[r][c + temp * k];
                            chessBoard[r][c + temp * k] = "A";
                            chessBoard[r][c] = " ";
                            if (kingSafeC()) {
                                list.append(r).append(c).append(r).append(c + temp * k).append(oldPiece);
                            }
                            chessBoard[r][c] = "A";
                            chessBoard[r][c + temp * k] = oldPiece;
                        }
                    }
                } catch (Exception ignored) {
                }
                temp = 1;
                    try {
                        if (k == 1) {
                            while (" ".equals(chessBoard[r][c + k * temp])) {
                                temp++;
                            }
                            if ("R".equals(chessBoard[r][c + temp * k]) && !kingCmoved && (c + temp * k) == 7) {

                                oldPiece = chessBoard[r][c + temp * k];
                                chessBoard[r][c + temp * k] = "A";
                                chessBoard[r][c] = " ";
                                if (kingSafeC()) {
                                    list.append(r).append(c).append(r).append(c + temp * k).append(oldPiece);
                                }
                                chessBoard[r][c] = "A";
                                chessBoard[r][c + temp * k] = oldPiece;
                            }
                        }
                    } catch (Exception ignored) {
                    }
                    temp = 1;
                }
        }return list.toString();
    }

    /**
     * Pomoci teto metody se vytvari mozne pohyby pro cerne pesaky.
     *
     * @param i projizdi jednoliva policka sachovnice.
     * @return vraci String pohybu ve formatu x1,y1,x2,y2,C - x1,y1 pocatecni pozice.. x2,y2 koncova pozice... C figurka, ktera bude sebrana.
     */
    public static String possiblep(int i) {
        StringBuilder list= new StringBuilder();
        String oldPiece;
        int r=i/8, c=i%8;
            for (int j = -1; j <= 1; j += 2) {
                try {//capture
                    if (Character.isUpperCase(chessBoard[r + 1][c + j].charAt(0)) && i <= 64) {
                        oldPiece = chessBoard[r + 1][c + j];
                        chessBoard[r][c] = " ";
                        chessBoard[r + 1][c + j] = "p";
                        if (kingSafeL()) {
                            list.append(r).append(c).append(r + 1).append(c + j).append(oldPiece);
                        }
                        chessBoard[r][c] = "p";
                        chessBoard[r + 1][c + j] = oldPiece;
                    }
                } catch (Exception ignored) {
                }
            }
            try {//move one up
                if (" ".equals(chessBoard[r + 1][c]) && i <= 64) {
                    oldPiece = chessBoard[r + 1][c];
                    chessBoard[r][c] = " ";
                    chessBoard[r + 1][c] = "p";
                    if (kingSafeL()) {
                        list.append(r).append(c).append(r + 1).append(c).append(oldPiece);
                    }
                    chessBoard[r][c] = "p";
                    chessBoard[r + 1][c] = oldPiece;
                }
            } catch (Exception ignored) {
            }
            try {//move two up
                if (" ".equals(chessBoard[r + 1][c]) && " ".equals(chessBoard[r + 2][c]) && i <= 16) {
                    oldPiece = chessBoard[r + 2][c];
                    chessBoard[r][c] = " ";
                    chessBoard[r + 2][c] = "p";
                    if (kingSafeL()) {
                        list.append(r).append(c).append(r + 2).append(c).append(oldPiece);
                    }
                    chessBoard[r][c] = "p";
                    chessBoard[r + 2][c] = oldPiece;
                }
            } catch (Exception ignored) {
            }
        try {//en passant
            for (int k = -1; k < 2; k = k + 2) {
                for (int f = 0; f < 8; f++) {
                    if ("P".equals(chessBoard[r][c + k]) && pawnLmove[f] && c+k == f) {
                        oldPiece = chessBoard[r][c + k];
                        chessBoard[r][c] = " ";
                        chessBoard[r + 1][c + k] = "p";
                        if (kingSafeC()) {
                            list.append(r).append(c).append(r).append(c + k).append(oldPiece);
                        }
                        chessBoard[r + 1][c + k] = " ";
                        chessBoard[r][c] = "p";
                        chessBoard[r][c + k] = oldPiece;
                    }
                }
            }
        }catch (Exception ignored){}
        return list.toString();
    }


    /**
     * Pomoci teto metody se vytvari mozne pohyby pro cerne veze.
     *
     * @param i projizdi jednoliva policka sachovnice.
     * @return vraci String pohybu ve formatu x1,y1,x2,y2,C - x1,y1 pocatecni pozice.. x2,y2 koncova pozice... C figurka, ktera bude sebrana.
     */
    public static String possibler(int i) {
        StringBuilder list = new StringBuilder();
        String oldPiece;
        int r = i / 8, c = i % 8;
        int temp = 1;
        for (int j = -1; j <= 1; j += 2) {
            try {
                while (" ".equals(chessBoard[r][c + temp * j])) {
                    oldPiece = chessBoard[r][c + temp * j];
                    chessBoard[r][c] = " ";
                    chessBoard[r][c + temp * j] = "r";
                    if(kingSafeL()) {
                        list.append(r).append(c).append(r).append(c + temp * j).append(oldPiece);
                    }
                    chessBoard[r][c] = "r";
                    chessBoard[r][c + temp * j] = oldPiece;
                    temp++;
                }
                if (Character.isUpperCase(chessBoard[r][c + temp * j].charAt(0))) {
                    oldPiece = chessBoard[r][c + temp * j];
                    chessBoard[r][c] = " ";
                    chessBoard[r][c + temp * j] = "r";
                    if(kingSafeL()) {
                        list.append(r).append(c).append(r).append(c + temp * j).append(oldPiece);
                    }
                    chessBoard[r][c] = "r";
                    chessBoard[r][c + temp * j] = oldPiece;
                }
            } catch (Exception ignored) {
            }
            temp = 1;
            try {
                while (" ".equals(chessBoard[r + temp * j][c])) {
                    oldPiece = chessBoard[r + temp * j][c];
                    chessBoard[r][c] = " ";
                    chessBoard[r + temp * j][c] = "r";
                    if(kingSafeL()) {
                        list.append(r).append(c).append(r + temp * j).append(c).append(oldPiece);
                    }
                    chessBoard[r][c] = "r";
                    chessBoard[r + temp * j][c] = oldPiece;
                    temp++;
                }
                if (Character.isUpperCase(chessBoard[r + temp * j][c].charAt(0))) {
                    oldPiece = chessBoard[r + temp * j][c];
                    chessBoard[r][c] = " ";
                    chessBoard[r + temp * j][c] = "r";
                    if(kingSafeL()) {
                        list.append(r).append(c).append(r + temp * j).append(c).append(oldPiece);
                    }
                    chessBoard[r][c] = "r";
                    chessBoard[r + temp * j][c] = oldPiece;
                }
            } catch (Exception ignored) {
            }
            temp = 1;
        }
        return list.toString();
    }

    /**
     * Pomoci teto metody se vytvari mozne pohyby pro cerne kone.
     *
     * @param i projizdi jednoliva policka sachovnice.
     * @return vraci String pohybu ve formatu x1,y1,x2,y2,C - x1,y1 pocatecni pozice.. x2,y2 koncova pozice... C figurka, ktera bude sebrana.
     */
    public static String possiblek(int i) {
        StringBuilder list = new StringBuilder();
        String oldPiece;
        int r = i / 8, c = i % 8;
        for (int j = -1; j <= 1; j += 2) {
            for (int k = -1; k <= 1; k += 2) {
                try {
                    if (Character.isUpperCase(chessBoard[r + j][c + k * 2].charAt(0)) || " ".equals(chessBoard[r + j][c + k * 2])) {
                        oldPiece = chessBoard[r + j][c + k * 2];
                        chessBoard[r][c] = " ";
                        if(kingSafeL()) {
                            list.append(r).append(c).append(r + j).append(c + k * 2).append(oldPiece);
                        }
                        chessBoard[r][c] = "k";
                        chessBoard[r + j][c + k * 2] = oldPiece;
                    }
                } catch (Exception ignored) {
                }
                try {
                    if (Character.isUpperCase(chessBoard[r + j * 2][c + k].charAt(0)) || " ".equals(chessBoard[r + j * 2][c + k])) {
                        oldPiece = chessBoard[r + j * 2][c + k];
                        chessBoard[r][c] = " ";
                        if(kingSafeL()) {
                            list.append(r).append(c).append(r + j * 2).append(c + k).append(oldPiece);
                        }
                        chessBoard[r][c] = "k";
                        chessBoard[r + j * 2][c + k] = oldPiece;
                    }
                } catch (Exception ignored) {
                }
            }
        }
        return list.toString();
    }

    /**
     * Pomoci teto metody se vytvari mozne pohyby pro cerne strelce.
     *
     * @param i projizdi jednoliva policka sachovnice.
     * @return vraci String pohybu ve formatu x1,y1,x2,y2,C - x1,y1 pocatecni pozice.. x2,y2 koncova pozice... C figurka, ktera bude sebrana.
     */
    public static String possibleb(int i) {
        StringBuilder list = new StringBuilder();
        String oldPiece;
        int r = i / 8, c = i % 8;
        int temp = 1;
        for (int j = -1; j <= 1; j += 2) {
            for (int k = -1; k <= 1; k += 2) {
                try {
                    while (" ".equals(chessBoard[r + temp * j][c + temp * k])) {
                        oldPiece = chessBoard[r + temp * j][c + temp * k];
                        chessBoard[r][c] = " ";
                        chessBoard[r + temp * j][c + temp * k] = "b";
                        if(kingSafeL()) {
                            list.append(r).append(c).append(r + temp * j).append(c + temp * k).append(oldPiece);
                        }
                        chessBoard[r][c] = "b";
                        chessBoard[r + temp * j][c + temp * k] = oldPiece;
                        temp++;
                    }
                    if (Character.isUpperCase(chessBoard[r + temp * j][c + temp * k].charAt(0))) {
                        oldPiece = chessBoard[r + temp * j][c + temp * k];
                        chessBoard[r][c] = " ";
                        chessBoard[r + temp * j][c + temp * k] = "b";
                        if(kingSafeL()) {
                            list.append(r).append(c).append(r + temp * j).append(c + temp * k).append(oldPiece);
                        }
                        chessBoard[r][c] = "b";
                        chessBoard[r + temp * j][c + temp * k] = oldPiece;
                    }
                } catch (Exception ignored) {
                }
                temp = 1;
            }
        }
        return list.toString();
    }

    /**
     * Pomoci teto metody se vytvari mozne pohyby pro cernou kralovnu.
     *
     * @param i projizdi jednoliva policka sachovnice.
     * @return vraci String pohybu ve formatu x1,y1,x2,y2,C - x1,y1 pocatecni pozice.. x2,y2 koncova pozice... C figurka, ktera bude sebrana.
     */
    public static String possibleq(int i) {
        StringBuilder list = new StringBuilder();
        String oldPiece;
        int r = i / 8, c = i % 8;
        int temp = 1;
        for (int j = -1; j <= 1; j++) {
            for (int k = -1; k <= 1; k++) {
                if (j != 0 || k != 0) {
                    try {
                        while (" ".equals(chessBoard[r + temp * j][c + temp * k])) {
                            oldPiece = chessBoard[r + temp * j][c + temp * k];
                            chessBoard[r][c] = " ";
                            chessBoard[r + temp * j][c + temp * k] = "q";
                            if(kingSafeL()) {
                                list.append(r).append(c).append(r + temp * j).append(c + temp * k).append(oldPiece);
                            }
                            chessBoard[r][c] = "q";
                            chessBoard[r + temp * j][c + temp * k] = oldPiece;
                            temp++;
                        }
                        if (Character.isUpperCase(chessBoard[r + temp * j][c + temp * k].charAt(0))) {
                            oldPiece = chessBoard[r + temp * j][c + temp * k];
                            chessBoard[r][c] = " ";
                            chessBoard[r + temp * j][c + temp * k] = "q";
                            if(kingSafeL()) {
                                list.append(r).append(c).append(r + temp * j).append(c + temp * k).append(oldPiece);
                            }
                            chessBoard[r][c] = "q";
                            chessBoard[r + temp * j][c + temp * k] = oldPiece;
                        }
                    } catch (Exception ignored) {
                    }
                    temp = 1;
                }
            }
        }
        return list.toString();
    }

    /**
     * Pomoci teto metody se vytvari mozne pohyby pro cerneho krale.
     *
     * @param i projizdi jednoliva policka sachovnice.
     * @return vraci String pohybu ve formatu x1,y1,x2,y2,C - x1,y1 pocatecni pozice.. x2,y2 koncova pozice... C figurka, ktera bude sebrana.
     */
    public static String possiblea(int i) {
        StringBuilder list = new StringBuilder();
        String oldPiece;
        int r = i / 8, c = i % 8;
        int temp = 1;
        for (int j = 0; j < 9; j++) {
            if (j != 4) {
                try {
                    if (Character.isUpperCase(chessBoard[r - 1 + j / 3][c - 1 + j % 3].charAt(0)) || " ".equals(chessBoard[r - 1 + j / 3][c - 1 + j % 3])) {
                        oldPiece = chessBoard[r - 1 + j / 3][c - 1 + j % 3];
                        chessBoard[r][c] = " ";
                        chessBoard[r - 1 + j / 3][c - 1 + j % 3] = "a";
                        int kingTemp = kingPositionL;
                        kingPositionL = i + (j / 3) * 8 + j % 3 - 9;
                        if (kingSafeL ()) {
                            list.append(r).append(c).append(r-1+j/3).append(c-1+j%3).append(oldPiece);
                        }
                        chessBoard[r][c] = "a";
                        chessBoard[r - 1 + j / 3][c - 1 + j % 3] = oldPiece;
                        kingPositionL = kingTemp;
                    }
                } catch (Exception ignored) {
                }
            }
        } //rosada
        for (int k = -1; k <= 1; k += 2) {
            try {
                if (k == -1) {
                    while (" ".equals(chessBoard[r][c + k * temp])) {
                        temp++;
                    }
                    if ("r".equals(chessBoard[r][c + temp * k]) && !kingLmoved && (c + temp * k) == 0) {
                        oldPiece = chessBoard[r][c + temp * k];
                        chessBoard[r][c + temp * k] = "a";
                        chessBoard[r][c] = " ";
                        if (kingSafeC()) {
                            list.append(r).append(c).append(r).append(c + temp * k).append(oldPiece);
                        }
                        chessBoard[r][c] = "a";
                        chessBoard[r][c + temp * k] = oldPiece;
                    }
                }
            } catch (Exception ignored) {
            }
            temp = 1;
            try {
                if (k == 1) {
                    while (" ".equals(chessBoard[r][c + k * temp])) {
                        temp++;
                    }
                    if ("r".equals(chessBoard[r][c + temp * k]) && !kingLmoved && (c + temp * k) == 7) {
                        oldPiece = chessBoard[r][c + temp * k];
                        chessBoard[r][c + temp * k] = "a";
                        chessBoard[r][c] = " ";
                        if (kingSafeC()) {
                            list.append(r).append(c).append(r).append(c + temp * k).append(oldPiece);
                        }
                        chessBoard[r][c] = "a";
                        chessBoard[r][c + temp * k] = oldPiece;
                    }
                }
            } catch (Exception ignored) {
            }
            temp = 1;
        }
        return list.toString();
    }

    /**
     * Kontroluje zda je bily kral v bezpeci
     * @return true pokud je v bezpeci, false pokud ne
     */
    public static boolean kingSafeC() {
        //strelec/kralovna
        int temp = 1;
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j += 2) {
                try {
                    while (" ".equals(chessBoard[kingPositionC / 8 + temp * i][kingPositionC % 8 + temp * j])) {
                        temp++;
                    }
                    if ("b".equals(chessBoard[kingPositionC / 8 + temp * i][kingPositionC % 8 + temp * j]) ||
                            "q".equals(chessBoard[kingPositionC / 8 + temp * i][kingPositionC % 8 + temp * j])) {
                        return false;
                    }
                } catch (Exception ignored) {
                }
                temp = 1;
            }
        }
    //vez/kralovna
        for (int i=-1; i<=1; i+=2) {
            try {
                while(" ".equals(chessBoard[kingPositionC/8][kingPositionC%8+temp*i])) {temp++;}
                if ("r".equals(chessBoard[kingPositionC/8][kingPositionC%8+temp*i]) ||
                    "q".equals(chessBoard[kingPositionC/8][kingPositionC%8+temp*i])) {
                    return false;
                }
            } catch (Exception ignored) {}
            temp=1;
            try {
                while(" ".equals(chessBoard[kingPositionC/8+temp*i][kingPositionC%8])) {temp++;}
                if ("r".equals(chessBoard[kingPositionC/8+temp*i][kingPositionC%8]) ||
                    "q".equals(chessBoard[kingPositionC/8+temp*i][kingPositionC%8])) {
                    return false;
                }
            } catch (Exception ignored) {}
            temp=1;
        }
        //kun
        for (int i=-1; i<=1; i+=2) {
            for (int j=-1; j<=1; j+=2) {
                try {
                    if ("k".equals(chessBoard[kingPositionC/8+i][kingPositionC%8+j*2])) {
                        return false;
                    }
                } catch (Exception ignored) {}
                try {
                    if ("k".equals(chessBoard[kingPositionC/8+i*2][kingPositionC%8+j])) {
                        return false;
                    }
                } catch (Exception ignored) {}
            }
        }
        //pesak
        if (kingPositionC>=16) {
            try {
                if ("p".equals(chessBoard[kingPositionC/8-1][kingPositionC%8-1])) {
                    return false;
                }
            } catch (Exception ignored) {}
            try {
                if ("p".equals(chessBoard[kingPositionC/8-1][kingPositionC%8+1])) {
                    return false;
                }
            } catch (Exception ignored) {}
            //kral
            for (int i=-1; i<=1; i++) {
                for (int j=-1; j<=1; j++) {
                    if (i!=0 || j!=0) {
                        try {
                            if ("a".equals(chessBoard[kingPositionC/8+i][kingPositionC%8+j])) {
                                return false;
                            }
                        } catch (Exception ignored) {}
                    }
                }
            }
        }
        return true;
    }
    /**
     * Kontroluje zda je cerny kral v bezpeci
     * @return true pokud je v bezpeci, false pokud ne
     */
    public static boolean kingSafeL() {
        //strelec/kralovna
        int temp = 1;
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j += 2) {
                try {
                    while (" ".equals(chessBoard[kingPositionL / 8 + temp * i][kingPositionL % 8 + temp * j])) {
                        temp++;
                    }
                    if ("B".equals(chessBoard[kingPositionL / 8 + temp * i][kingPositionL % 8 + temp * j]) ||
                            "Q".equals(chessBoard[kingPositionL / 8 + temp * i][kingPositionL % 8 + temp * j])) {
                        return false;
                    }
                } catch (Exception ignored) {
                }
                temp = 1;
            }
        }
        //vez/kralovna
        for (int i=-1; i<=1; i+=2) {
            try {
                while(" ".equals(chessBoard[kingPositionL/8][kingPositionL%8+temp*i])) {temp++;}
                if ("R".equals(chessBoard[kingPositionL/8][kingPositionL%8+temp*i]) ||
                        "Q".equals(chessBoard[kingPositionL/8][kingPositionL%8+temp*i])) {
                    return false;
                }
            } catch (Exception ignored) {}
            temp=1;
            try {
                while(" ".equals(chessBoard[kingPositionL/8+temp*i][kingPositionL%8])) {temp++;}
                if ("R".equals(chessBoard[kingPositionL/8+temp*i][kingPositionL%8]) ||
                        "Q".equals(chessBoard[kingPositionL/8+temp*i][kingPositionL%8])) {
                    return false;
                }
            } catch (Exception ignored) {}
            temp=1;
        }
        //kun
        for (int i=-1; i<=1; i+=2) {
            for (int j=-1; j<=1; j+=2) {
                try {
                    if ("K".equals(chessBoard[kingPositionL/8+i][kingPositionL%8+j*2])) {
                        return false;
                    }
                } catch (Exception ignored) {}
                try {
                    if ("K".equals(chessBoard[kingPositionL/8+i*2][kingPositionL%8+j])) {
                        return false;
                    }
                } catch (Exception ignored) {}
            }
        }
        //pesak
        if (kingPositionL>=48) {
            try {
                if ("P".equals(chessBoard[kingPositionL/8+1][kingPositionL%8+1])) {
                    return false;
                }
            } catch (Exception ignored) {}
            try {
                if ("P".equals(chessBoard[kingPositionL/8+1][kingPositionL%8-1])) {
                    return false;
                }
            } catch (Exception ignored) {}
            //kral
            for (int i=-1; i<=1; i++) {
                for (int j=-1; j<=1; j++) {
                    if (i!=0 || j!=0) {
                        try {
                            if ("A".equals(chessBoard[kingPositionL/8+i][kingPositionL%8+j])) {
                                return false;
                            }
                        } catch (Exception ignored) {}
                    }
                }
            }
        }
        return true;
    }
}
