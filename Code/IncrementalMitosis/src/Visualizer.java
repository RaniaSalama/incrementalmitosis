import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.DefaultXYDataset;

public class Visualizer {

	static JFreeChart createChart(ArrayList<Cluster> clusters,
			HashMap<Integer, String> map, int size) {
		int clusterCount = 0;
		DefaultXYDataset dataset = new DefaultXYDataset();
		if (clusters != null) {
			for (Cluster cluster : clusters) {
				ArrayList<Pattern> patterns = cluster.getPatterns();
				double[][] value = new double[2][patterns.size()];
				for (int j = 0; j < patterns.size(); j++) {
					String[] mapPattern = map.get(patterns.get(j).getId())
							.split("\\s+");
					value[0][j] = Double.parseDouble(mapPattern[0]);
					value[1][j] = Double.parseDouble(mapPattern[1]);
				}
				dataset.addSeries(clusterCount++, value);
			}
		}
		JFreeChart chart = ChartFactory.createScatterPlot("Incremental Mitosis",
				"Category", "Value", dataset, PlotOrientation.VERTICAL, true,
				true, false);
		chart.clearSubtitles();
		XYPlot plot = (XYPlot) chart.getPlot();
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setRange(0, 450);
		NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
		domainAxis.setRange(0, 800);
		Color brown = new Color(156, 93, 82);
		Color maroon = new Color(139, 28, 98);
		XYItemRenderer renderer = plot.getRenderer();
		Color[] colors = { Color.red, Color.blue, Color.BLACK, Color.green,
				maroon, brown, Color.yellow, Color.MAGENTA, Color.white,
				Color.pink, Color.cyan };
		if (clusters != null) {
			for (int i = 0; i < clusters.size(); i++) {
				renderer.setSeriesPaint(i, colors[i % colors.length]);
			}
		}
		return chart;

	}

}
