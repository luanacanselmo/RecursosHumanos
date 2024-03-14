package gm.rh.controlador;

import gm.rh.exception.RecursoNoEncontradoException;
import gm.rh.modelo.Empleado;
import gm.rh.servicio.EmpleadoServicio;
import gm.rh.servicio.IEmpleadoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//http://localhost:8080/rh-app
@RequestMapping("rh-app")
@CrossOrigin(value =  "http://localhost:3000")
//para peticiones desde el front
public class EmpleadoControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(EmpleadoControlador.class);
    //enviar info a consola
    @Autowired
    private IEmpleadoServicio empleadoServicio;

//http://localhost:8080/rh-app/empleados
    @GetMapping("/empleados")

    public List<Empleado> obtenerEmpleados(){
        var empleados = empleadoServicio.listarEmpleados();
        empleados.forEach(empleado -> logger.info(empleado.toString()));
        return empleados;
    }
    @PostMapping("/empleados")
    //Cuando se realiza una solicitud POST a esa ruta, este método será invocado para manejarla.
    //@RequestBody indica a Spring que debe deserializar el cuerpo de la solicitud
    //HTTP en un objeto Empleado y pasarlo como argumento al método.
    public Empleado agregarEmpleado(@RequestBody Empleado empleado){
        logger.info("Empleado a agregar: " + empleado);
        return empleadoServicio.guardarEmpleado(empleado);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado>
    obtenerEmpleadoPorId(@PathVariable Integer id){
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if(empleado == null)
            throw new RecursoNoEncontradoException("No se encontro el id: "+ id);
        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado>
    actualizarEmpleado(@PathVariable Integer id, @RequestBody Empleado empleadoRecibido){

        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if(empleado == null)
            throw new RecursoNoEncontradoException("El id no existe "+ id);
        empleado.setNombre(empleadoRecibido.getNombre());
        empleado.setDepartamento(empleadoRecibido.getDepartamento());
        empleado.setSueldo(empleadoRecibido.getSueldo());
         empleadoServicio.guardarEmpleado(empleado);
        return ResponseEntity.ok(empleado);

    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String, Boolean>>
    eliminarEMPLEAFO (@PathVariable Integer id){
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if(empleado == null)
            throw new RecursoNoEncontradoException("El id recibido no existe");
        empleadoServicio.eliminarEmpleado(empleado);
        //Json
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
