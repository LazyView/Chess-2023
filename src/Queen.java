import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
/**
 * Tato trida slouzi pro vykresleni kraloven
 */
public class Queen {
    double orgX = Sachovnice_a_sachy.newMouseX;
    double orgY = Sachovnice_a_sachy.newMouseY;
    /**
     * Tento konstruktor vykresluje figurky kraloven, ktere se zrovna neanimuji.
     * @param g2 - graficky kontext.
     * @param chessBoxX - velikost strany policka.
     * @param chessboxPositionX - X pozice policka v okne.
     * @param chessboxPositionY - Y pozice policka v okne.
     * @param i - cislo policka.
     * @param color1 - nastavuje se podle toho jestli se jedna o bilou ci o cernou vez.
     * @param color2 - nastavuje se podle toho jestli se jedna o bilou ci o cernou vez.
     */
    public Queen(Graphics2D g2, double chessBoxX, double chessboxPositionX, double chessboxPositionY, int i, double scale, Color color1, Color color2) {
        if (i % 8 != (int) ((orgX / chessBoxX)) || i / 8 != (int) ((orgY / chessBoxX))) {
            Path2D queenQ = new Path2D.Double();
            queenQ.moveTo(chessboxPositionX + (i % 8 + 0.15) * chessBoxX, chessboxPositionY + (i / 8 + 0.9) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + (i % 8 + 0.2) * chessBoxX, chessboxPositionY + (i / 8 + 0.85) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + (i % 8 + 0.2) * chessBoxX, chessboxPositionY + (i / 8 + 0.8) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + (i % 8 + 0.25) * chessBoxX, chessboxPositionY + (i / 8 + 0.8) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + (i % 8 + 0.25) * chessBoxX, chessboxPositionY + (i / 8 + 0.75) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + (i % 8 + 0.15) * chessBoxX, chessboxPositionY + (i / 8 + 0.65) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + (i % 8 + 0.15) * chessBoxX, chessboxPositionY + (i / 8 + 0.55) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + (i % 8 + 0.1) * chessBoxX, chessboxPositionY + (i / 8 + 0.3) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + (i % 8 + 0.29) * chessBoxX, chessboxPositionY + (i / 8 + 0.55) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + (i % 8 + 0.3) * chessBoxX, chessboxPositionY + (i / 8 + 0.25) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + (i % 8 + 0.43) * chessBoxX, chessboxPositionY + (i / 8 + 0.505) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + (i % 8 + 0.5) * chessBoxX, chessboxPositionY + (i / 8 + 0.2) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + (i % 8 + 0.57) * chessBoxX, chessboxPositionY + (i / 8 + 0.505) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + (i % 8 + 0.7) * chessBoxX, chessboxPositionY + (i / 8 + 0.25) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + (i % 8 + 0.72) * chessBoxX, chessboxPositionY + (i / 8 + 0.55) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + (i % 8 + 0.9) * chessBoxX, chessboxPositionY + (i / 8 + 0.3) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + (i % 8 + 0.85) * chessBoxX, chessboxPositionY + (i / 8 + 0.55) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + (i % 8 + 0.85) * chessBoxX, chessboxPositionY + (i / 8 + 0.65) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + (i % 8 + 0.75) * chessBoxX, chessboxPositionY + (i / 8 + 0.75) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + (i % 8 + 0.75) * chessBoxX, chessboxPositionY + (i / 8 + 0.8) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + (i % 8 + 0.8) * chessBoxX, chessboxPositionY + (i / 8 + 0.8) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + (i % 8 + 0.8) * chessBoxX, chessboxPositionY + (i / 8 + 0.85) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + (i % 8 + 0.85) * chessBoxX, chessboxPositionY + (i / 8 + 0.9) * chessBoxX);
            queenQ.closePath();
            g2.setColor(color1);
            g2.fill(new Arc2D.Double(chessboxPositionX + (i % 8 + 0.025) * chessBoxX, chessboxPositionY + (i / 8 + 0.155) * chessBoxX, 7.5 * scale, 7.5 * scale, 0, 360, Arc2D.OPEN));
            g2.fill(new Arc2D.Double(chessboxPositionX + (i % 8 + 0.225) * chessBoxX, chessboxPositionY + (i / 8 + 0.105) * chessBoxX, 7.5 * scale, 7.5 * scale, 0, 360, Arc2D.OPEN));
            g2.fill(new Arc2D.Double(chessboxPositionX + (i % 8 + 0.45) * chessBoxX, chessboxPositionY + (i / 8 + 0.055) * chessBoxX, 7.5 * scale, 7.5 * scale, 0, 360, Arc2D.OPEN));
            g2.fill(new Arc2D.Double(chessboxPositionX + (i % 8 + 0.675) * chessBoxX, chessboxPositionY + (i / 8 + 0.105) * chessBoxX, 7.5 * scale, 7.5 * scale, 0, 360, Arc2D.OPEN));
            g2.fill(new Arc2D.Double(chessboxPositionX + (i % 8 + 0.875) * chessBoxX, chessboxPositionY + (i / 8 + 0.155) * chessBoxX, 7.5 * scale, 7.5 * scale, 0, 360, Arc2D.OPEN));
            g2.fill(queenQ);
            g2.setColor(color2);
            g2.draw(queenQ);
            g2.draw(new Arc2D.Double(chessboxPositionX + (i % 8 + 0.15) * chessBoxX, chessboxPositionY + (i / 8 + 0.6) * chessBoxX, 52.5 * scale, 6.5 * scale, 0, 180, Arc2D.OPEN));
            g2.draw(new Line2D.Double(chessboxPositionX + (i % 8 + 0.2) * chessBoxX, chessboxPositionY + (i / 8 + 0.8) * chessBoxX, chessboxPositionX + (i % 8 + 0.8) * chessBoxX, chessboxPositionY + (i / 8 + 0.8) * chessBoxX));
            g2.draw(new Arc2D.Double(chessboxPositionX + (i % 8 + 0.025) * chessBoxX, chessboxPositionY + (i / 8 + 0.155) * chessBoxX, 7.5 * scale, 7.5 * scale, 0, 360, Arc2D.OPEN));
            g2.draw(new Arc2D.Double(chessboxPositionX + (i % 8 + 0.225) * chessBoxX, chessboxPositionY + (i / 8 + 0.105) * chessBoxX, 7.5 * scale, 7.5 * scale, 0, 360, Arc2D.OPEN));
            g2.draw(new Arc2D.Double(chessboxPositionX + (i % 8 + 0.45) * chessBoxX, chessboxPositionY + (i / 8 + 0.055) * chessBoxX, 7.5 * scale, 7.5 * scale, 0, 360, Arc2D.OPEN));
            g2.draw(new Arc2D.Double(chessboxPositionX + (i % 8 + 0.675) * chessBoxX, chessboxPositionY + (i / 8 + 0.105) * chessBoxX, 7.5 * scale, 7.5 * scale, 0, 360, Arc2D.OPEN));
            g2.draw(new Arc2D.Double(chessboxPositionX + (i % 8 + 0.875) * chessBoxX, chessboxPositionY + (i / 8 + 0.155) * chessBoxX, 7.5 * scale, 7.5 * scale, 0, 360, Arc2D.OPEN));
        }
    }
    /**
     * Tento konstruktor vykresluje figurku kralovny, ktera se zrovna animuje.
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
    public Queen(Graphics2D g2, double chessBoxX, double chessboxPositionX, double chessboxPositionY, double orgX, double orgY, double toX, double toY, long startTime, boolean animaceTahu, double scale, Color color1, Color color2) {
        if (animaceTahu) {
            double prirustek = (System.currentTimeMillis() - startTime) / 500.;
            orgX = (int) ((orgX / chessBoxX)) * chessBoxX;
            orgY = (int) ((orgY / chessBoxX)) * chessBoxX;
            toX = (int) ((toX / chessBoxX)) * chessBoxX;
            toY = (int) ((toY / chessBoxX)) * chessBoxX;
            int x = (int) ((((toX) - (orgX)) * prirustek));
            int y = (int) ((((toY) - (orgY)) * prirustek));
            g2.translate(x, y);
            Path2D queenQ = new Path2D.Double();
            queenQ.moveTo(chessboxPositionX + ((orgX / chessBoxX) + 0.15) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.9) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.2) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.85) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.2) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.8) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.25) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.8) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.25) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.75) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.15) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.65) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.15) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.55) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.1) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.3) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.29) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.55) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.3) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.25) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.43) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.505) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.5) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.2) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.57) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.505) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.7) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.25) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.72) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.55) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.9) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.3) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.85) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.55) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.85) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.65) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.75) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.75) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.75) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.8) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.8) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.8) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.8) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.85) * chessBoxX);
            queenQ.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.85) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.9) * chessBoxX);
            queenQ.closePath();
            g2.setColor(color1);
            g2.fill(new Arc2D.Double(chessboxPositionX + ((orgX / chessBoxX) + 0.025) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.155) * chessBoxX, 7.5 * scale, 7.5 * scale, 0, 360, Arc2D.OPEN));
            g2.fill(new Arc2D.Double(chessboxPositionX + ((orgX / chessBoxX) + 0.225) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.105) * chessBoxX, 7.5 * scale, 7.5 * scale, 0, 360, Arc2D.OPEN));
            g2.fill(new Arc2D.Double(chessboxPositionX + ((orgX / chessBoxX) + 0.45) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.055) * chessBoxX, 7.5 * scale, 7.5 * scale, 0, 360, Arc2D.OPEN));
            g2.fill(new Arc2D.Double(chessboxPositionX + ((orgX / chessBoxX) + 0.675) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.105) * chessBoxX, 7.5 * scale, 7.5 * scale, 0, 360, Arc2D.OPEN));
            g2.fill(new Arc2D.Double(chessboxPositionX + ((orgX / chessBoxX) + 0.875) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.155) * chessBoxX, 7.5 * scale, 7.5 * scale, 0, 360, Arc2D.OPEN));
            g2.fill(queenQ);
            g2.setColor(color2);
            g2.draw(queenQ);
            g2.draw(new Arc2D.Double(chessboxPositionX + ((orgX / chessBoxX) + 0.15) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.6) * chessBoxX, 52.5 * scale, 6.5 * scale, 0, 180, Arc2D.OPEN));
            g2.draw(new Line2D.Double(chessboxPositionX + ((orgX / chessBoxX) + 0.2) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.8) * chessBoxX, chessboxPositionX + ((orgX / chessBoxX) + 0.8) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.8) * chessBoxX));
            g2.draw(new Arc2D.Double(chessboxPositionX + ((orgX / chessBoxX) + 0.025) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.155) * chessBoxX, 7.5 * scale, 7.5 * scale, 0, 360, Arc2D.OPEN));
            g2.draw(new Arc2D.Double(chessboxPositionX + ((orgX / chessBoxX) + 0.225) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.105) * chessBoxX, 7.5 * scale, 7.5 * scale, 0, 360, Arc2D.OPEN));
            g2.draw(new Arc2D.Double(chessboxPositionX + ((orgX / chessBoxX) + 0.45) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.055) * chessBoxX, 7.5 * scale, 7.5 * scale, 0, 360, Arc2D.OPEN));
            g2.draw(new Arc2D.Double(chessboxPositionX + ((orgX / chessBoxX) + 0.675) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.105) * chessBoxX, 7.5 * scale, 7.5 * scale, 0, 360, Arc2D.OPEN));
            g2.draw(new Arc2D.Double(chessboxPositionX + ((orgX / chessBoxX) + 0.875) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.155) * chessBoxX, 7.5 * scale, 7.5 * scale, 0, 360, Arc2D.OPEN));
        }
    }
}
