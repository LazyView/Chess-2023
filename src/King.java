import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
/**
 * Tato trida slouzi pro vykresleni kralu
 */
public class King {
    double orgX = Sachovnice_a_sachy.newMouseX;
    double orgY = Sachovnice_a_sachy.newMouseY;
    /**
     * Tento konstruktor vykresluje figurky kralu, ktere se zrovna neanimuji.
     * @param g2 - graficky kontext.
     * @param chessBoxX - velikost strany policka.
     * @param chessboxPositionX - X pozice policka v okne.
     * @param chessboxPositionY - Y pozice policka v okne.
     * @param i - cislo policka.
     * @param color1 - nastavuje se podle toho jestli se jedna o bilou ci o cernou vez.
     * @param color2 - nastavuje se podle toho jestli se jedna o bilou ci o cernou vez.
     */
    public King(Graphics2D g2, double chessBoxX, double chessboxPositionX, double chessboxPositionY, int i, double scale, Color color1, Color color2) {
        if (i % 8 != (int) ((orgX / chessBoxX)) || i / 8 != (int) ((orgY / chessBoxX))) {
            Path2D kingA = new Path2D.Double();
            kingA.moveTo(chessboxPositionX + (i % 8 + 0.15) * chessBoxX, chessboxPositionY + (i / 8 + 0.9) * chessBoxX);
            kingA.lineTo(chessboxPositionX + (i % 8 + 0.2) * chessBoxX, chessboxPositionY + (i / 8 + 0.85) * chessBoxX);
            kingA.lineTo(chessboxPositionX + (i % 8 + 0.2) * chessBoxX, chessboxPositionY + (i / 8 + 0.8) * chessBoxX);
            kingA.lineTo(chessboxPositionX + (i % 8 + 0.25) * chessBoxX, chessboxPositionY + (i / 8 + 0.8) * chessBoxX);
            kingA.lineTo(chessboxPositionX + (i % 8 + 0.25) * chessBoxX, chessboxPositionY + (i / 8 + 0.6) * chessBoxX);
            kingA.lineTo(chessboxPositionX + (i % 8 + 0.5) * chessBoxX, chessboxPositionY + (i / 8 + 0.45) * chessBoxX);
            kingA.lineTo(chessboxPositionX + (i % 8 + 0.75) * chessBoxX, chessboxPositionY + (i / 8 + 0.6) * chessBoxX);
            kingA.lineTo(chessboxPositionX + (i % 8 + 0.75) * chessBoxX, chessboxPositionY + (i / 8 + 0.8) * chessBoxX);
            kingA.lineTo(chessboxPositionX + (i % 8 + 0.8) * chessBoxX, chessboxPositionY + (i / 8 + 0.8) * chessBoxX);
            kingA.lineTo(chessboxPositionX + (i % 8 + 0.8) * chessBoxX, chessboxPositionY + (i / 8 + 0.85) * chessBoxX);
            kingA.lineTo(chessboxPositionX + (i % 8 + 0.85) * chessBoxX, chessboxPositionY + (i / 8 + 0.9) * chessBoxX);
            kingA.closePath();
            g2.setColor(color1);
            g2.fill(kingA);
            g2.setColor(color2);
            g2.draw(kingA);
            g2.draw(new Arc2D.Double(chessboxPositionX + (i % 8 + 0.0) * chessBoxX, chessboxPositionY + (i / 8 + 0.6) * chessBoxX, 37.5 * scale, 30 * scale, 0, 90, Arc2D.OPEN));
            g2.draw(new Arc2D.Double(chessboxPositionX + (i % 8 + 0.5) * chessBoxX, chessboxPositionY + (i / 8 + 0.6) * chessBoxX, 37.5 * scale, 30 * scale, 90, 90, Arc2D.OPEN));
            g2.draw(new Line2D.Double(chessboxPositionX + (i % 8 + 0.2) * chessBoxX, chessboxPositionY + (i / 8 + 0.8) * chessBoxX, chessboxPositionX + (i % 8 + 0.75) * chessBoxX, chessboxPositionY + (i / 8 + 0.8) * chessBoxX));
            g2.draw(new Line2D.Double(chessboxPositionX + (i % 8 + 0.2) * chessBoxX, chessboxPositionY + (i / 8 + 0.8) * chessBoxX, chessboxPositionX + (i % 8 + 0.8) * chessBoxX, chessboxPositionY + (i / 8 + 0.8) * chessBoxX));
            g2.draw(new Line2D.Double(chessboxPositionX + (i % 8 + 0.5) * chessBoxX, chessboxPositionY + (i / 8 + 0.2) * chessBoxX, chessboxPositionX + (i % 8 + 0.5) * chessBoxX, chessboxPositionY + (i / 8 + 0.45) * chessBoxX));
            g2.draw(new Line2D.Double(chessboxPositionX + (i % 8 + 0.4) * chessBoxX, chessboxPositionY + (i / 8 + 0.3) * chessBoxX, chessboxPositionX + (i % 8 + 0.6) * chessBoxX, chessboxPositionY + (i / 8 + 0.3) * chessBoxX));
        }
    }
    /**
     * Tento konstruktor vykresluje figurku strelce, ktera se zrovna animuje.
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
    public King(Graphics2D g2, double chessBoxX, double chessboxPositionX, double chessboxPositionY, double orgX, double orgY, double toX, double toY, long startTime, boolean animaceTahu, double scale, Color color1, Color color2) {
        if (animaceTahu) {
            double prirustek = (System.currentTimeMillis() - startTime) / 500.;
            orgX = (int) ((orgX / chessBoxX)) * chessBoxX;
            orgY = (int) ((orgY / chessBoxX)) * chessBoxX;
            toX = (int) ((toX / chessBoxX)) * chessBoxX;
            toY = (int) ((toY / chessBoxX)) * chessBoxX;
            int x = (int) ((((toX) - (orgX)) * prirustek));
            int y = (int) ((((toY) - (orgY)) * prirustek));
            g2.translate(x, y);
            Path2D kingA = new Path2D.Double();
            kingA.moveTo(chessboxPositionX + ((orgX / chessBoxX) + 0.15) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.9) * chessBoxX);
            kingA.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.2) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.85) * chessBoxX);
            kingA.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.2) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.8) * chessBoxX);
            kingA.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.25) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.8) * chessBoxX);
            kingA.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.25) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.6) * chessBoxX);
            kingA.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.5) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.45) * chessBoxX);
            kingA.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.75) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.6) * chessBoxX);
            kingA.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.75) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.8) * chessBoxX);
            kingA.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.8) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.8) * chessBoxX);
            kingA.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.8) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.85) * chessBoxX);
            kingA.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.85) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.9) * chessBoxX);
            kingA.closePath();
            g2.setColor(color1);
            g2.fill(kingA);
            g2.setColor(color2);
            g2.draw(kingA);
            g2.draw(new Arc2D.Double(chessboxPositionX + ((orgX / chessBoxX) + 0.0) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.6) * chessBoxX, 37.5 * scale, 30 * scale, 0, 90, Arc2D.OPEN));
            g2.draw(new Arc2D.Double(chessboxPositionX + ((orgX / chessBoxX) + 0.5) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.6) * chessBoxX, 37.5 * scale, 30 * scale, 90, 90, Arc2D.OPEN));
            g2.draw(new Line2D.Double(chessboxPositionX + ((orgX / chessBoxX) + 0.2) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.8) * chessBoxX, chessboxPositionX + ((orgX / chessBoxX) + 0.75) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.8) * chessBoxX));
            g2.draw(new Line2D.Double(chessboxPositionX + ((orgX / chessBoxX) + 0.2) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.8) * chessBoxX, chessboxPositionX + ((orgX / chessBoxX) + 0.8) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.8) * chessBoxX));
            g2.draw(new Line2D.Double(chessboxPositionX + ((orgX / chessBoxX) + 0.5) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.2) * chessBoxX, chessboxPositionX + ((orgX / chessBoxX) + 0.5) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.45) * chessBoxX));
            g2.draw(new Line2D.Double(chessboxPositionX + ((orgX / chessBoxX) + 0.4) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.3) * chessBoxX, chessboxPositionX + ((orgX / chessBoxX) + 0.6) * chessBoxX, chessboxPositionY + ((orgY / chessBoxX) + 0.3) * chessBoxX));
        }
    }
}
