package pe.edu.cibertec.cursoservice.dto;

public record CursoDisponibilidadDTO(
        Long idCurso,
        String codigoCurso,
        String nombreCurso,
        Integer vacantesDisponibles,
        String prerrequisito,
        String estadoCurso
) {
}
