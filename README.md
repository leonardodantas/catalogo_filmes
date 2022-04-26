
# CATALOGO-FILMES

<p>
API para acesso a algumas funcionalidades do site TMDB
</p>

### :pushpin: Features

- [x] Buscar filmes.
- [x] Buscar detalhes.
- [x] Buscar palavras chaves.
- [x] Buscar reviews.
- [x] Buscar filmes semelhantes.
- [x] Buscar trailers.

### :hammer: Pré-requisitos

Para realização das buscas será necessário a geração de uma API KEY. O passo a passo está disponível no seguinte endereço: https://developers.themoviedb.org/3/getting-started/introduction 

## Refatoração

A primeira versão do projeto teve o seu desenvolvimento finalizado em 21/06/2021. No dia 10/04/2022 iniciei a refatoração do projeto, buscando empregar as melhores
praticas do mundo da programação. As seguintes mudançãs foram adicionadas ao projeto.

- Atualização Java 11 para o Java 17
- Atualização versão do spring
- Alteração das camadas da aplicação para algo mais próximo de clean arch
- Alteração de nomes das classes e métodos para uma melhor interpretação
- Melhoria na divisão de responsabilidades entre as classes
- Melhoria no tratamento de erros
- Criação de uma classe dinâmica para a geração de URLs
- Utilização do conceito de inversão de dependências
- Aplicação dos conceitos de SOLID


A implementação original pode ser conferida na seguinte branch:
https://github.com/leonardodantas/catalogo_filmes

Já a refatoração pode ser conferida na branch:
https://github.com/leonardodantas/catalogo_filmes/tree/feature/refactor-project
## Documentação da API


### Retorna uma página com os filmes

```
  GET /catalogo/filmes/v1/movies/tmbd
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `apiKey` | `Header` | **Obrigatório**. Key TMDB |
| `page` | `int` | **Obrigatório**. Página da requisição |
| `language` | `String` | **Obrigatório**. Idioma do retorno |
| `request` | `String` | **Obrigatório**. Categoria do retorno |

### Retorna os detalhes de um filme

```
  GET /catalogo/filmes/v1/movies/details
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `apiKey` | `Header` | **Obrigatório**. Key TMDB |
| `language` | `String` | **Obrigatório**. Idioma do retorno |
| `movie` | `int` | **Obrigatório**. ID do filme |

### Retorna as palavras chaves para o filme
```
  GET /catalogo/filmes/v1/movies/keyword
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `apiKey` | `Header` | **Obrigatório**. Key TMDB |
| `movie` | `int` | **Obrigatório**. ID do filme |

### Retorna os reviews do filme
```
  GET /catalogo/filmes/v1/movies/review
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `apiKey` | `Header` | **Obrigatório**. Key TMDB |
| `movie` | `int` | **Obrigatório**. ID do filme |
| `page` | `int` | **Obrigatório**. Página da requisição |

### Retorna uma página com os filmes semelhantes

```
  GET /catalogo/filmes/v1/movies/similar
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `apiKey` | `Header` | **Obrigatório**. Key TMDB |
| `page` | `int` | **Obrigatório**. Página da requisição |
| `language` | `String` | **Obrigatório**. Idioma do retorno |
| `movie` | `int` | **Obrigatório**. ID do filme |

### Retorna os trailers
```
  GET /catalogo/filmes/v1/movies/videos
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `apiKey` | `Header` | **Obrigatório**. Key TMDB |
| `movie` | `int` | **Obrigatório**. ID do filme |
| `page` | `int` | **Obrigatório**. Página da requisição |

## Tecnologias

<div style="display: inline_block">

  <img align="center" alt="java" src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white" />
  <img align="center" alt="spring" src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" />
  <img align="center" alt="swagger" src="https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white" />

</div>


### :sunglasses: Autor
Criado por Leonardo Rodrigues Dantas.

[![Linkedin Badge](https://img.shields.io/badge/-Leonardo-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/leonardo-rodrigues-dantas/)](https://www.linkedin.com/in/leonardo-rodrigues-dantas/) 
[![Gmail Badge](https://img.shields.io/badge/-leonardordnt1317@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:leonardordnt1317@gmail.com)](mailto:leonardordnt1317@gmail.com)

## Licença
Este projeto esta sobe a licença MIT.
