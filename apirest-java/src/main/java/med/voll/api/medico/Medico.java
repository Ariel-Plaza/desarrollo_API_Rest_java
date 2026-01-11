package med.voll.api.medico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.direccion.Direccion;

//Entidad JPA envia a la BD
//Nombre de la tabla
@Table(name = "medicos")
//Reconocida como entidad Medico
@Entity(name = "Medico")
//genera en tiempo de compilacion los getters a tarvez de lombok
@Getter
//Crea constructor sin argumentos
@NoArgsConstructor
//genera contructos con todos los argumentos de medico
@AllArgsConstructor
//sistema identifica si dos ID son iguales
@EqualsAndHashCode(of = "id")
public class Medico {
    //Id
    @Id
    //autogenrado
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean activo;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    //Enum de tipo texto
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
//se agrega a la tabla de medico
    @Embedded
    private Direccion direccion;
//Constructos en base a datos en formato JSON, para guardar en BD
    public Medico(DatosRegistroMedico datos) {
        this.id = null;
        this.activo = true;
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.telefono = datos.telefono();
        this.documento = datos.documento();
        this.especialidad = datos.especialidad();
        this.direccion = new Direccion(datos.direccion());
    }

    public void actualizarInformaciones(@Valid DatosActualizacionMedico datos) {
        //verifica si llegan los datos
        if (datos.nombre() != null) {
            this.nombre = datos.nombre();
        }
        if (datos.telefono() != null) {
            this.telefono = datos.telefono();
        }
        if (datos.direccion() != null) {
            this.direccion.actualizarDireccion(datos.direccion());
        }
    }

    public void eliminar() {
        this.activo = false;
    }
}
