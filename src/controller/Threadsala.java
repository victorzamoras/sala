package controller;

import java.util.concurrent.Semaphore;

public class Threadsala extends Thread {
	private int id = 1;
	private Semaphore semaforo;
	private int corredor = 200;
	private int tempo;

	public Threadsala(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		corredores();
		try {
			semaforo.acquire();
			porta();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	public void corredores() {
		int andar;
		try {
			do {
				andar = (int) (Math.random() * 3) + 4;
				corredor -= andar;
				sleep(1000);
				System.out.println(
						"pessoa " + id + " andou " + andar + "m e faltam " + corredor + "m para ela chegar a porta");
				if (andar > corredor) {
					corredor = 0;
					System.out.println("pessoa " + id + " chegou até a porta");
				}
			} while (andar <= corredor);
			sleep(1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	public void porta() {
		tempo = (int) (Math.random() * 1001 + 1000);
		try {
			if (corredor == 0) {
				System.out.println("pessoa " + id + " abriu a porta");
				sleep(tempo);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
