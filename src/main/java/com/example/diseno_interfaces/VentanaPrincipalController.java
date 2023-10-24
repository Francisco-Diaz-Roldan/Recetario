package com.example.diseno_interfaces;

import com.example.diseno_interfaces.models.Receta;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;


public class VentanaPrincipalController implements Initializable {


    @FXML
    private Label welcomeText;
    @FXML
    private ComboBox<Receta> comboRecetas;
    @FXML
    private TextField txtNombre;
    @FXML
    private ToggleGroup dificultad;
    @FXML
    private Label lblDuracion;
    @FXML
    private Slider sliderDuracion;
    @FXML
    private ComboBox comboDificultad;
    @FXML
    private ListView listTipo;
    @FXML
    private Button btnAñadir;
    @FXML
    private TableView<Receta> tabla;
    @FXML
    private TableColumn<Receta, String> cNombre;
    @FXML
    private TableColumn<Receta, String> cDuracion;
    @FXML
    private TableColumn<Receta, String> cDificultad;
    @FXML
    private TableColumn<Receta, String> cTipo;
    @FXML
    private Label info;
    @FXML
    private MenuItem menuSalir;
    @FXML
    private MenuItem menuAcercaDe;

    @FXML
    protected void insertarReceta(ActionEvent actionEvent) {

        if(!txtNombre.getText().isEmpty()){
            Receta receta = new Receta();
            receta.setNombre( txtNombre.getText());
            receta.setTipo((String) listTipo.getSelectionModel().getSelectedItem());
            receta.setDuracion((int) sliderDuracion.getValue());
            receta.setDificultad((String) comboDificultad.getSelectionModel().getSelectedItem());
            tabla.getItems().add(receta);
            info.setText(receta.toString());
        }

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
        //Si el combo no es estático. /// ObservableList no se instancia -> NO se pone new delante
        ObservableList<String> datos = FXCollections.observableArrayList();//Es como un arrayList especial para JavaFX
        datos.addAll("Fácil", "Moderada", "Difícil");
        comboDificultad.setItems(datos);
        comboDificultad.getSelectionModel().selectFirst();//Selecciona el primer elemento por defecto

        sliderDuracion.setValue(60);//Los slider llevan double
        lblDuracion.setText(Math.round(sliderDuracion.getValue()) + " min");//Le pasamos el valor redondeado

        listTipo.getItems().addAll("Desayuno", "Segundo desayuno", "Almuerzo", "SobreAlmuerzo", "Merienda", "Cena", "Recena", "Poscena");
        listTipo.getSelectionModel().select(0);//Selecciona el primer elemento por defecto

        sliderDuracion.valueProperty().addListener((observableValue, number, t1) -> {// number -> antiguo valor y t1->nuevoValor
            lblDuracion.setText(t1.intValue() + " min");//Para que se sincronice cuandp arrastramos el ratón
        });

       /* txtNombre.textProperty().addListener((ob, vold, vnew) ->{
            info.setText("antiguo: "+vold+" nuevo: "+vnew);} );*/

        tabla.getSelectionModel().selectedItemProperty().addListener(//Cuando cambie la seleción en la tabla (al hacer click) se hace esto
                (observable, vOld, vNew) -> {
                    info.setText(vNew.toString());
                    txtNombre.setText(vNew.getNombre());
                    sliderDuracion.setValue(vNew.getDuracion());
                    listTipo.getSelectionModel().select(vNew.getTipo());
                    comboDificultad.getSelectionModel().select(vNew.getDificultad());
                }
        );


        cNombre.setCellValueFactory((fila) -> {         //Inicializador de la tabla, se recomienda ponder todo String y luego convertir
            String nombre = fila.getValue().getNombre().toUpperCase();
            return new SimpleStringProperty(nombre);
        });//Le indicamos de dónde sacamos el valor de esa columna

        cDificultad.setCellValueFactory((fila) -> new SimpleStringProperty( fila.getValue().getDificultad()));//Le indicamos de dónde sacamos el valor de esa columna

        cDuracion.setCellValueFactory((fila) -> {         //Inicializador de la tabla, se recomienda ponder todo String y luego convertir
            return new SimpleStringProperty(fila.getValue().getDuracion()+ "min");
        });//Le indicamos de dónde sacamos el valor de esa columna

        cTipo.setCellValueFactory( (fila) ->new SimpleStringProperty(fila.getValue().getTipo() ));

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

        //Indico al comboBox como mostrar una receta creando una clase setConverter
        comboRecetas.setConverter(new StringConverter<Receta>() {
            @Override
            public String toString(Receta receta) {
                if (receta != null) return receta.getNombre();//Hago que devuelva el nombre de las recetas
                else return null;
            }

            @Override
            public Receta fromString(String s) {
                return null;
            }
        });

        comboRecetas.getItems().addAll(tabla.getItems() );//Le añado toda la tabla y cuando modifico solo cambio 1

    }

    @FXML
    public void actualizarDuracion(Event event) {//Le hemos pasado tambien el Dragged en el visual
       //lblDuracion.setText(Math.round(sliderDuracion.getValue()) + " min");//Para que se sincronice cuandp arrastramos el ratón
    }
//    @FXML
//    public void click(ActionEvent actionEvent) {System.out.println(tabla.getSelectionModel().getSelectedItem());    }

    @FXML
    public void salir(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    public void mostrarAcercaDe(ActionEvent actionEvent) {
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("El Creador");
        alert.setContentText("Francisco Romero");
        alert.showAndWait();
    }

    @FXML
    public void mostrarReceta(ActionEvent actionEvent) {
        System.out.println(comboRecetas.getSelectionModel().getSelectedItem());
        tabla.getSelectionModel().select(comboRecetas.getSelectionModel().getSelectedItem());
        //System.out.println(dificultad.getSelectedToggle().getUserData().toString());

        Session.setRecetaActual(comboRecetas.getSelectionModel().getSelectedItem());
        HelloApplication.loadFXML("VentanaSecundaria.fxml");
    }
}





//Importante actualizar con btn derecho del raton
//Propiedad no es lo mismo que atributo, un atributo -> algo es negro mientras que la propiedad -> Color, un objeto que tiene algo y puede cambiar
//El atributo es algo que viene predeterminado
//La propiedad es algo más cambiante