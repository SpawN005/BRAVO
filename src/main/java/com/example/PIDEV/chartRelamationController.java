package com.example.PIDEV;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
//import com.itextpdf.text.Element;
import javafx.fxml.Initializable;

import utils.DataSource;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.scene.*;
import javafx.stage.Stage;


import javafx.scene.chart.XYChart;
import java.text.DecimalFormat;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;


public class chartRelamationController implements Initializable {
    @FXML
    private AnchorPane chartNode;
    public XYChart.Series buildDataLineChart() {
        XYChart.Series series = new XYChart.Series();
        series.setName("Nombre de reclamations par jour");
        XYChart.Series d;

        try {
            String requete = "SELECT Reclamation.date_creation,COUNT(Reclamation.id) as nbr FROM Reclamation group by DAYNAME(Reclamation.date_creation)";

            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next())
            {
                series.getData().add(new XYChart.Data(rs.getString(1), rs.getInt(2)));
            }

            return series;

        } catch (Exception e) {

            System.out.println("Error on DB connection BuildDataLineChart");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());

        }
        return series;
    }
    public void convertirPDF(ActionEvent event) {
    }

    public void lineChart(ActionEvent event) {
        //LineChart<Date, Number> lineChart = new LineChart<>(dateAxis, numberAxis, series);
        double total = 0;
        DecimalFormat df2 = new DecimalFormat(".##");
        Stage stage = new Stage();
        Scene scene = new Scene(new Group());
        stage.setTitle("Nombre de reclamations par jour");
        stage.setWidth(600);
        stage.setHeight(600);

        //defining the axes
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        //creating the chart
        final LineChart<String,Number> lineChart =new LineChart(xAxis,yAxis);

        lineChart.getData().add(buildDataLineChart());
        ((Group) scene.getRoot()).getChildren().add(lineChart);
        stage.setScene(scene);
//     chartNode.getChildren().clear();
 //       chartNode.getChildren().setAll(lineChart);

    }

    public void detailleEvent(ActionEvent event) {
    }

    public void detailleOeuvre(ActionEvent event) {
    }

    public void globalChart(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
