package com.academico.application.service;

import com.academico.application.port.in.CursoUseCase;
import com.academico.application.port.out.CursoRepository;
import com.academico.domain.model.Curso;

import java.util.List;

public class CursoService implements CursoUseCase {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void registrar(Curso curso) {

        List<Curso> cursos = repository.listar();

        cursos.add(curso);

        repository.guardar(cursos);
    }

    @Override
    public List<Curso> listar() {
        return repository.listar();
    }

    @Override
    public boolean actualizar(Curso curso) {

        List<Curso> cursos = repository.listar();

        for (Curso c : cursos) {

            if (c.getId() == curso.getId()) {

                c.setNombre(curso.getNombre());
                c.setCreditos(curso.getCreditos());

                repository.guardar(cursos);

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean eliminar(int id) {

        List<Curso> cursos = repository.listar();

        boolean eliminado = cursos.removeIf(c -> c.getId() == id);

        if (eliminado) {
            repository.guardar(cursos);
        }

        return eliminado;
    }
}