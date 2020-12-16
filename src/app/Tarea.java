package app;
import javafx.concurrent.Task;
import com.fazecast.jSerialComm.*;

public class Tarea extends Task<Void>{
	
	private SerialPort sp;
	private Byte valor;
	public Tarea(String puerto)
	{
		try {
		   sp = SerialPort.getCommPort(puerto);
		   sp.openPort();
		}catch(SerialPortInvalidPortException pi)
		{
			pi.printStackTrace();
		}
		
	}
	
	@Override
	protected Void call() throws Exception {
		if(sp.isOpen())
		{
			System.out.println("Puerto abierto!");
			System.out.println("Valor a transmitir");
			byte [] datos = { valor , 0 };
			for (byte i = 0; i < 100; i++)
			{
				if (isCancelled()) {
		               break;
		        }
				datos[0] = valor;
				System.out.println("Valor a transmitir:"+datos[0]);
				datos[1] = i;
				System.out.println("Valor iteración:"+datos[1]);
				sp.writeBytes(datos,datos.length);
		        this.updateProgress(i, 100);
		        Thread.sleep(1000);
			}
		}else{
			System.out.println("Puerto cerrado");
		}
		return null;
	}
	
	public byte getValor()
	{
		return valor;
	}
	public void setValor(byte valor) {
		this.valor = valor;
		
	}
}