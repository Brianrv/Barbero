package ejercicio;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        SalaDeEspera salaDeEspera = new SalaDeEspera(10);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(new Barbero(salaDeEspera));
        executorService.submit(new Barbero(salaDeEspera));
        executorService.submit(new Barbero(salaDeEspera));

        List<Cliente> clientes = Stream.generate(() -> new Cliente(salaDeEspera))
                                         .limit(10)
                                         .peek(executorService::submit)
                                         .collect(toList());

        while (!clientes.stream().allMatch(Cliente::esAfeitado)) {
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println("Todos los clientes han sido afeitados");
        executorService.shutdownNow();
        executorService.awaitTermination(1, MINUTES);
    }

}
