package com.PPOOII.Proyecto.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.PPOOII.Proyecto.Entities.Usuario;
import com.PPOOII.Proyecto.Services.UsuarioService;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PutMapping("/{id}/cambiar-password")
    public ResponseEntity<Void> cambiarPassword(@PathVariable Long id, @RequestBody String nuevoPassword) {
        usuarioService.cambiarPassword(id, nuevoPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/detalles/{idpersona}")
    public ResponseEntity<Usuario> obtenerUsuarioDetalles(@PathVariable Long idpersona) {
        Optional<Usuario> usuario = usuarioService.obtenerUsuarioDetalles(idpersona);
        return usuario.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

