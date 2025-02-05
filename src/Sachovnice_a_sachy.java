import org.jfree.chart.ChartPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * Tato trida slouzi pro vykresleni sachovnice a jednotlivych figurek. Dale slouzi k naprogramovani pohybu a registraci mysi na sachovnici.
 */
public class Sachovnice_a_sachy extends JPanel implements MouseListener, MouseMotionListener{
    double scaleX;
    double scaleY;
    static double scale;
    double fieldX;
    double fieldY;
    double chessBoxX;
    double chessBoxY;
    double posX;
    double posY;
    double chessboxPositionX;
    double chessboxPositionY;
    //Pozice mysi, mouseX, mouseY - startovni pozice... newMouseX, newMouseY - konecna pozice
    static int mouseX, mouseY;
    static int newMouseX = 1000000000;
    static int newMouseY = 1000000000;
    static ArrayList<Double> casC = new ArrayList<>();
    static ArrayList<Double> casL = new ArrayList<>();
    Double timeS = System.currentTimeMillis() / 1000.0;
    double time = 0;
    static String moveP1;
    String moveP;
    long startTime;
    long startTime1;
    static boolean animaceTahu = false;
    //Tlacitka
    JButton b;
    JButton l;
    JButton f;
    JButton d;
    JButton ex;
    JButton UI;
    JButton Pl;
    //Pozice mysi na sachovnici
    int x1;
    int x2;
    int y1;
    int y2;
    //Rozhodnuti o vysledku
    boolean konec = false;
    static ArrayList<String> forMoves = new ArrayList<>();
    public Sachovnice_a_sachy() {

        // Prida detekci mysi.
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setPreferredSize(new Dimension(800, 600));
        this.setLayout(new BorderLayout());

        //Tlacitko na graf
        b = new JButton("Graf");
        b.addActionListener(e -> {
            //graf
            JFrame oknoG = new JFrame();
            Graf delka = new Graf(Sachovnice_a_sachy.casC, Sachovnice_a_sachy.casL);
            ChartPanel panel = new ChartPanel(delka.createBarChart());
            oknoG.add(panel);
            oknoG.pack();
            oknoG.setVisible(true);
        });
        this.add(b);

        //Tlacitko na reset
        d = new JButton("Reset");
        d.addActionListener(e -> {
            try {
                Chess_SP_2023.chessBoard = new String[][]{
                        {"r", "k", "b", "q", "a", "b", "k", "r"},
                        {"p", "p", "p", "p", "p", "p", "p", "p"},
                        {" ", " ", " ", " ", " ", " ", " ", " "},
                        {" ", " ", " ", " ", " ", " ", " ", " "},
                        {" ", " ", " ", " ", " ", " ", " ", " "},
                        {" ", " ", " ", " ", " ", " ", " ", " "},
                        {"P", "P", "P", "P", "P", "P", "P", "P"},
                        {"R", "K", "B", "Q", "A", "B", "K", "R"}};
                Chess_SP_2023.moves.clear();
                casL.clear();
                casC.clear();
                PiecesMovement.player = true;
                repaint();
            }catch (Exception ignored){}
        });
        this.add(d);

        //Tlacitko na vraceni tahu
        l = new JButton("Vraceni tahu");
        l.addActionListener(e -> {
            try {
                Chess_SP_2023.undoMove(Chess_SP_2023.moves.get(Chess_SP_2023.moves.size() - 1));
                repaint();

                if (PiecesMovement.player) {
                    casL.remove(casL.size() - 1);
                        PiecesMovement.player = false;
                } else {
                    casC.remove(casC.size() - 1);
                    PiecesMovement.player = true;
                }
            } catch (Exception ignored) {}
        });
        this.add(l);

        //Tlacitko na tah dopredu
        f = new JButton("Tah dopredu");
        f.addActionListener(e -> {
            try{
                Chess_SP_2023.makeMove(forMoves.get(forMoves.size() - 1));
                forMoves.remove(forMoves.size() - 1);
                repaint();
            }catch (Exception ignored){}
        });
        this.add(f);
        //Tlacitko na export PNG
        ex = new JButton("Export png");
        ex.addActionListener(e -> {
            saveImage();
        });
        this.add(ex);

        //tlacitko, ktere zapne UI
        UI = new JButton("Zapnout UI");
        UI.addActionListener(e -> {
            Chess_SP_2023.PCplay = true;
        });
        this.add(UI);

        //tlacitko, ktere vypne UI
        Pl = new JButton("Hrac jako cerny");
        Pl.addActionListener(e -> {
            Chess_SP_2023.PCplay = false;
        });
        this.add(Pl);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //Nastavi barvu pozadi.
        this.setBackground(new Color(123, 159, 45));


        //Tyto hodnoty slouzi pro vypocitani zvetsovani okna.
        double chessboard_width = 600;
        double chessboard_height = 600;

        //Tyto hodnoty slouzi pro vypocitani zvetsovani okna.
        scaleX = (this.getWidth() / chessboard_width);
        scaleY = (this.getHeight() / chessboard_height);
        scale = Math.min(scaleX, scaleY);
        fieldX = 600 * scale;
        fieldY = 600 * scale;
        chessBoxX = fieldX / 8;
        chessBoxY = fieldY / 8;
        posX = this.getWidth() / 2.0;
        posY = this.getHeight() / 2.0;
        chessboxPositionX = posX - (fieldX / 2);
        chessboxPositionY = posY - (fieldY / 2);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke((float) (2 * scale)));


