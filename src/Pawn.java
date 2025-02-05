import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
/**
 * Tato trida slouzi pro vykresleni pesaku
 */
public class Pawn{
    double orgX = Sachovnice_a_sachy.newMouseX;
    double orgY = Sachovnice_a_sachy.newMouseY;
        /**
         * Tento konstruktor vykresluje figurky pesaku, ktere se zrovna neanimuji.
         * @param g2 - graficky kontext.
         * @param chessBoxX - velikost strany policka.
         * @param chessboxPositionX - X pozice policka v okne.
         * @param chessboxPositionY - Y pozice policka v okne.
         * @param i - cislo policka.
         * @param color1 - nastavuje se podle toho jestli se jedna o bilou ci o cernou vez.
         * @param color2 - nastavuje se podle toho jestli se jedna o bilou ci o cernou vez.
         */
    public Pawn(Graphics2D g2, double chessBoxX, double chessboxPositionX, double chessboxPositionY, int i, double scale, Color color1, Color color2) {
        if(i % 8 != (int)((orgX / chessBoxX)) || i/8 != (int)((orgY / chessBoxX))) {
                g2.setColor(color2);
                Path2D pawnP = new Path2D.Double();
                pawnP.moveTo(chessboxPositionX + (i % 8 + 0.25) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.9) * chessBoxX)));
                pawnP.lineTo((chessboxPositionX + (i % 8 + 0.25) * chessBoxX), (chessboxPositionY + (i / 8 + 0.8) * chessBoxX));
                pawnP.lineTo((chessboxPositionX + (i % 8 + 0.35) * chessBoxX), (chessboxPositionY + (i / 8 + 0.7) * chessBoxX));
                pawnP.lineTo((chessboxPositionX + (i % 8 + 0.4) * chessBoxX), (chessboxPositionY + (i / 8 + 0.55) * chessBoxX));
                pawnP.lineTo((chessboxPositionX + (i % 8 + 0.6) * chessBoxX), (chessboxPositionY + (i / 8 + 0.55) * chessBoxX));
                pawnP.lineTo((chessboxPositionX + (i % 8 + 0.65) * chessBoxX), (chessboxPositionY + (i / 8 + 0.7) * chessBoxX));
                pawnP.lineTo((chessboxPositionX + (i % 8 + 0.75) * chessBoxX), (chessboxPositionY + (i / 8 + 0.8) * chessBoxX));
                pawnP.lineTo((chessboxPositionX + (i % 8 + 0.75) * chessBoxX), (chessboxPositionY + (i / 8 + 0.9) * chessBoxX));
                pawnP.closePath();
                g2.fill(pawnP);
                g2.setColor(color1);
                g2.draw(pawnP);
                g2.setColor(color2);
                g2.fill(new Arc2D.Double(chessboxPositionX + (i % 8 + 0.35) * chessBoxX, chessboxPositionY + (i / 8 + 0.3) * chessBoxX, 22.5 * scale, 22.5 * scale, 0, 360, Arc2D.OPEN));
                g2.setColor(color1);
                g2.draw(new Arc2D.Double(chessboxPositionX + (i % 8 + 0.35) * chessBoxX, chessboxPositionY + (i / 8 + 0.3) * chessBoxX, 22.5 * scale, 22.5 * scale, 0, 360, Arc2D.OPEN));
        }
    }
        /**
         * Tento konstruktor vykresluje figurku pesaka, ktera se zrovna animuje.
         * @param g2 - graficky kontext.
         * @param chessBoxX - velikost policka.
         * @param chessboxPositionX - X pozice policka.
         * @param chessboxPositionY - Y pozice policka.
         * @param orgX - pocatecni X.
         * @param orgY - pocatecni Y.
         * @param toX - koncove X.
         * @param toY - koncove Y.
         * @param startTime - cas kdy se animace spousti.
         * @param animaceTahu - sleduje zda se ma animace vykonavat.
         * @param scale - meritko vuci velikosti okna.
         * @param color1 - nastavuje se podle toho jestli se jedna o bilou ci o cernou vez.
         * @param color2 - nastavuje se podle toho jestli se jedna o bilou ci o cernou vez.
         */
    public Pawn(Graphics2D g2, double chessBoxX, double chessboxPositionX, double chessboxPositionY, double orgX, double orgY, double toX, double toY, long startTime, boolean animaceTahu, double scale, Color color1, Color color2){
        if(animaceTahu) {
                double prirustek = (System.currentTimeMillis() - startTime)/500.;
                orgX = (int) ((orgX / chessBoxX)) * chessBoxX;
                orgY = (int) ((orgY / chessBoxX)) * chessBoxX;
                toX =  (int) ((toX / chessBoxX)) * chessBoxX;
                toY = (int) ((toY / chessBoxX)) * chessBoxX;
                int x = (int) ((((toX) - (orgX)) * prirustek));
                int y = (int) ((((toY) - (orgY)) * prirustek));
                g2.translate(x, y);
                g2.setColor(color2);
                Path2D pawnP1 = new Path2D.Double();
                pawnP1.moveTo(chessboxPositionX + ((int) (orgX / chessBoxX) + 0.25) * chessBoxX, (chessboxPositionY + ((int) (orgY / chessBoxX) + 0.9) * chessBoxX));
                pawnP1.lineTo((chessboxPositionX + ((int) (orgX / chessBoxX) + 0.25) * chessBoxX), (chessboxPositionY + ((int) (orgY / chessBoxX) + 0.8) * chessBoxX));
                pawnP1.lineTo((chessboxPositionX + ((int) (orgX / chessBoxX) + 0.35) * chessBoxX), (chessboxPositionY + ((int) (orgY / chessBoxX) + 0.7) * chessBoxX));
                pawnP1.lineTo((chessboxPositionX + ((int) (orgX / chessBoxX) + 0.4) * chessBoxX), (chessboxPositionY + ((int) (orgY / chessBoxX) + 0.55) * chessBoxX));
                pawnP1.lineTo((chessboxPositionX + ((int) (orgX / chessBoxX) + 0.6) * chessBoxX), (chessboxPositionY + ((int) (orgY / chessBoxX) + 0.55) * chessBoxX));
                pawnP1.lineTo((chessboxPositionX + ((int) (orgX / chessBoxX) + 0.65) * chessBoxX), (chessboxPositionY + ((int) (orgY / chessBoxX) + 0.7) * chessBoxX));
                pawnP1.lineTo((chessboxPositionX + ((int) (orgX / chessBoxX) + 0.75) * chessBoxX), (chessboxPositionY + ((int) (orgY / chessBoxX) + 0.8) * chessBoxX));
                pawnP1.lineTo((chessboxPositionX + ((int) (orgX / chessBoxX) + 0.75) * chessBoxX), (chessboxPositionY + ((int) (orgY / chessBoxX) + 0.9) * chessBoxX));
                pawnP1.closePath();
                g2.fill(pawnP1);
                g2.setColor(color1);
                g2.draw(pawnP1);
                g2.setColor(color2);
                g2.fill(new Arc2D.Double(chessboxPositionX + ((int) (orgX / chessBoxX) + 0.35) * chessBoxX, chessboxPositionY + ((int) (orgY / chessBoxX) + 0.3) * chessBoxX, 22.5 * scale, 22.5 * scale, 0, 360, Arc2D.OPEN));
                g2.setColor(color1);
                g2.draw(new Arc2D.Double(chessboxPositionX + ((int) (orgX / chessBoxX) + 0.35) * chessBoxX, chessboxPositionY + ((int) (orgY / chessBoxX) + 0.3) * chessBoxX, 22.5 * scale, 22.5 * scale, 0, 360, Arc2D.OPEN));
        }
    }
}

