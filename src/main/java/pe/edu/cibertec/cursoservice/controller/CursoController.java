package pe.edu.cibertec.cursoservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pe.edu.cibertec.cursoservice.dto.CursoDisponibilidadDTO;
import pe.edu.cibertec.cursoservice.service.CursoService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {

    private final CursoService cursoService;

    // GET /api/v1/cursos/{idCurso}
    @GetMapping("/{idCurso}")
    public ResponseEntity<CursoDisponibilidadDTO> obtenerCurso(@PathVariable Long idCurso) {
        return ResponseEntity.ok(cursoService.buscarPorId(idCurso));
    }

    // GET /api/v1/cursos/estudiante/{estudianteId}
    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<CursoDisponibilidadDTO>> cursosDelEstudiante(@PathVariable Long estudianteId) {
        return ResponseEntity.ok(cursoService.listarPorEstudiante(estudianteId));
    }
}
