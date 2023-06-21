package com.mantenimiento.spring.repositorio;
 
import org.springframework.data.jpa.repository.JpaRepository;
import com.mantenimiento.spring.models.Sede;


public interface SedeRepositorio extends JpaRepository<Sede, Integer>{
	// @Query(value = "SELECT a FROM Sede a WHERE a.nombre=?1")
    // public List<Sede> buscarSedePorNombre(String nombre);

    // @Query(value = "SELECT a FROM Sede a WHERE a.nombre like CONCAT(?1,'%')")
    // public List<Sede>  buscarSedeLikeNombre(String nombre);

}
