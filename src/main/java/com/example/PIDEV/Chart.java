package com.example.PIDEV;

import java.awt.Color;
import java.util.List;
import javax.swing.JFrame;

import entity.Dons;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import service.ServiceDons;

public class Chart {
    private List<Dons> donations;
    private ServiceDons serviceDons;

    public Chart(List<Dons> donations) {
        this.serviceDons = new ServiceDons();
        this.donations = donations;
    }

    public Chart(ServiceDons serviceDons) {
    }

    public void displayChart() {
        // Create a dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Dons donation : donations) {
            int totalAmount = serviceDons.calculateTotalAmountByTitle((donation.getTitle()));
            dataset.setValue(totalAmount, "Amount Donated", donation.getTitle());
        }

        // Create a bar chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Amount Donated by Title",
                "Title",
                "Total Amount Donated",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );
        chart.setBackgroundPaint(Color.WHITE);

        // Display the chart in a JFrame
        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame("Donation Chart");
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

}
