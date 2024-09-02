package com.PPOOII.Proyecto.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import com.PPOOII.Proyecto.Entities.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
	Optional<Persona> findByIdentificacion(int identificacion);
	List<Persona> findByEdad(int edad);
	List<Persona> findByPapellido(String papellido);
	List<Persona> findByPnombre(String pnombre);
	
}
