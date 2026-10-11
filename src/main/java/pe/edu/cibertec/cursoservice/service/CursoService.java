package pe.edu.cibertec.cursoservice.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import pe.edu.cibertec.cursoservice.dto.CursoDisponibilidadDTO;


@Service
public class CursoService {

    private final Map<Long, CursoDisponibilidadDTO> cursos = new LinkedHashMap<>();


    private final Map<Long, List<Long>> cursosPorEstudiante = new HashMap<>();

    public CursoService() {
        agregar(new CursoDisponibilidadDTO(1L, "INF101", "Programación I", 15, "Ninguno", "ABIERTO"));
        agregar(new CursoDisponibilidadDTO(2L, "INF201", "Estructuras de Datos", 8, "INF101", "ABIERTO"));
        agregar(new CursoDisponibilidadDTO(3L, "INF301", "Base de Datos", 0, "INF201", "CERRADO"));
        agregar(new CursoDisponibilidadDTO(4L, "MAT101", "Matemática I", 20, "Ninguno", "ABIERTO"));
        agregar(new CursoDisponibilidadDTO(5L, "INF401", "Arquitectura de Microservicios", 5, "INF301", "ABIERTO"));
        agregar(new CursoDisponibilidadDTO(6L, "ING101", "Inglés Técnico", 0, "Ninguno", "CERRADO"));

        cursosPorEstudiante.put(1L, List.of(1L, 2L, 4L));
        cursosPorEstudiante.put(2L, List.of(1L, 3L, 4L, 5L));
    }

    private void agregar(CursoDisponibilidadDTO curso) {
        cursos.put(curso.idCurso(), curso);
    }

    public CursoDisponibilidadDTO buscarPorId(Long idCurso) {
        CursoDisponibilidadDTO curso = cursos.get(idCurso);
        if (curso == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No existe el curso con id " + idCurso);
        }
        return curso;
    }

    public List<CursoDisponibilidadDTO> listarPorEstudiante(Long estudianteId) {
        List<Long> ids = cursosPorEstudiante.get(estudianteId);
        if (ids == null) {

            return cursos.values().stream()
                    .filter(c -> "ABIERTO".equals(c.estadoCurso()))
                    .toList();
        }
        return ids.stream().map(cursos::get).toList();
    }
}
