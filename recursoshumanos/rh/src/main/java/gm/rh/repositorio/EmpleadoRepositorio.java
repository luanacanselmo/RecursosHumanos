package gm.rh.repositorio;

import gm.rh.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
//el uso de repositorios generalmente está asociado con el framework de persistencia de datos llamado Spring Data.
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Integer> {
}
