package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DatosListaMedico;
import med.voll.api.medico.DatosRegistroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Responda
@RestController
//Path a la cual responde
@RequestMapping("/medicos")
public class MedicoController {
//crea una instancia de la interfaz de MedicoRepository
    @Autowired
    private MedicoRepository repository;
//indicamo que haremos modificaciones en la bd
    @Transactional
//Metodo POST
    @PostMapping
    //@RequestBody indicamos que el String es el body de la request,@Valid indica que se deben validar los campos
    public void registrar(@RequestBody @Valid DatosRegistroMedico datos) {
        //guardo datos en BD antes convierto los datos a entidad Medico
//        repository.save(new Medico(null, datos.nombre(), datos.email()))
        repository.save(new Medico(datos));
    }
    //leer datos de la BD
    @GetMapping
    //Modificamos todos los datos pero solo mostramos los necesario con DTO
    public List<DatosListaMedico> listar(){
        return repository.findAll().stream()
                .map(DatosListaMedico::new)
                .toList();

    }
}