package com.example.raspgui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class HelloController extends Application {

    @Override public void start(Stage stage) {
        stage.setTitle("Line Chart Sample");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Graph Color");
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 23));

/*--------------------------------------------------------------------------*/

        //defining the axes
        final NumberAxis xAxis2 = new NumberAxis();
        final NumberAxis yAxis2 = new NumberAxis();
        xAxis2.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number,Number> lineChart2 =
                new LineChart<Number,Number>(xAxis2,yAxis2);

        lineChart2.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Graph Color");
        //populating the series with data
        series2.getData().add(new XYChart.Data(1, 23));


        HBox hBox = new HBox(lineChart, lineChart2);


        Scene scene  = new Scene(hBox,1000,600);
        lineChart.getData().add(series);
        lineChart2.getData().add(series2);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}