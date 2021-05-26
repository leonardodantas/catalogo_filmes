package com.api.catalogo.filmes.utils.constantes;

public class MoviesTMDBConstants {


    private MoviesTMDBConstants(){

    }

    public static final String ERROR_PAGE_BIGGER_500_SERVER = "page must be less than or equal to 500";
    public static String ERROR_PAGE_BIGGER_500_RESPONSE = "Pagina deve ser igual ou menor que 500";

    public static final String ERROR_ID_NOT_FOUND_SERVER = "The resource you requested could not be found.";
    public static final String ERROR_ID_NOT_FOUND_RESPONSE = "O recurso que você solicitou não foi encontrado.";
}
