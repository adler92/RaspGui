package com.example.raspgui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;


public class HelloController extends Application {

    private double str1;
    private double str2;

    //defining the axes
    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();

    //defining the axes
    final NumberAxis xAxis2 = new NumberAxis();
    final NumberAxis yAxis2 = new NumberAxis();

    //creating the chart
    final LineChart<Number,Number> lineChart =
            new LineChart<Number,Number>(xAxis,yAxis);

    //creating the chart
    final LineChart<Number,Number> lineChart2 =
            new LineChart<Number,Number>(xAxis2,yAxis2);

    //defining a series
    XYChart.Series series = new XYChart.Series();

    //defining a series
    XYChart.Series series2 = new XYChart.Series();
    @Override public void start(Stage stage) throws InterruptedException {
        stage.setTitle("Line Chart Sample");

        xAxis.setLabel("Number of Month");
        lineChart.setTitle("Stock Monitoring, 2010");
        series.setName("Graph Color");

/*--------------------------------------------------------------------------*/

        xAxis2.setLabel("Number of Month");
        lineChart2.setTitle("Stock Monitoring, 2010");
        series2.setName("Graph Color");

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                test();
                InsertTempAndHum();
            }
        });

        HBox hBox = new HBox(lineChart, lineChart2);

        Scene scene  = new Scene(hBox,1000,600);
        lineChart.getData().add(series);
        lineChart2.getData().add(series2);

        stage.setScene(scene);
        stage.show();
    }
    private void InsertTempAndHum() {
        try {

            // bruger localtime klassen til at få hvad klokken er lige nu i forhold til sekunder.
            LocalTime localTime = LocalTime.now();
            double time = (double) localTime.getSecond();

            System.out.println(time);

            // giver vores chart data og tilføjer tekst til vores labels.
            series.getData().add(new XYChart.Data<Number, Number>(time,str1));
            series2.getData().add(new XYChart.Data<Number, Number>(time,str2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void test(){
        Socket sock = null;
        try {
            sock = new Socket("192.168.1.25", 1024);
            System.out.println("Connection established");
            BufferedReader dIS = new BufferedReader(new InputStreamReader(sock.getInputStream()));

            String humid = dIS.readLine();
            String temp = dIS.readLine();

            System.out.println("Humid" + humid);
            System.out.println("Temp" + temp);

            str1 = Double.parseDouble(humid);
            str2 = Double.parseDouble(temp);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

}