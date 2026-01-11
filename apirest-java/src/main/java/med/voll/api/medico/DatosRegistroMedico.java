package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.direccion.DatosDireccion;
//ya tiene constructores.getter y setters
//Son los datos recibidos desde JSON

public record DatosRegistroMedico(
        //Validacion no nulo
        @NotBlank String nombre,
        //validacion no nulo y tipo de dato email
        @NotBlank @Email String email,
        @NotBlank String telefono,
        //validacion no nulo, Patron del dato
        @NotBlank @Pattern(regexp = "\\d{7,9}") String documento,
        //Se crea enum que acepta solo valores ya generados
        @NotNull Especialidad especialidad,
        //Propia clase con record
        //Validacion No null y se debe validar Datos direccion
        @NotNull @Valid DatosDireccion direccion) {
}
