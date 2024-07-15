package com.card.printing.app.cardprinting;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class DashboardController {
    public PieChart pieChart;
    public Label handoverStat;
    public Label qaRejected;
    public Label qaProcessed;
    public Label availBatch;
    public BarChart barChart;


    @FXML
    private void initialize() {
        // Sample data
        int qualityProcessed = 24;
        int qualityRejected = 12;
        System.out.println("handoverStat"+Integer.parseInt(handoverStat.getText()));

        // Create PieChart data
        PieChart.Data processedData = new PieChart.Data("Quality Processed", Integer.parseInt(qaProcessed.getText()));
        PieChart.Data rejectedData = new PieChart.Data("Quality Rejected", Integer.parseInt(qaRejected.getText()));

        // Add data to PieChart
        pieChart.getData().addAll(processedData, rejectedData);

        // Customize the PieChart (optional)
        pieChart.setTitle("Quality Status");
        pieChart.setLegendVisible(true);
        pieChart.setLabelsVisible(true);

        // Sample data
//        int availableBatch = 10;
//        int qaProcessed = 24;
//        int qaRejected = 12;
//        int handoverStatus = 46;

        // Create a series and add data
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Card Printing Status");
        series.getData().add(new XYChart.Data<>("Available Batch", Integer.parseInt(availBatch.getText())));
        series.getData().add(new XYChart.Data<>("QA Processed", Integer.parseInt(qaProcessed.getText())));
        series.getData().add(new XYChart.Data<>("QA Rejected",  Integer.parseInt(qaRejected.getText())));
        series.getData().add(new XYChart.Data<>("Handover Status",  Integer.parseInt(handoverStat.getText())));

        // Add series to BarChart
        barChart.getData().add(series);

        // Optional: Customize the BarChart
        barChart.setTitle("Card Printing Status Overview");
        barChart.setCategoryGap(10);
        barChart.setBarGap(5);
    }
}
