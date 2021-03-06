package util;

import java.util.Timer;

public class temporizador {
	private static Timer timer;
	private final int PERIODO = 1000;//la unidad de tiempo del periodo es el segundo  
	private static TareaEnviarMail tareaEnviarMail;
	private static TareaLotesVencidos tareaLV;
	private static TareaRevisionSaldoNac tareaSaldoNac;

	public temporizador(){
		timer = new Timer();
		tareaLV = new TareaLotesVencidos();	
		tareaSaldoNac = new TareaRevisionSaldoNac();
		tareaEnviarMail = new TareaEnviarMail();
	}
	
	public void programarTemporizador(){
		
		System.out.println("  >>>>>>>Temporizador Programado<<<<<<<");
		timer.schedule(tareaLV, 0, 60*1440*PERIODO ); //cada 24 horas
		timer.schedule(tareaSaldoNac, 0, 60*1440*PERIODO ); //cada 24 horas
		timer.schedule(tareaEnviarMail, 0, 60*10*PERIODO ); //cada 10 minutos

	}
	
	public void finalizarTemporizador(){
		timer.cancel();
		System.out.println(">>>>>>>Temporizador Finalizado<<<<<<<");
	}
}