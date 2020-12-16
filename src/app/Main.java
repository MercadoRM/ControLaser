package app;

import java.net.URL;

import controladores.AppControlador;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader cargador = new FXMLLoader();
		System.out.println(getClass().toString());
		URL xmlUrl = getClass().getResource("/view/AppOverview.fxml");
		cargador.setLocation(xmlUrl);
		cargador.setController(new AppControlador()); // Writing controller directly
		BorderPane root = cargador.load();
		Scene scene = new Scene(root,600,400);
		//scene.getStylesheets().add(getClass().getResource("/styles/app.css").toExternalForm());
		primaryStage.setTitle("Control de diodos");
		primaryStage.setScene(scene);
		primaryStage.show();	

	}
	
	public static void main(String [] args)
	{
		launch(args);
	}
}
