package gm.rh.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
//crea automaticamente una tabla en la bd
@Data
//metodos get y set automaticos
@NoArgsConstructor
//constructor vacio
@AllArgsConstructor
//constructor con todos los atributos
@ToString
public class Empleado {
    @Id
    //llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //auto incrementable
    Integer idEmpleado;
    //tipo y nombre
    String nombre;
    String departamento;
    Double sueldo;



}
