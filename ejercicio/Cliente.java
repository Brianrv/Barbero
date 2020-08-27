package ejercicio;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Cliente implements Runnable {

    private static final AtomicInteger idGenerator = new AtomicInteger();

    private final int id;

    private final SalaDeEspera salaDeEspera;

    private final SynchronousQueue<Boolean> synchronousQueue;

    private volatile boolean afeitado;

    public Cliente(SalaDeEspera salaDeEspera) {
        this.id = idGenerator.incrementAndGet();
        this.salaDeEspera = salaDeEspera;
        this.synchronousQueue = new SynchronousQueue<>();
    }

    @Override
    public void run() {
        try {
            salaDeEspera.tomaTurno(this);

            System.out.println("cliente " + this + " espera ser llamado");
            esperaTurnoBarbero();

            afeitado = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void llamaYAfeita() throws InterruptedException {
        synchronousQueue.put(true);
    }

    public void esperaTurnoBarbero() throws InterruptedException {
        synchronousQueue.take();
    }

    public boolean esAfeitado() {
        return afeitado;
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
