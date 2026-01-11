package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    //Modificamos todos los datos pero solo mostramos los necesario con DTO, dato de tipo Page, porque divide losdatos
//    y los muestra en una lista
    public Page<DatosListaMedico> listar(@PageableDefault(size=10,page=0, sort={"nombre"}) Pageable paginacion){
        return repository.findAllByActivoTrue(paginacion)
                .map(DatosListaMedico::new);

    }
//Actualizar un registro
//ejecuta de forma automatica la actualizacion
    @Transactional
    @PutMapping
    public void actualizar(@RequestBody @Valid DatosActualizacionMedico datos) {
        //obtenemos el medico de la BD
        var medico = repository.getReferenceById(datos.id());
        //ocupamos metodo para actualizar
        medico.actualizarInformaciones(datos);
    }

    @Transactional
    @DeleteMapping("/{id}")
    //@PathVariable obtenemos el id desde el path
    public void eliminar(@PathVariable Long id) {
        //buscamos por id y elimina por id
//        repository.deleteById(id);
        //Exculsion logica
        var medico = repository.getReferenceById(id);
        medico.eliminar();
    }
}