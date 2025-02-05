import javax.swing.*;
import java.util.Arrays;

/**
 * Tato trida je hlavni tridou celeho projektu
 */
public class Main {
    static JFrame okno = new JFrame();
    public static void main(String[] args) {

        okno.setTitle("Filip Chlad, A22B0060P ");
        Sachovnice_a_sachy dp = new Sachovnice_a_sachy();
        //prida komponentu

        okno.add(dp);
        okno.pack();
        //udela resize okna dle komponenty
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        //vycentrovani na stred
        okno.setLocationRelativeTo(null);
        okno.setVisible(true);
        while (!"A".equals(Chess_SP_2023.chessBoard[Chess_SP_2023.kingPositionC / 8][Chess_SP_2023.kingPositionC % 8])) {
            Chess_SP_2023.kingPositionC++;
        }//get King's location
        while (!"a".equals(Chess_SP_2023.chessBoard[Chess_SP_2023.kingPositionL / 8][Chess_SP_2023.kingPositionL % 8])) {
            Chess_SP_2023.kingPositionL++;
        }//get king's location\
        //vyhodnocuje vysledek hry

    }
}