package ejercicio;

public class Barbero implements Runnable {

    private final SalaDeEspera salaDeEspera;

    public Barbero(SalaDeEspera salaDeEspera) {
        this.salaDeEspera = salaDeEspera;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Cliente cliente = salaDeEspera.nextCliente();
                System.out.println("Bienvenido a la barberia "+ cliente);
                System.out.println("barbero llama y afeita al cliente" + cliente);
                cliente.llamaYAfeita();
            }

        } catch (InterruptedException e) {
            System.out.println("El barbero ha finalizado");
        }
    }
}
