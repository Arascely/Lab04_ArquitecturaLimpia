package com.academico.application.port.out;

import com.academico.domain.model.Curso;

import java.util.List;

public interface CursoRepository {

    List<Curso> listar();

    void guardar(List<Curso> cursos);
}
