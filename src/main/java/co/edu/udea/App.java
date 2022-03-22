package co.edu.udea;

/*
 Reto 11
 Hecho por:
 Efraín García(CC.1001370984)
 Samuel Acevedo(CC.1001016099)
*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();

    }


    public final String ruta = "src/main/BTC-USD.csv";
    public final String ruta2 = "src/main/Punto1.txt";

    public static void escribirLineaPorLinea(String rutaLeer , String rutaEscribir ) throws IOException , NullPointerException{

        Path miRutaEscribir = Paths.get(rutaEscribir);
        StringBuilder cadena = new StringBuilder();
        String miConceptoCierre;
        String[] palabras;
        int i = 1;
        while (leerLineaPorLinea(i) != null) {

            palabras = leerLineaPorLinea(i);
            assert palabras != null;
            miConceptoCierre = conceptoCierre(palabras[4]);
            cadena.append("Fecha: ").append(palabras[0]).append("\t").append("Concepto de cierre: ").append(miConceptoCierre).append("\n");
            i++;
        }

        Files.writeString(miRutaEscribir, cadena.toString());
    }

    public static String[] leerLineaPorLinea(int x) throws IOException {
        String ruta = "src/main/BTC-USD.csv";
        Path miRutaLeer = Paths.get(ruta);
        List<String> lineasArchivoLeer;
        String[] palabrasSeparadas;
        lineasArchivoLeer = Files.readAllLines(miRutaLeer);
        try {
            String lineaActual = lineasArchivoLeer.get(x);
            palabrasSeparadas = lineaActual.split(",");
        } catch (IndexOutOfBoundsException k) {
            return null;
        }

        return palabrasSeparadas;

    }

    public static String conceptoCierre(String c) {
        double valor = Double.parseDouble(c);


        if (valor < 30000){
            return "MUY BAJO";
        }
        else if (valor < 40000){
            return "BAJO";
        }
        else if (valor < 50000){
            return "MEDIO";
        }
        else if (valor < 60000){
            return "ALTO";
        }
        else return "MUY ALTO";
    }

    public static float media() throws IOException {
        String ruta = "src/main/BTC-USD.csv";
        int i = 1;
        float sumaElementos=0;
        String[] palabras;
        while (leerLineaPorLinea(i) != null) {
            palabras = leerLineaPorLinea(i);
            assert palabras != null;
            sumaElementos += Float.parseFloat(palabras[1]);
            i++;
        }
        return sumaElementos/ i-1;
    }

    public static double desviacionEstandar() throws IOException {
        String ruta = "src/main/BTC-USD.csv";
        float mediaDeLosDatos = media();
        float sumatoriaDistanciasCuadradas=0;
        float distanciaConLaMedia;

        int i = 1;
        String[] palabras;
        while (leerLineaPorLinea(i) != null) {
            palabras = leerLineaPorLinea(i);
            assert palabras != null;
            distanciaConLaMedia = Float.parseFloat(palabras[1]) - mediaDeLosDatos;
            sumatoriaDistanciasCuadradas += Math.pow(distanciaConLaMedia,2);
            i++;
        }
        return Math.sqrt(sumatoriaDistanciasCuadradas/i-1);
    }

    public static String[] valorMax() throws IOException {
        String ruta = "src/main/BTC-USD.csv";
        int i=1;
        int j=i+1;
        double datoMax = 0;
        String[] palabras;
        String[] palabrasSiguientes;
        String fechaDato = null;
        while (leerLineaPorLinea(j) != null) {
            palabras = leerLineaPorLinea(i);
            palabrasSiguientes = leerLineaPorLinea(j);
            assert palabras != null;
            assert palabrasSiguientes != null;
            if(Double.parseDouble(palabras[2]) > Double.parseDouble(palabrasSiguientes[2])){
                fechaDato = palabras[0];
                datoMax = Double.parseDouble(palabras[2]);
            }else{
                fechaDato = palabrasSiguientes[0];
                datoMax = Double.parseDouble(palabrasSiguientes[2]);
                i=j;
            }
            j++;
        }
        return new String[]{fechaDato,Double.toString(datoMax)};
    }

    public static String[] valorMin() throws IOException {
        String ruta = "src/main/BTC-USD.csv";
        int i=1;
        int j=i+1;
        double datoMin = 0;
        String[] palabras;
        String[] palabrasSiguientes;
        String fechaDato = null;
        while (leerLineaPorLinea(j) != null) {
            palabras = leerLineaPorLinea(i);
            palabrasSiguientes = leerLineaPorLinea(j);
            assert palabras != null;
            assert palabrasSiguientes != null;
            if(Double.parseDouble(palabras[3]) < Double.parseDouble(palabrasSiguientes[3])){
                fechaDato = palabras[0];
                datoMin = Double.parseDouble(palabras[3]);
            }else{
                fechaDato = palabrasSiguientes[0];
                datoMin = Double.parseDouble(palabrasSiguientes[3]);
                i=j;
            }
            j++;
        }
        return new String[]{fechaDato,Double.toString(datoMin)};
    }



}