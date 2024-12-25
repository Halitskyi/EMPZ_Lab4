import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;

import javax.swing.*;
import java.util.Random;

public class Main extends JFrame {

    public Main(String title, int n) {
        super(title);

        // Генерація вибірок
        double[] normalData = generateNormalSample(20 * n, 0.5 * n, 0.5 * n);
        double[] exponentialData = generateExponentialSample(20 * n, n);

        // Побудова гістограм
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("Нормальний розподіл", normalData, 5, 0, n);
        dataset.addSeries("Показниковий розподіл", exponentialData, 5, 0, n);

        // Створення діаграми
        JFreeChart histogram = ChartFactory.createHistogram(
                "Гістограми розподілів",
                "Значення",
                "Частота",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Відображення графіка
        ChartPanel chartPanel = new ChartPanel(histogram);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }

    // Генерація вибірки нормального розподілу
    private static double[] generateNormalSample(int size, double mean, double stdDev) {
        Random random = new Random();
        double[] data = new double[size];
        for (int i = 0; i < size; i++) {
            data[i] = mean + stdDev * random.nextGaussian();
        }
        return data;
    }

    // Генерація вибірки показникового розподілу
    private static double[] generateExponentialSample(int size, double lambda) {
        Random random = new Random();
        double[] data = new double[size];
        for (int i = 0; i < size; i++) {
            data[i] = -Math.log(1 - random.nextDouble()) * lambda;
        }
        return data;
    }

    public static void main(String[] args) {
        int N = 1;
        SwingUtilities.invokeLater(() -> {
            Main example = new Main("Гістограми вибірок", N);
            example.setSize(800, 600);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}
