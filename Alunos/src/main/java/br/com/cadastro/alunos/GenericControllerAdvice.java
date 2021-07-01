package br.com.cadastro.alunos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericControllerAdvice {

    Logger LOGGER = LoggerFactory.getLogger(GenericControllerAdvice.class);

    /*Essa configuracao vai ser globlal para todas as classes que implementam a classe RecursoInexistente*/
    @ExceptionHandler({AlunoNaoEncontrado.class})
    public ResponseEntity<String> handle(final  AlunoNaoEncontrado e) {
        final String alunoNulo = "Aluno Não Encontrado!!!";

        LOGGER.error(alunoNulo);
//        LOGGER.error(e.getMessage());
        return new ResponseEntity<>(alunoNulo, HttpStatus.NOT_FOUND);
    }
}
