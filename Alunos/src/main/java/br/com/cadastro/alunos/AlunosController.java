package br.com.cadastro.alunos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunosController {

    final static Logger LOGGER = LoggerFactory.getLogger(AlunoNaoEncontrado.class);


    private final List<Alunos> alunos;

    @Autowired
    private AlunoServiceRepository alunoServiceRepository;


    public AlunosController() {

        this.alunos = new ArrayList<>();
    }


    @GetMapping
    public Object findAll(@RequestParam(required = false) String nome, Integer idade) throws AlunoNaoEncontrado {
        try {
            return alunoServiceRepository.findAll(nome, idade);
        } catch (AlunoNaoEncontrado encontrado) {
            throw new AlunoNaoEncontrado();
        }
    }

    @GetMapping("/{id}")
    public Alunos findById(@PathVariable("id") Integer id) {
        return alunoServiceRepository.findById(id);
    }


    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody Alunos aluno) {
        Integer id = alunoServiceRepository.add(aluno);
        return new ResponseEntity<>(aluno.getId(), HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity update(@RequestBody final Alunos aluno) {
        alunoServiceRepository.update(aluno);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        alunoServiceRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}