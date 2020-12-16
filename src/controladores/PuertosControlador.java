package controladores;

import app.Puerto;
import com.fazecast.jSerialComm.*;


import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.util.Callback;

public class PuertosControlador {
	
	@FXML 
	JFXTreeTableView<Puerto> tablaPuertos;
	
	private ObservableList<Puerto> puertosObs;
	
	@FXML
	public void initialize()
	{
		System.out.println("Hola");
		JFXTreeTableColumn<Puerto,String> c1 = new JFXTreeTableColumn<>("Nombre del puerto");
		c1.setCellValueFactory( new Callback<JFXTreeTableColumn.CellDataFeatures<Puerto, String>,ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Puerto, String> param) {
				
				return param.getValue().getValue().getNombreSistema();
			}
		});
		JFXTreeTableColumn<Puerto,String> c2 = new JFXTreeTableColumn<>("Descripción");
		c2.setCellValueFactory( param -> {
			return param.getValue().getValue().getDescripcion();
		});
		JFXTreeTableColumn<Puerto,Integer> c3 = new JFXTreeTableColumn<>("Tamaño del buffer");
		c3.setCellValueFactory( param -> {
			return param.getValue().getValue().getTamanioBuffer().asObject();
		});
		JFXTreeTableColumn<Puerto,Integer> c4 = new JFXTreeTableColumn<>("Baudios");
		c4.setCellValueFactory(new TreeItemPropertyValueFactory<Puerto,Integer>("velocidad"));
		JFXTreeTableColumn<Puerto,Integer> c5 = new JFXTreeTableColumn<>("Bits para dato");
		c5.setCellValueFactory(new TreeItemPropertyValueFactory<Puerto,Integer>("tamanioPalabra"));
		JFXTreeTableColumn<Puerto,Boolean> c6 = new JFXTreeTableColumn<>("Estado");
		c6.setCellValueFactory(new TreeItemPropertyValueFactory<Puerto,Boolean>("abierto"));
		
		tablaPuertos.getColumns().addAll(c1,c2,c3,c4,c5,c6);
		puertosObs = FXCollections.observableArrayList();
		for(SerialPort sp:SerialPort.getCommPorts())
		{
			puertosObs.add(new Puerto(sp));
		}
		TreeItem<Puerto> origen = new RecursiveTreeItem<>(puertosObs,RecursiveTreeObject::getChildren);
		tablaPuertos.setRoot(origen);
		tablaPuertos.setShowRoot(false);
	}
}
