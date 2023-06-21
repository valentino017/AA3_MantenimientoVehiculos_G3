package com.mantenimiento.spring.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import com.mantenimiento.spring.models.Reserva;

public interface ReservaRepositorio extends  JpaRepository<Reserva, Integer> {
    // @Query(value = "SELECT a FROM Reserva a WHERE a.id_reserva=?1")
    // public List<Reserva> buscarClientesPorNombre(Integer id_reserva);

    // @Query(value = "SELECT a FROM Cliente a WHERE a.nombre like CONCAT(?1,'%')")
    // public List<Cliente> buscarClienteLikeNombre(String nombre);
}