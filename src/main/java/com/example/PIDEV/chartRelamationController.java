package com.example.PIDEV;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.*;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.DataSource;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class chartRelamationController implements Initializable {
    @FXML
    private AnchorPane chartPane;
    private PieChart pieChart;

    Stage window;
    private Stage primaryStage;


    public XYChart.Series DataLineChart() {

        // Création de la série de données pour le graph
        XYChart.Series<String, Number> series = new XYChart.Series();
        series.setName("Nombre de reclamations par jour");
        try {
            String requete = "SELECT Reclamation.date_creation,COUNT(Reclamation.id) as nbr FROM Reclamation group by DAYNAME(Reclamation.date_creation)";

            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next())
            {
                // Ajout des données à la série de données
                series.getData().add(new XYChart.Data(rs.getString(1), rs.getInt(2)));
            }

            return series;

        } catch (Exception e) {

            System.out.println("une erreur existante au niveau de datalinechart");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }
        return series;
    }


    public void lineChart(ActionEvent event) throws IOException {

        // Création de l'axe des abscisses
         CategoryAxis xAxis = new CategoryAxis();
         xAxis.setLabel("Jour");
         // Création de l'axe des ordonnées (nb réclamation)
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Nombre de réclamations");

        // Création du graphique de type courbe
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Nombre de réclamations par jour");
        lineChart.getData().add(DataLineChart());
        StackPane root = new StackPane();
        root.getChildren().add(lineChart);

        chartPane.getChildren().add(lineChart);

        /* //Création d'une nouvelle scène avec le graphique
        Scene scene = new Scene(root, 800, 600);
        // Création d'une nouvelle fenêtre
        Stage stage = new Stage();
        stage.setScene(scene);
         // Affichage de la fenêtre
        stage.show();*/


    }
    public void barChart(ActionEvent event) throws SQLException, IOException {
        // Création des axes
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Type de réclamation");
        yAxis.setLabel("Nombre de réclamations");

        // Création du BarChart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Nombre de réclamations par type");

        String requete = "SELECT typeReclamation, COUNT(TypeReclamation.id) FROM TypeReclamation GROUP BY typeReclamation";
        // Récupération des données depuis la base de données
        Statement stmt = DataSource.getInstance().getCnx().createStatement();
        ResultSet rs = stmt.executeQuery(requete);

        // Ajout des données à la série de données
        Map<String, Integer> dataMap = new HashMap<>();
        while (rs.next()) {
            String typeReclamation = rs.getString("typeReclamation");
            int count = rs.getInt(2);
            dataMap.put(typeReclamation, count);
        }

        ObservableList<XYChart.Series<String, Number>> barChartData = FXCollections.observableArrayList();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Réclamations");
        for (String type : dataMap.keySet()) {
            series.getData().add(new XYChart.Data<>(type, dataMap.get(type)));
        }
        barChartData.add(series);

        barChart.setData(barChartData);
        barChart.setLegendVisible(false);
        // Ajout du BarChart au conteneur chartPane
        chartPane.getChildren().add(barChart);

        // Création d'une nouvelle scène avec le BarChart
       /*Scene scene = new Scene(new Group(barChart), 800, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();*/


        // Supprimer le PieChart existant s'il y en a un
        /*for (Node node : chartPane.getChildren()) {
            if (node instanceof PieChart) {
                chartPane.getChildren().clear();
                break;
            }
        }
        Créer et ajouter le nouveau BarChart
        chartPane.getChildren().add(barChart);*/


    }

    public void globalChart(ActionEvent event) {
        List<PieChart.Data> myList = new ArrayList<PieChart.Data>();
        ResultSet rs = null;
        PieChart.Data d;

        try {

            String requete = "SELECT Reclamation.etat,COUNT(Reclamation.id) as nbr FROM Reclamation group by Reclamation.etat";

            Statement pst = DataSource.getInstance().getCnx().prepareStatement(requete);
            rs = pst.executeQuery(requete);
            while (rs.next()) {

                if (rs.getObject(1) == null) {
                    System.out.println(rs.getString(1));
                    d = new PieChart.Data("Autre ", rs.getInt(2));
                } else {
                    d = new PieChart.Data(rs.getString(1), rs.getInt(2));
                }
                myList.add(d);
            }
            // Création d'un nouvel objet PieChart avec les données de la liste
            PieChart chart = new PieChart(FXCollections.observableArrayList(myList));
            chart.setTitle("Réclamations par Etat");
            /*Scene scene = new Scene(new Group(chart), 800, 600);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();*/
            chartPane.getChildren().add(chart); // ajout du PieChart au conteneur chartPane

        } catch (Exception e) {

            System.out.println("Erreur de communication avec la bd");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());

        }

    }

    public void convertirPDF(ActionEvent event) throws IOException {
        WritableImage chartImage = chartPane.snapshot(new SnapshotParameters(), null);

// Création du document PDF
        String outputPdfPath = "C:/Users/ferie/OneDrive/Bureau/document.pdf";
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outputPdfPath));
        Document document = new Document(pdfDocument);

// Conversion de l'image en PDF
        BufferedImage bufferedImage = new BufferedImage((int) chartImage.getWidth(), (int) chartImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
       // Image pdfImage = new Image(ImageDataFactory.create(SwingFXUtils.fromFXImage(chartImage, bufferedImage)));
        //document.add(pdfImage);

// Fermeture du document
        document.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
