package med.voll.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//define que es una aplicacion web
@RestController
//escuchando URL
@RequestMapping("/hello")
public class HelloController {
    //Responde al GET
    @GetMapping
    public String Hello() {

        return "hello World!";
    }
}
