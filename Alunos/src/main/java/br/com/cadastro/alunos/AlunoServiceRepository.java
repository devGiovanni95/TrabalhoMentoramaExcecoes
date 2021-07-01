package br.com.cadastro.alunos;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Service
public class AlunoServiceRepository {

    private final List<Alunos> alunos;

    public AlunoServiceRepository(List<Alunos> alunos) {
        this.alunos = new ArrayList<>();
    }


    //Busca aluno por nome ou idade
    public List<Alunos> findAll(String nome, Integer idade) {
        if (nome != null) {
             return alunos.stream().filter(msg -> msg.getNome().contains(nome)).collect(Collectors.toList());

        }
        if (idade != null) {
            return alunos.stream().filter(msg -> msg.getIdade().equals(idade)).collect(Collectors.toList());
        }
        return this.alunos;
    }


    //Busca por id
    public Alunos findById(@PathVariable("id") Integer id) {
        return this.alunos.stream()
                .filter(alunoId -> alunoId.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    //Alterar nome do aluno
    public void update(@RequestBody final Alunos aluno) {
        alunos.stream().filter(nomeAluno -> nomeAluno.getId().equals(aluno.getId()))
                .forEach(nomeAluno -> nomeAluno.setNome(aluno.getNome()));

        alunos.stream()
                .filter(nomeAluno -> nomeAluno.getId().equals(aluno.getId()))
                .forEach(nomeAluno -> nomeAluno.setIdade(aluno.getIdade()));

    }


    //deletando por id
    public void delete(@PathVariable("id") Integer id) {
        alunos.removeIf(aluno -> aluno.getId().equals(id));
    }


    //Adicionando aluno
    public Integer add(@RequestBody Alunos aluno) {
        if (aluno.getId() == null) {
            aluno.setId(alunos.size() + 1);
        } else if (alunos.contains(aluno)) {
        } else
            alunos.add(aluno);
        return null;
    }

}
