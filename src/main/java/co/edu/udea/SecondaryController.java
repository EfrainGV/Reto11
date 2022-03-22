package co.edu.udea;

import java.io.IOException;
import java.util.Arrays;

import javafx.beans.Observable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SecondaryController {

    public Label labelOperaciones;
    public TableView tablaDatos;
    public TableColumn<String, String> columnaFecha;
    public TableColumn<Double, Double> columnaApertura;
    public TableColumn<Double, Double> columnaAlto;
    public TableColumn<Double, Double> columnaBajo;
    public TableColumn<Double, Double> columnaCierre;
    public TableColumn<Double, Double> columnaCierreAjustado;
    public TableColumn<Integer, Integer> columnaVolumen;


    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }


    public void media(ActionEvent actionEvent) throws IOException {
        labelOperaciones.setText("El promedio es: " + App.media());
    }

    public void desviacionEstandar(ActionEvent actionEvent) throws IOException {
        labelOperaciones.setText("La desviación estandar es: " + App.desviacionEstandar());
    }

    public void valorMax(ActionEvent actionEvent) throws IOException {
        labelOperaciones.setText("El mayor precio es: " + Arrays.toString(App.valorMax()));
    }

    public void valorMin(ActionEvent actionEvent) throws IOException {
        labelOperaciones.setText("El menor precio es: " + Arrays.toString(App.valorMin()));
    }

    public void generarTabla(ActionEvent actionEvent) throws IOException {
        int i = 1;
        String[] datosSeparados;
        ObservableList<Object> lista = FXCollections.observableArrayList();
        while (App.leerLineaPorLinea(i) != null){
            datosSeparados = App.leerLineaPorLinea(i);
            String[] finalDatosSeparados = datosSeparados;
            assert finalDatosSeparados != null;
            columnaFecha.setCellValueFactory(c -> new SimpleStringProperty(finalDatosSeparados[0]));
            columnaApertura.setCellValueFactory(c -> new SimpleDoubleProperty(Double.parseDouble(finalDatosSeparados[1])).asObject());
            columnaAlto.setCellValueFactory(c -> new SimpleDoubleProperty(Double.parseDouble(finalDatosSeparados[2])).asObject());
            columnaBajo.setCellValueFactory(c -> new SimpleDoubleProperty(Double.parseDouble(finalDatosSeparados[3])).asObject());
            columnaCierre.setCellValueFactory(c -> new SimpleDoubleProperty(Double.parseDouble(finalDatosSeparados[4])).asObject());
            columnaCierreAjustado.setCellValueFactory(c -> new SimpleDoubleProperty(Double.parseDouble(finalDatosSeparados[5])).asObject());
            columnaVolumen.setCellValueFactory(c -> new SimpleIntegerProperty(Integer.parseInt(finalDatosSeparados[6])).asObject());
            lista.add(finalDatosSeparados);
            i++;
            tablaDatos.setItems(lista);
        }

    }
}