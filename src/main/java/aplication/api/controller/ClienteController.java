package aplication.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/clientes")
public class ClienteController {

    @RequestMapping(value = "/hello/{nome}")
    public String helloCliente ( @PathVariable("nome") String nomeCliente) {
        return String.format("Hello %s", nomeCliente);
    }
}
