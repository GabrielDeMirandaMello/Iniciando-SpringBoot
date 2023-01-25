package aplication;

import aplication.domain.entity.Cliente;
import aplication.domain.entity.Pedido;
import aplication.domain.repository.Clientes;
import aplication.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
@RestController
public class VendasApplication {
    @Bean
    public CommandLineRunner commandLineRunner(
            @Autowired Clientes clientes,
            @Autowired Pedidos pedidos) {
        return args -> {

        };
    }
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
