import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *  Graf ukazující délku jednoho tahu.
 * @author Filip Chlad
 */
public class Graf extends JPanel {

    ArrayList<Double> listC;
    ArrayList<Double> listL;

    public Graf (ArrayList<Double> listC, ArrayList<Double> listL) {
        this.setPreferredSize(new Dimension(800,600));
        this.listC = listC;
        this.listL = listL;
    }

    public JFreeChart createBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //Max tahů
        int M = Math.max(listC.size(), listL.size());


        for (int i = 0; i < M; i++) {
            try {
                dataset.addValue(listC.get(i), "White", String.valueOf(i+1));
            } catch (Exception ignored) {}
            try {
                dataset.addValue(listL.get(i), "Black", String.valueOf(i+1));
            } catch (Exception ignored) {}
        }


        JFreeChart chart = ChartFactory.createBarChart("Časy jednotlivých tahů obou hráču", "Tah", "Čas [s]", dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.DARK_GRAY);

        CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2} s", NumberFormat.getInstance(Locale.US)));
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelFont(new Font("Caliri", Font.PLAIN, 9));


        BarRenderer br = (BarRenderer) renderer;
        br.setItemMargin(0.05);
        br.setBarPainter(new StandardBarPainter());
        br.setSeriesPaint(0, new Color(255, 255, 255));
        br.setSeriesPaint(1, new Color(0,0,0));
        return chart;
    }
}
