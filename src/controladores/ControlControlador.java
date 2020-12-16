package controladores;

import java.io.IOException;

import com.fazecast.jSerialComm.SerialPort;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXSlider;

import app.Tarea;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class ControlControlador {

	@FXML
	BorderPane bp;
	
	@FXML
	Button btnRegresar;
	@FXML
	JFXCheckBox cbEncendido;
	@FXML
	JFXSlider sliderLum;
	
	@FXML
	JFXProgressBar progressBar;
	
	@FXML
	Label labelValor;
	
	@FXML
	Label labelCaracter;
	
	private Scene anterior;
	private Tarea tarea;
	/* This method is automatically called after 
	 * the fxml file is loaded
	*/ 
	@FXML
	private void initialize()
	{
		tarea = new Tarea("COM1");
		sliderLum.setMax(200);
		sliderLum.setMin(10);
		sliderLum.setShowTickLabels(true);
		sliderLum.setShowTickMarks(true);

		sliderLum.valueProperty().addListener(new ChangeListener<Number>() {
		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			   tarea.setValor(newValue.byteValue());
			   labelValor.setText(""+newValue.byteValue());
			   char c = (char) newValue.byteValue();
			   labelCaracter.setText(Character.toString(c));
		}
	    });
	}
	
	@FXML
	public void regresar(ActionEvent ae)
	{
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AppOverview.fxml"));
            loader.setController(new AppControlador());
            BorderPane parent = loader.load();
            ((Stage)btnRegresar.getScene().getWindow()).setScene(new Scene(parent));
        } catch (IOException eox) {
            eox.printStackTrace();
        }
	}
	@FXML
	public void encender(ActionEvent ae)
	{
		progressBar.progressProperty().bind(tarea.progressProperty());
		new Thread(tarea).start();
		//labelValor.setText(""+tarea.getValor());
		System.out.println("Presionado");
	}
}
