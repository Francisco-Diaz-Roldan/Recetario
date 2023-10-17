package com.example.diseno_interfaces;

import com.example.diseno_interfaces.models.Receta;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class VentanaPrincipalController implements Initializable {


    @FXML
    private Label welcomeText;
    @FXML
    private TextField txtNombre;
    @FXML
    private Slider sliderDuracion;
    @FXML
    private ComboBox comboDificultad;
    @FXML
    private ListView<String> listTipo;
    @FXML
    private Button btnAñadir;
    @FXML
    private TableView<Receta> tabla;
    @FXML
    private TableColumn <Receta, String> cNombre;//Recibe elementos Receta y devuelve elementos String -> Se va a extraer un String de Receta
    @FXML
    private TableColumn <Receta, String>  cDuracion;
    @FXML
    private TableColumn <Receta, String>  cDificultad;
    @FXML
    private TableColumn <Receta, String>  cTipo;
    @FXML
    private Label info;
    @FXML
    private Label lblDuracion;

    @FXML
    protected void insertarReceta(ActionEvent actionEvent) {


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        //Si el combo es estático
        comboDificultad.getItems().add("Fácil");
        comboDificultad.getItems().add("Medio");
        comboDificultad.getItems().add("Difícil");

        comboDificultad.getItems().addAll("básica","hard", "pro");
        */
        //Si el combo no es estático
        ObservableList<String> datos = FXCollections.observableArrayList();//Es como un arrayList especial para JavaFX
        datos.addAll("Fácil", "Medio", "Difícil");
        comboDificultad.setItems(datos);
        comboDificultad.getSelectionModel().selectFirst();//Selecciona el primer elemento por defecto

        sliderDuracion.setValue(60);//Los slider llevan double
        lblDuracion.setText(Math.round(sliderDuracion.getValue()) + " min");//Le pasamos el valor redondeado

        listTipo.getItems().addAll("Desayuno", "Segundo desayuno", "Almuerzo", "SobreAlmuerzo", "Merienda", "Cena", "Recena", "Poscena");
        listTipo.getSelectionModel().select(0);//Selecciona el primer elemento por defecto

        cNombre.setCellValueFactory((fila) -> {
            String nombre = fila.getValue().getNombre().toUpperCase();
            return new SimpleStringProperty(nombre);
        });//Le indicamos de dónde sacamos el valor de esa columna
        cDificultad.setCellValueFactory((fila) -> new SimpleStringProperty( fila.getValue().getDificultad()));//Le indicamos de dónde sacamos el valor de esa columna

        tabla.getItems().add(new Receta("Tacos de carne asada", "Almuerzo", 45, "Fácil"));
        tabla.getItems().add(new Receta("Huevos revueltos con tocino", "Desayuno", 15, "Moderada"));
        tabla.getItems().add(new Receta("Sándwich de jamón y queso", "Merienda", 10, "Fácil"));
        tabla.getItems().add(new Receta("Pollo a la parrilla con verduras", "Almuerzo", 60, "Moderada"));
        tabla.getItems().add(new Receta("Avena con frutas", "Desayuno", 20, "Fácil"));
        tabla.getItems().add(new Receta("Ensalada de atún", "Almuerzo", 30, "Moderada"));
        tabla.getItems().add(new Receta("Pizza casera", "Cena", 35, "Moderada"));
        tabla.getItems().add(new Receta("Batido de frutas", "Merienda", 5, "Fácil"));
        tabla.getItems().add(new Receta("Sopa de pollo casera", "Cena", 40, "Difícil"));
        tabla.getItems().add(new Receta("Pancakes con sirope de arce", "Desayuno", 25, "Moderada"));
    }

    @FXML
    public void actualizarDuracion(Event event) {//Le hemos pasado tambien el Dragged en el visual
        lblDuracion.setText(Math.round(sliderDuracion.getValue()) + " min");//Para que se sincronice cuandp arrastramos el ratón

    }
}
//Importante actualizar con btn derecho del raton