        //Uhladi vykreslene cary.
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Nastavi pozice a velikosti tlacitek
        b.setLocation((int) (this.getWidth() - (100 * scale)), (int) (this.getHeight() - (350 * scale)));
        b.setSize((int) (100 * scale), (int) (50 * scale));

        d.setLocation(0, (int) (this.getHeight() - (350 * scale)));
        d.setSize((int) (100 * scale), (int) (50 * scale));

        l.setLocation(0, (int) (this.getHeight() - (50 * scale)));
        l.setSize((int) (100 * scale), (int) (50 * scale));

        f.setLocation(0, (int) (this.getHeight() - (100 * scale)));
        f.setSize((int) (100 * scale), (int) (50 * scale));

        ex.setLocation((int) (this.getWidth() - (100 * scale)), (int) (this.getHeight() - (50 * scale)));
        ex.setSize((int) (100 * scale), (int)( (50 * scale)));

        UI.setLocation(0, 0);
        UI.setSize((int) (100 * scale), (int)( (50 * scale)));

        Pl.setLocation((int) (this.getWidth() - (100 * scale)),0);
        Pl.setSize((int) (100 * scale), (int)( (50 * scale)));


        /**
         Vykresleni sachovnice.
         */
        boolean white = true;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (white) {
                    g2.setColor(new Color(227, 215, 194));
                } else {
                    g2.setColor(new Color(143, 67, 25));
                }
                g2.fill(new Rectangle2D.Double(chessboxPositionX + (x * chessBoxX), chessboxPositionY + (y * chessBoxY), chessBoxX, chessBoxY));
                white = !white;
            }
            white = !white;
        }


        /**
         Vykresleni figurek pomoci switch, ktery projizdi pole chessBoard a na konkretnich pismenech/pozicich vykresli figurku.
         */
        for (int i = 0; i < 64; i++) {
            switch (Chess_SP_2023.chessBoard[i / 8][i % 8]) {
                case "P" -> {
                    //Vykresli pesaka pro bileho hrace.
                    Pawn P = new Pawn(g2, chessBoxX, chessboxPositionX, chessboxPositionY, i, scale, Color.BLACK, Color.WHITE);
                }
                case "p" -> {
                    //Vykresli pesaka pro cerneho hrace.
                    Pawn p = new Pawn(g2, chessBoxX, chessboxPositionX, chessboxPositionY, i, scale, Color.WHITE, Color.BLACK);
                }


                case "R" -> {
                    //Vykresli vez pro bileho hrace.
                    Rook R = new Rook(g2, chessBoxX, chessboxPositionX, chessboxPositionY, i, Color.WHITE, Color.BLACK);
                }
                case "r" -> {
                    //Vykresli vez pro cerneho hrace.
                    Rook r = new Rook(g2, chessBoxX, chessboxPositionX, chessboxPositionY, i, Color.BLACK, Color.WHITE);
                }


                case "K" -> {
                    //Vykresli kone pro bileho hrace.
                    Knight K = new Knight(g2, chessBoxX, chessboxPositionX, chessboxPositionY, i, scale, Color.BLACK, Color.WHITE);
                }
                case "k" -> {
                    //Vykresli kone pro cerneho hrace.
                    Knight k = new Knight(g2, chessBoxX, chessboxPositionX, chessboxPositionY, i, scale, Color.WHITE, Color.BLACK);
                }


                case "B" -> {
                    //Vykresli strelce pro bileho hrace.
                    Bishop B = new Bishop(g2, chessBoxX, chessboxPositionX, chessboxPositionY, i, scale, Color.BLACK, Color.WHITE);
                }
                case "b" -> {
                    //Vykresli strelce pro cerneho hrace.
                    Bishop b = new Bishop(g2, chessBoxX, chessboxPositionX, chessboxPositionY, i, scale, Color.WHITE, Color.BLACK);
                }


                case "Q" -> {
                    //Vykresli kralovnu pro bileho hrace.
                    Queen Q = new Queen(g2, chessBoxX, chessboxPositionX, chessboxPositionY, i, scale, Color.WHITE, Color.BLACK);
                }
                case "q" -> {
                    // Vykresli kralovnu pro cerneho hrace.
                    Queen q = new Queen(g2, chessBoxX, chessboxPositionX, chessboxPositionY, i, scale, Color.BLACK, Color.WHITE);
                }


                case "A" -> {
                    // Vykresli krale pro cerneho hrace.
                    King K = new King(g2, chessBoxX, chessboxPositionX, chessboxPositionY, i, scale, Color.WHITE, Color.BLACK);
                }
                case "a" -> {
                    //Vykresli krale pro cerneho krale.
                    King k = new King(g2, chessBoxX, chessboxPositionX, chessboxPositionY, i, scale, Color.BLACK, Color.WHITE);
                }
            }
        }
        //Vykonava animaci tahu
        if(animaceTahu){
            try {
                AffineTransform save = new AffineTransform();
                if (x2 != 0) {
                    if ("P".equals(Chess_SP_2023.chessBoard[Character.getNumericValue(moveP.charAt(2))][Character.getNumericValue(moveP.charAt(3))])) {
                        Pawn p1 = new Pawn(g2, chessBoxX, chessboxPositionX, chessboxPositionY, x1, y1, x2, y2, startTime1, animaceTahu, scale, Color.BLACK, Color.WHITE);
                        p1 = null;
                        g2.setTransform(save);
                    } else if ("p".equals(Chess_SP_2023.chessBoard[Character.getNumericValue(moveP.charAt(2))][Character.getNumericValue(moveP.charAt(3))])) {
                        Pawn p1 = new Pawn(g2, chessBoxX, chessboxPositionX, chessboxPositionY, x1, y1, x2, y2, startTime1, animaceTahu, scale, Color.WHITE, Color.BLACK);
                        p1 = null;
                        g2.setTransform(save);
                    } else if ("B".equals(Chess_SP_2023.chessBoard[Character.getNumericValue(moveP.charAt(2))][Character.getNumericValue(moveP.charAt(3))])) {
                        Bishop p1 = new Bishop(g2, chessBoxX, chessboxPositionX, chessboxPositionY, x1, y1, x2, y2, startTime1, animaceTahu, scale, Color.BLACK, Color.WHITE);
                        p1 = null;
                        g2.setTransform(save);
                    } else if ("b".equals(Chess_SP_2023.chessBoard[Character.getNumericValue(moveP.charAt(2))][Character.getNumericValue(moveP.charAt(3))])) {
                        Bishop p1 = new Bishop(g2, chessBoxX, chessboxPositionX, chessboxPositionY, x1, y1, x2, y2, startTime1, animaceTahu, scale, Color.WHITE, Color.BLACK);
                        p1 = null;
                        g2.setTransform(save);
                    } else if ("A".equals(Chess_SP_2023.chessBoard[Character.getNumericValue(moveP.charAt(2))][Character.getNumericValue(moveP.charAt(3))])) {
                        King p1 = new King(g2, chessBoxX, chessboxPositionX, chessboxPositionY, x1, y1, x2, y2, startTime1, animaceTahu, scale, Color.WHITE, Color.BLACK);
                        p1 = null;
                        g2.setTransform(save);
                    } else if ("a".equals(Chess_SP_2023.chessBoard[Character.getNumericValue(moveP.charAt(2))][Character.getNumericValue(moveP.charAt(3))])) {
                        King p1 = new King(g2, chessBoxX, chessboxPositionX, chessboxPositionY, x1, y1, x2, y2, startTime1, animaceTahu, scale, Color.BLACK, Color.WHITE);
                        p1 = null;
                        g2.setTransform(save);
                    } else if ("K".equals(Chess_SP_2023.chessBoard[Character.getNumericValue(moveP.charAt(2))][Character.getNumericValue(moveP.charAt(3))])) {
                        Knight p1 = new Knight(g2, chessBoxX, chessboxPositionX, chessboxPositionY, x1, y1, x2, y2, startTime1, animaceTahu, scale, Color.BLACK, Color.WHITE);
                        p1 = null;
                        g2.setTransform(save);
                    } else if ("k".equals(Chess_SP_2023.chessBoard[Character.getNumericValue(moveP.charAt(2))][Character.getNumericValue(moveP.charAt(3))])) {
                        Knight p1 = new Knight(g2, chessBoxX, chessboxPositionX, chessboxPositionY, x1, y1, x2, y2, startTime1, animaceTahu, scale, Color.WHITE, Color.BLACK);
                        p1 = null;
                        g2.setTransform(save);
                    } else if ("Q".equals(Chess_SP_2023.chessBoard[Character.getNumericValue(moveP.charAt(2))][Character.getNumericValue(moveP.charAt(3))])) {
                        Queen p1 = new Queen(g2, chessBoxX, chessboxPositionX, chessboxPositionY, x1, y1, x2, y2, startTime1, animaceTahu, scale, Color.WHITE, Color.BLACK);
                        p1 = null;
                        g2.setTransform(save);
                    } else if ("q".equals(Chess_SP_2023.chessBoard[Character.getNumericValue(moveP.charAt(2))][Character.getNumericValue(moveP.charAt(3))])) {
                        Queen p1 = new Queen(g2, chessBoxX, chessboxPositionX, chessboxPositionY, x1, y1, x2, y2, startTime1, animaceTahu, scale, Color.BLACK, Color.WHITE);
                        p1 = null;
                        g2.setTransform(save);
                    } else if ("R".equals(Chess_SP_2023.chessBoard[Character.getNumericValue(moveP.charAt(2))][Character.getNumericValue(moveP.charAt(3))])) {
                        Rook p1 = new Rook(g2, chessBoxX, chessboxPositionX, chessboxPositionY, x1, y1, x2, y2, startTime1, animaceTahu, scale, Color.WHITE, Color.BLACK);
                        p1 = null;
                        g2.setTransform(save);
                    } else if ("r".equals(Chess_SP_2023.chessBoard[Character.getNumericValue(moveP.charAt(2))][Character.getNumericValue(moveP.charAt(3))])) {
                        Rook p1 = new Rook(g2, chessBoxX, chessboxPositionX, chessboxPositionY, x1, y1, x2, y2, startTime1, animaceTahu, scale, Color.BLACK, Color.WHITE);
                        p1 = null;
                        g2.setTransform(save);
                    }
                }
            }catch (Exception ignored){}
        }
        try {
            UIplay(startTime,time,timeS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        moveMarker(g2, moveP1, scale, chessBoxX, chessboxPositionX);
        b.repaint();
        l.repaint();
        lastmove(g2);
        MATEvent(Main.okno);
    }



    /**
     * Metoda, ktera detekuje stisknuti mysi.
     *
     * @param ev ceka na stisk tlacitka mysi.
     */
    @Override
    public void mousePressed(MouseEvent ev) {
        //Velikost sachovnice.
        double chessboard_width = 600;
        double chessboard_height = 600;
        //Tyto hodnoty slouzi pro vypocitani zvetsovani okna.
        double scaleX = (this.getWidth() / chessboard_width);
        double scaleY = (this.getHeight() / chessboard_height);
        double scale = Math.min(scaleX, scaleY);
        double fieldX = 600 * scale;
        double fieldY = 600 * scale;

        //Jestlize je uvnitr sachovnice vrati pozici mysi pri kliknuti.
        if (ev.getX() - ((this.getWidth() - fieldX) / 2) < fieldX && ev.getY() - ((this.getHeight() - fieldY) / 2) < fieldY) {
            mouseX = (int) (ev.getX() - ((this.getWidth() - fieldX) / 2));
            mouseY = (int) (ev.getY() - ((this.getHeight() - fieldY) / 2));
            moveP1 = "" + (int) (mouseY / chessBoxY) + (int) (mouseX / chessBoxX);
            Chess_SP_2023.chessBoard1[Character.getNumericValue(moveP1.charAt(0))][Character.getNumericValue(moveP1.charAt(1))] = true;
            y1 = mouseY;
            x1 = mouseX;
            repaint();
        }
    }

    /**
     * Metoda, ktera detekuje kdy bylo tlacitko mysi BUTTON1 pusteno.
     *
     * @param ev ceka na pusteni tlacitka mysi.
     */
    @Override
    public void mouseReleased(MouseEvent ev) {

        //Velikost sachovnice.
        double chessboard_width = 600;
        double chessboard_height = 600;
        //Tyto hodnoty slouzi pro vypocitani zvetsovani okna.
        double scaleX = (this.getWidth() / chessboard_width);
        double scaleY = (this.getHeight() / chessboard_height);
        double scale = Math.min(scaleX, scaleY);
        double fieldX = 600 * scale;
        double fieldY = 600 * scale;
        double chessBoxX = fieldX / 8;
        double chessBoxY = fieldY / 8;


        //Jestlize je uvnitr sachovnice vrati pozici mysi po pusteni tlacitka mysi BUTTON1.
        if (ev.getX() - ((this.getWidth() - fieldX) / 2) < fieldX && ev.getY() - ((this.getHeight() - fieldY) / 2) < fieldY) {
            newMouseX = (int) (ev.getX() - ((this.getWidth() - fieldX) / 2));
            newMouseY = (int) (ev.getY() - ((this.getHeight() - fieldY) / 2));
            x2 = newMouseX;
            y2 = newMouseY;

            if (ev.getButton() == MouseEvent.BUTTON1) {
                Chess_SP_2023.chessBoard1[Character.getNumericValue(moveP1.charAt(0))][Character.getNumericValue(moveP1.charAt(1))] = false;
                String dragMove;
                //Normalni tah
                dragMove = "" + (int) (mouseY / chessBoxY) + (int) (mouseX / chessBoxX) + (int) (newMouseY / chessBoxY) + (int) (newMouseX / chessBoxX) + Chess_SP_2023.chessBoard[(int) (newMouseY / chessBoxY)][(int) (newMouseX / chessBoxX)];
                String userPosibilities = PiecesMovement.possibleMoves();

                //Jestlize je to mozny pohyb, tak posune figurku, pokud neni, nic se nestane.
                if (userPosibilities.replaceAll(dragMove, "").length() < userPosibilities.length()) {
                    if (PiecesMovement.player) {
                        //pohyb, kdyz hraje bily
                        moveP = dragMove;
                        PiecesMovement.makeMove(dragMove);
                        startTime = System.currentTimeMillis();
                        double timeE = System.currentTimeMillis() / 1000.0;
                        double cas = timeE - timeS - time;
                        time = time + cas;
                        casC.add(cas);
                    } else {
                        if(!Chess_SP_2023.PCplay) {
                            // pohyb, kdyz hraje cerny
                            moveP = dragMove;
                            PiecesMovement.makeMove(dragMove);
                            startTime = System.currentTimeMillis();
                            double timeE = System.currentTimeMillis() / 1000.0;
                            double cas = timeE - timeS - time;
                            time = time + cas;
                            casL.add(cas);
                        }
                    }
                }
                try {
                    pripravAnimaci(x1, y1, x2, y2);
                }catch (Exception ignored){}
            }
            repaint();
        }
    }



    @Override
    public void mouseClicked(MouseEvent ev) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    /**
     * Tato metoda meri cas animace
     * @param orgX - startovni x
     * @param orgY - startovni y
     * @param toX - cilove x
     * @param toY - cilove y
     */
    public void pripravAnimaci(int orgX, int orgY, int toX, int toY){
        double rozdilX = ((double) toX) - (double) orgX;
        double rozdilY = ((double) toY) - (double) orgY;
        long time = 500;
        long elTime = System.currentTimeMillis() - startTime;
        int pocetOpak = (int)(Math.sqrt(rozdilX* rozdilX + rozdilY*rozdilY))/5;
        animaceTahu = true;
        startTime1 = System.currentTimeMillis();
        Timer tm = new Timer();
        tm.schedule(new TimerTask() {
            @Override
            public void run() {
                if((System.currentTimeMillis()-startTime1)>500){
                    tm.cancel();
                    newMouseX = 1000000000;
                    newMouseY = 1000000000;
                    animaceTahu = false;
                    Main.okno.repaint();
                }
                Main.okno.repaint();
            }
        },0,pocetOpak);
    }
    /**
     * Tato metoda zobrazuje jaky pohyb byl vykonan jako posledni
     * @param g2 - Graficky kontext
     */
    public void lastmove(Graphics2D g2) {
        g2.setColor(Color.GREEN);
        if (this.moveP != null && moveP1 !=null) {
            if (Chess_SP_2023.moves.size() > 0) {
                String move = Chess_SP_2023.moves.get(Chess_SP_2023.moves.size() - 1);
                g2.setStroke(new BasicStroke(5));
                g2.draw(new Rectangle2D.Double(chessboxPositionX + chessBoxX * Character.getNumericValue(move.charAt(1)), chessBoxX * Character.getNumericValue(move.charAt(0)) + chessboxPositionY, chessBoxX, chessBoxY));
                g2.draw(new Rectangle2D.Double(chessboxPositionX + chessBoxX * Character.getNumericValue(move.charAt(3)), chessBoxX * Character.getNumericValue(move.charAt(2)) + chessboxPositionY, chessBoxX, chessBoxY));
            }
        }
    }

    /**
     * Pomoci teto metody se ulozi obrazek sachovnice v PNG
     */
    public void saveImage(){
        BufferedImage bi = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        this.paint(g);
        g.dispose();
        try{ImageIO.write(bi,"png",new File("test.png"));}catch (Exception e) {}
    }


    /**
     * Tato metoda zobrazi v okne vysledek pokud dojde k matu ci remize.
     * @param okno
     */
   public void MATEvent(JFrame okno) {
        int result;
        if (!PiecesMovement.kingSafeC() && " ".equals(PiecesMovement.possibleMoves())) {
            konec = true;
            result = JOptionPane.showConfirmDialog(null, "Vyhrál černý hráč. Chceš hru Restartovat?", "Konec", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                try {
                    for (int i = Chess_SP_2023.moves.size() - 1; i >= 0; i--) {
                        Chess_SP_2023.undoMove(Chess_SP_2023.moves.get(i));
                    }
                    Chess_SP_2023.moves.clear();
                    casL.clear();
                    casC.clear();
                    PiecesMovement.player = true;
                    konec = false;
                    repaint();
                } catch (Exception ignored){}
            } else{
                okno.dispose();
            }
        }else if ((PiecesMovement.kingSafeC() && " ".equals(PiecesMovement.possibleMoves())) || (PiecesMovement.kingSafeL() && " ".equals(PiecesMovement.possibleMoves()))) {
            konec = true;
            result = JOptionPane.showConfirmDialog(null, "Remíza. Chceš hru Restartovat?", "Konec", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                for (int i = Chess_SP_2023.moves.size() - 1; i >= 0; i--) {
                    Chess_SP_2023.undoMove(Chess_SP_2023.moves.get(i));
                }
                Chess_SP_2023.moves.clear();
                casL.clear();
                casC.clear();
                PiecesMovement.player = true;
                konec = false;
                repaint();
            } else {
                okno.dispose();
            }
        }else if (!PiecesMovement.kingSafeL() && " ".equals(PiecesMovement.possibleMoves())) {
            konec = true;
            result = JOptionPane.showConfirmDialog(null, "Vyhrál bílý hráč. Chceš hru Restartovat?", "Konec", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                for (int i = Chess_SP_2023.moves.size() - 1; i >= 0; i--) {
                    Chess_SP_2023.undoMove(Chess_SP_2023.moves.get(i));
                }
                Chess_SP_2023.moves.clear();
                casL.clear();
                casC.clear();
                PiecesMovement.player = true;
                konec = false;
                repaint();
            } else {
                okno.dispose();
            }
        }
    }

    /**
     * Tato metoda zobrazuje na jake policka muze figurka jit
     * @param g2 - graficky kontext
     * @param moveP1 - pozice, na kterou se kliklo mysi
     * @param scale - pomaha drzet pozici, kdyz se zvetsi okno
     * @param chessBoxX - velikost jednoho policka
     * @param chessBoxPositionX - X pozice policka
     */
    public void moveMarker(Graphics2D g2, String moveP1, double scale, double chessBoxX, double chessBoxPositionX) {
        ArrayList<String> markMoves1 = new ArrayList<>();
        List<String> moveM = new ArrayList<>();
        g2.setStroke(new BasicStroke(5));
        g2.setColor(Color.GREEN);
        String e = PiecesMovement.list1.toString();
        for(int i = 0; i + 5 <= e.length(); i += 5) {
            moveM.add(e.substring(i + 1, i + 6).trim());
        }
        if (moveP1 != null) {
            for (String m : moveM) {
                if (Character.getNumericValue(moveP1.charAt(0)) == Character.getNumericValue(m.charAt(0)) && Character.getNumericValue(moveP1.charAt(1)) == Character.getNumericValue(m.charAt(1))) {
                    markMoves1.add(m);
                }
            }
            if (Chess_SP_2023.chessBoard1[Character.getNumericValue(moveP1.charAt(0))][Character.getNumericValue(moveP1.charAt(1))]) {
                for (String h : markMoves1) {
                    g2.fill(new Arc2D.Double(chessBoxPositionX + (30 * scale) + chessBoxX * (Character.getNumericValue(h.charAt(3))), chessBoxX * Character.getNumericValue(h.charAt(2)) + (30 * scale), 17.5 * scale, 17.5 * scale, 0, 360, Arc2D.OPEN));
                }
            }
        }
        while(!markMoves1.isEmpty()){
            markMoves1.remove(markMoves1.size()-1);
        }
    }

    /**
     * Tato metoda umoznuje hraci hrat proti Pocitaci
     * @param time - parametr casu delky tahu
     * @param startTime - sleduje cas v dobe odehrani
     * @param timeS - mezicas
     * @throws InterruptedException
     */
    public void UIplay(long startTime, double time, double timeS) throws InterruptedException {
        if (PiecesMovement.listUI.length() != 0) {
            String e = PiecesMovement.listUI.toString();
            ArrayList<String> UImoves = new ArrayList<>();
            for (int i = 0; i + 5 <= e.length(); i += 5) {
                UImoves.add(e.substring(i + 1, i + 6).trim());
            }
            Random r = new Random();
            if (Chess_SP_2023.PCplay) {
                if (Chess_SP_2023.UIturnM(PiecesMovement.player)) {
                    Chess_SP_2023.makeMove(UImoves.get(r.nextInt(UImoves.size() - 1)));
                    startTime = System.currentTimeMillis();
                    double timeE = System.currentTimeMillis() / 1000.0;
                    double cas = timeE - timeS - time;
                    time = time + cas;
                    casL.add(cas);
                    repaint();
                } else {
                    return;
                }
            }
            for (int i = 0; i < UImoves.size(); i++) {
                UImoves.remove(i);
            }
        }
    }
}


