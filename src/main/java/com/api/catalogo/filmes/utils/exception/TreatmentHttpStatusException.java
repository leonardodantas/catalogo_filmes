package com.api.catalogo.filmes.utils.exception;

import com.api.catalogo.filmes.utils.constantes.MoviesTMDBConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Component
public class TreatmentHttpStatusException {

    public void handlerHttpStatusException(HttpStatusCodeException error) throws ResponseStatusException{
        if(Objects.requireNonNull(error.getMessage()).contains(MoviesTMDBConstants.ERROR_PAGE_BIGGER_500_SERVER)){
            throw new ResponseStatusException(HttpStatus.valueOf(error.getRawStatusCode()), MoviesTMDBConstants.ERROR_PAGE_BIGGER_500_RESPONSE);
        }

        if(Objects.requireNonNull(error.getMessage()).contains(MoviesTMDBConstants.ERROR_ID_NOT_FOUND_SERVER)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MoviesTMDBConstants.ERROR_ID_NOT_FOUND_RESPONSE);
        }

        if(Objects.requireNonNull(error.getMessage()).contains(MoviesTMDBConstants.ERROR_ID_NOT_FOUND_SERVER)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MoviesTMDBConstants.ERROR_ID_NOT_FOUND_RESPONSE);
        }
        if(Objects.requireNonNull(error.getMessage()).contains(MoviesTMDBConstants.ERROR_PAGE_BIGGER_500_SERVER)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MoviesTMDBConstants.ERROR_PAGE_BIGGER_500_RESPONSE);
        }
    }

}
