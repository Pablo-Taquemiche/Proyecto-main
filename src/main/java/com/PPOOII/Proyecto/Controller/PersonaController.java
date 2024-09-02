package com.PPOOII.Proyecto.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.PPOOII.Proyecto.Entities.Persona;	
import com.PPOOII.Proyecto.Services.PersonaService;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @PostMapping
    public ResponseEntity<Persona> crearPersona(@RequestBody Persona persona) {
        Persona nuevaPersona = personaService.crearPersona(persona);
        return new ResponseEntity<>(nuevaPersona, HttpStatus.CREATED);
    }

    // Metodos de busqueda
    
    @GetMapping
    public List<Persona> obtenerPersonas() {
        return personaService.obtenerPersonas();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPersonaPorId(@PathVariable Long id) {
        return personaService.obtenerPersonaPorId(id)
                .map(persona -> new ResponseEntity<>(persona, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/buscar/identificacion/{identificacion}")
    public ResponseEntity<Persona> buscarPorIdentificacion(@PathVariable int identificacion) {
        return personaService.buscarPorIdentificacion(identificacion)
                .map(persona -> new ResponseEntity<>(persona, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/buscar/edad/{edad}")
    public ResponseEntity<List<Persona>> buscarPorEdad(@PathVariable int edad) {
        List<Persona> personas = personaService.buscarPorEdad(edad);
        if (personas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    @GetMapping("/buscar/apellido/{papellido}")
    public ResponseEntity<List<Persona>> buscarPorPrimerApellido(@PathVariable String papellido) {
        List<Persona> personas = personaService.buscarPorPrimerApellido(papellido);
        if (personas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    @GetMapping("/buscar/nombre/{pnombre}")
    public ResponseEntity<List<Persona>> buscarPorPrimerNombre(@PathVariable String pnombre) {
        List<Persona> personas = personaService.buscarPorPrimerNombre(pnombre);
        if (personas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }
    // Actualizar un usuario

    @PutMapping("/{id}")
    public ResponseEntity<Persona> actualizarPersona(@PathVariable Long id, @RequestBody Persona personaActualizada) {
        try {
            Persona persona = personaService.actualizarPersona(id, personaActualizada);
            return new ResponseEntity<>(persona, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable Long id) {
        personaService.eliminarPersona(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
