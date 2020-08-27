package ejercicio;

import java.util.concurrent.ArrayBlockingQueue;

public class SalaDeEspera {

    private final ArrayBlockingQueue<Cliente> waitingClientes;

    public SalaDeEspera(int capacity) {
        waitingClientes = new ArrayBlockingQueue<>(capacity);
    }

    public void tomaTurno(Cliente cliente) throws InterruptedException {
        waitingClientes.put(cliente);
    }

    public Cliente nextCliente() throws InterruptedException {
        return waitingClientes.take();
    }

}
