package controladores;

import java.io.IOException;
import java.net.URL;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AppControlador {

	@FXML 
	BorderPane bp;
	@FXML
	JFXButton btnAcerca;
	@FXML
	JFXButton btnPuertos;
	@FXML
	JFXButton btnControl;
	@FXML
	StackPane sp;

	
	@FXML
	public void mostrarControl(ActionEvent ae)
	{
		System.out.println("Presionado");
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ControlOverview.fxml"));
            BorderPane parent = loader.load();
            ((Stage)btnControl.getScene().getWindow()).setScene(new Scene(parent));
        } catch (IOException eox) {
            eox.printStackTrace();
        }
		/*
		scene = bp.getScene();
		stage = (Stage) scene.getWindow();
		FXMLLoader loader = new FXMLLoader();
		URL xmlUrl = getClass().getResource("/view/AppOverview.fxml");
		loader.setLocation(xmlUrl);
		loader.setController(new ControlControlador(scene));
        BorderPane control;
		try {
			control = loader.load();
			stage.setScene(new Scene(control));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	}
	
	@FXML
	public void mostrarPuertos(ActionEvent ae)
	{
		System.out.println("Presionado");
		FXMLLoader cargador = new FXMLLoader();
		URL xmlUrl = getClass().getResource("/view/PuertosOverview.fxml");
		cargador.setLocation(xmlUrl);
		cargador.setController(new PuertosControlador()); // Writing controller directly
		BorderPane root;
		try {
			root = cargador.load();
			Scene scene = new Scene(root);
			Stage ns = new Stage();
			ns.setTitle("Monitoreo de puertos");
			ns.setScene(scene);
			ns.show();	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void mostrarAcerca(ActionEvent ae)
	{
		JFXDialogLayout contenido = new JFXDialogLayout();
		Label titulo = new Label("Control de diodos");
		titulo.setId("titulo1");
		contenido.setHeading(titulo);
		Text cuerpo = new Text();
		cuerpo.setText("Este proyecto fue creado por:\n"
				+ "Mercado Rogel Martin\n"
				+ "Compañeros\n"
				+ "ESCOM IPN 2020");
		cuerpo.setId("texto");
		contenido.setBody(cuerpo);
		JFXButton regresar = new JFXButton("Ocultar");
		/* VERSION 1
		regresar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
			}
			
		});*/
		System.out.println("Presionado");
		JFXDialog dialogo = new JFXDialog(sp,contenido,
				JFXDialog.DialogTransition.CENTER);
		regresar.setOnAction(eh -> {
			dialogo.close();
		});
		regresar.setId("btnRegresar");
		contenido.setActions(regresar);
		dialogo.show();
		
	}
}
