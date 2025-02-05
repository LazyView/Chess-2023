import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;

/**
 * Tato trida slouzi pro vykresleni vezi.
 */
public class Rook {
    double orgX = Sachovnice_a_sachy.newMouseX;
    double orgY = Sachovnice_a_sachy.newMouseY;

    /**
     * Tento konstruktor vykresluje veze, ktere se zrovna neanimuji.
     * @param g2 - graficky kontext.
     * @param chessBoxX - velikost strany policka.
     * @param chessboxPositionX - X pozice policka v okne.
     * @param chessboxPositionY - Y pozice policka v okne.
     * @param i - cislo policka.
     * @param color1 - nastavuje se podle toho jestli se jedna o bilou ci o cernou vez.
     * @param color2 - nastavuje se podle toho jestli se jedna o bilou ci o cernou vez.
     */
    public Rook(Graphics2D g2, double chessBoxX, double chessboxPositionX, double chessboxPositionY, int i, Color color1, Color color2) {
        if (i % 8 != (int) ((orgX / chessBoxX)) || i / 8 != (int) ((orgY / chessBoxX))) {
            g2.setColor(color1);
            Path2D rookR = new Path2D.Double();
            rookR.moveTo(chessboxPositionX + (i % 8 + 0.15) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.9) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + (i % 8 + 0.2) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.75) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + (i % 8 + 0.3) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.75) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + (i % 8 + 0.3) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.45) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + (i % 8 + 0.2) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.4) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + (i % 8 + 0.2) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.35) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + (i % 8 + 0.2) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.2) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + (i % 8 + 0.35) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.2) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + (i % 8 + 0.35) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.3) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + (i % 8 + 0.425) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.3) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + (i % 8 + 0.425) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.2) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + (i % 8 + 0.575) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.2) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + (i % 8 + 0.575) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.3) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + (i % 8 + 0.65) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.3) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + (i % 8 + 0.65) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.2) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + (i % 8 + 0.8) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.2) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + (i % 8 + 0.8) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.4) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + (i % 8 + 0.7) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.45) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + (i % 8 + 0.7) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.75) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + (i % 8 + 0.8) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.75) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + (i % 8 + 0.85) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.9) * chessBoxX)));
            rookR.closePath();
            Path2D rookRwindow1 = new Path2D.Double();
            rookRwindow1.moveTo(chessboxPositionX + (i % 8 + 0.35) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.5) * chessBoxX)));
            rookRwindow1.lineTo(chessboxPositionX + (i % 8 + 0.45) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.5) * chessBoxX)));
            rookRwindow1.lineTo(chessboxPositionX + (i % 8 + 0.45) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.6) * chessBoxX)));
            rookRwindow1.lineTo(chessboxPositionX + (i % 8 + 0.35) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.6) * chessBoxX)));
            rookRwindow1.closePath();
            Path2D rookRwindow2 = new Path2D.Double();
            rookRwindow2.moveTo(chessboxPositionX + (i % 8 + 0.55) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.5) * chessBoxX)));
            rookRwindow2.lineTo(chessboxPositionX + (i % 8 + 0.65) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.5) * chessBoxX)));
            rookRwindow2.lineTo(chessboxPositionX + (i % 8 + 0.65) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.6) * chessBoxX)));
            rookRwindow2.lineTo(chessboxPositionX + (i % 8 + 0.55) * chessBoxX, (chessboxPositionY + ((i / 8 + 0.6) * chessBoxX)));
            rookRwindow2.closePath();
            g2.fill(rookR);
            g2.setColor(color2);
            g2.draw(rookR);
            g2.draw(new Line2D.Double((chessboxPositionX + (i % 8 + 0.3) * chessBoxX), (chessboxPositionY + (i / 8 + 0.45) * chessBoxX), (chessboxPositionX + (i % 8 + 0.7) * chessBoxX), (chessboxPositionY + (i / 8 + 0.45) * chessBoxX)));
            g2.draw(new Line2D.Double(chessboxPositionX + (i % 8 + 0.2) * chessBoxX, (chessboxPositionY + (i / 8 + 0.75) * chessBoxX), (chessboxPositionX + (i % 8 + 0.8) * chessBoxX), (chessboxPositionY + (i / 8 + 0.75) * chessBoxX)));
            g2.draw(rookRwindow1);
            g2.draw(rookRwindow2);
        }
    }

    /**
     * Tento konstruktor vykresluje vez, ktera se zrovna animuje.
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
    public Rook(Graphics2D g2, double chessBoxX, double chessboxPositionX, double chessboxPositionY, double orgX, double orgY, double toX, double toY, long startTime, boolean animaceTahu, double scale, Color color1, Color color2) {
        if (animaceTahu) {
            double prirustek = (System.currentTimeMillis() - startTime) / 500.;
            orgX = (int) ((orgX / chessBoxX)) * chessBoxX;
            orgY = (int) ((orgY / chessBoxX)) * chessBoxX;
            toX = (int) ((toX / chessBoxX)) * chessBoxX;
            toY = (int) ((toY / chessBoxX)) * chessBoxX;
            int x = (int) ((((toX) - (orgX)) * prirustek));
            int y = (int) ((((toY) - (orgY)) * prirustek));
            g2.translate(x, y);
            g2.setColor(color1);
            Path2D rookR = new Path2D.Double();
            rookR.moveTo(chessboxPositionX + ((orgX / chessBoxX) + 0.15) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.9) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.2) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.75) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.3) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.75) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.3) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.45) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.2) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.4) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.2) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.35) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.2) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.2) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.35) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.2) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.35) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.3) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.425) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.3) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.425) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.2) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.575) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.2) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.575) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.3) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.65) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.3) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.65) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.2) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.8) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.2) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.8) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.4) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.7) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.45) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.7) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.75) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.8) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.75) * chessBoxX)));
            rookR.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.85) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.9) * chessBoxX)));
            rookR.closePath();
            Path2D rookRwindow1 = new Path2D.Double();
            rookRwindow1.moveTo(chessboxPositionX + ((orgX / chessBoxX) + 0.35) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.5) * chessBoxX)));
            rookRwindow1.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.45) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.5) * chessBoxX)));
            rookRwindow1.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.45) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.6) * chessBoxX)));
            rookRwindow1.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.35) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.6) * chessBoxX)));
            rookRwindow1.closePath();
            Path2D rookRwindow2 = new Path2D.Double();
            rookRwindow2.moveTo(chessboxPositionX + ((orgX / chessBoxX) + 0.55) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.5) * chessBoxX)));
            rookRwindow2.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.65) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.5) * chessBoxX)));
            rookRwindow2.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.65) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.6) * chessBoxX)));
            rookRwindow2.lineTo(chessboxPositionX + ((orgX / chessBoxX) + 0.55) * chessBoxX, (chessboxPositionY + (((orgY / chessBoxX) + 0.6) * chessBoxX)));
            rookRwindow2.closePath();
            g2.fill(rookR);
            g2.setColor(color2);
            g2.draw(rookR);
            g2.draw(new Line2D.Double((chessboxPositionX + ((orgX / chessBoxX) + 0.3) * chessBoxX), (chessboxPositionY + ((orgY / chessBoxX) + 0.45) * chessBoxX), (chessboxPositionX + ((orgX / chessBoxX) + 0.7) * chessBoxX), (chessboxPositionY + ((orgY / chessBoxX) + 0.45) * chessBoxX)));
            g2.draw(new Line2D.Double(chessboxPositionX + ((orgX / chessBoxX) + 0.2) * chessBoxX, (chessboxPositionY + ((orgY / chessBoxX) + 0.75) * chessBoxX), (chessboxPositionX + ((orgX / chessBoxX) + 0.8) * chessBoxX), (chessboxPositionY + ((orgY / chessBoxX) + 0.75) * chessBoxX)));
            g2.draw(rookRwindow1);
            g2.draw(rookRwindow2);
        }
    }
}
