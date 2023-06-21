package com.mantenimiento.spring.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import com.mantenimiento.spring.models.Vehiculo;



public interface VehiculoRepositorio extends JpaRepository<Vehiculo, Integer>{
	// @Query(value = "SELECT a FROM Vehiculo a WHERE a.nroMatricula=?1")
    // public List<Vehiculo> buscarVehiculosPornroMatricula(String nroMatricula);

    // @Query(value = "SELECT a FROM Vehiculo a WHERE a.nroMatricula like CONCAT(?1,'%')")
    // public List<Vehiculo>  buscarVehiculoLikenroMatricula(String nroMatriculae);

}
