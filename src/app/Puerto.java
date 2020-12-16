package app;

import com.fazecast.jSerialComm.SerialPort;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Puerto extends RecursiveTreeObject<Puerto>{ 
	private StringProperty nombreSistema;
	private StringProperty descripcion;
	private IntegerProperty tamanioBuffer;
	private IntegerProperty velocidad;
	private IntegerProperty tamanioPalabra;
	private BooleanProperty abierto;
	
	private SerialPort serial;

	
	public StringProperty getNombreSistema() {
		// TODO Auto-generated method stub
		return nombreSistema;
	}
	
	public Puerto(SerialPort sp)
	{
		this.serial = sp;
		this.nombreSistema = new SimpleStringProperty(serial.getSystemPortName());
		this.descripcion = new SimpleStringProperty(serial.getPortDescription());
		this.tamanioBuffer = new SimpleIntegerProperty(serial.getDeviceReadBufferSize());
		this.setVelocidadProperty(new SimpleIntegerProperty(serial.getBaudRate()));
		this.setTamanioPalabraProperty(new SimpleIntegerProperty(serial.getNumDataBits()));
		this.setAbiertoProperty(new SimpleBooleanProperty(serial.isOpen()));
	}
	
	public Puerto(String nombreSistema,String descripcion,Integer tamanioBuffer,SerialPort serial)
	{
		this.nombreSistema = new SimpleStringProperty(nombreSistema);
		this.descripcion = new SimpleStringProperty(descripcion);
		this.tamanioBuffer = new SimpleIntegerProperty(tamanioBuffer);
		this.serial = serial;
	}
	
	public Puerto(StringProperty nombreSistema, StringProperty descripcion, IntegerProperty tamanioBuffer,SerialPort referencia) {
		super();
		this.nombreSistema = nombreSistema;
		this.descripcion = descripcion;
		this.tamanioBuffer = tamanioBuffer;
		this.serial = referencia;
	}
	public ObservableValue<String> getDescripcion() {
		// TODO Auto-generated method stub
		return descripcion;
	}
	public IntegerProperty getTamanioBuffer() {
		// TODO Auto-generated method stub
		return tamanioBuffer;
	}

	public IntegerProperty velocidadProperty() {
		return velocidad;
	}

	public void setVelocidadProperty(IntegerProperty velocidad) {
		this.velocidad = velocidad;
	}

	public IntegerProperty tamanioPalabraProperty() {
		return tamanioPalabra;
	}

	public void setTamanioPalabraProperty(IntegerProperty tamanioPalabra) {
		this.tamanioPalabra = tamanioPalabra;
	}

	public BooleanProperty abiertoProperty() {
		abierto.set(serial.isOpen());
		return abierto;
	}

	public void setAbiertoProperty(BooleanProperty abierto) {
		this.abierto = abierto;
	}

	
}
