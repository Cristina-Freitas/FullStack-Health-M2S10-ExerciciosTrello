package br.senai.lab365.medicos.exceptions;

import br.senai.lab365.medicos.dto.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class TratamentoErros {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handleEntityNotFound() {

        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateCrm(DuplicateKeyException exception) {
        ErrorResponse error = new ErrorResponse("CRM", "O CRM já existe");
        //error.setField("crm");
        //error.setMessage("O CRM já existe");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInadequateData(HttpMessageNotReadableException exception) {
        String message = exception.getMessage();

        if (message.contains("LocalDate")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("dataNascimento", "Data em formato inválido. Utilize dd/MM/yyyy."));
        }
        if (message.contains("EspecialidadeEnum")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("especialidade", "Enum em formato inválido. A especialidade deve ser maiúscula e sem espaços conforme a lista. Verifique na mensagem a seguir: " + exception.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Outro campo", exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>> handleValidationError(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getFieldErrors();
        List<ErrorResponse> errors = fieldErrors.stream()
                .map(ErrorResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
