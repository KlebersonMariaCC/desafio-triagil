# Descrição, pré requisitos e afins

O projeto é uma api rest, feita com spring-boot para guardar times de pokemons (veja a especificação do problema abaixo)

## Requisitos

Para rodar api localmente, java 17+, maven 3.9+ e postgresql (pgAdmin4 opicional)

Para rodar usando containers,  java 17+ ,docker docker-compose (a imagem do postgres e pgAdmin é baixada automaticamente)

## Execução

### Para rodar localmente:

 Na raiz do projeto faça os seguintes comandos:
 
```
mvn package -DskipTests
```
```
java -jar ./target/desafio-triagil-0.0.1-SNAPSHOT.jar

```
Além disso é necessário alterar o application.properties para apontar para o banco postgres local da máquina.

### Para rodar usando containers:

```
docker-compose up

```

O docker automaticamente faz o empacotamento, baixa um banco postgres e o pgAdmin, tudo junto.
 
Para rodar o pgAdmin vá para:

https://localhost:5050/

E use as credenciais colocadas no docker-compose.yml (email padrão: admin@mail.com e senha padrão: admin)

crie um servidor com o nome que preferir, mas com os atributos:

  * connection: postgresql
  * porta: 5432
  * database, usuário e senha : o mesmo do .env


Assim ele deve reconhecer o postgresql do container e dai vc consegue visualizar por lá

Você também pode criar apenas a imagem da api. Na raiz do projeto (onde está o Dockerfile):


```
docker build -t desafio-triagil

```
Ou outro nome que preferir. Para executar a imagem (na primeira vez):
```
docker run -p 8080:8080 --name desafio-triagil

```
# Especificação do problema

## Desafio Triágil

Olá! Nesse repositório você vai encontrar as instruções necessárias pra participar do desafio Triágil. O objetivo aqui é testar os seus conhecimentos de programação (e de Google), valendo uma vaga de estágio na equipe da Triágil.

O desafio é o seguinte: Você precisa desenvolver uma API na linguagem de programação que se sentir mais confortável, pra montar times de Pokémons, pra isso, você vai usar a [pokeapi.co](https://pokeapi.co/). O usuário que utilizar sua API deve conseguir gerar um novo time, passando como parâmetro uma lista de pokémons e um nome de usuário. Você deve então ler essa lista, buscar pelos pokémons na pokeAPI e caso não encontre nenhum erro, salvar esse time da maneira que preferir (pode ser em memória, banco de dados, arquivo de texto...). Com o time salvo, você retorna ao usuário uma mensagem de validação e uma **id** unica, para identificar aquele time. Além disso, você deve disponibilizar uma rota para leitura dos dados, uma que busque um time pelo usuário.

Daí com sua API pronta, você só precisa gerar um Dockerfile e um compose! A ideia aqui é que qualquer pessoa com acesso ao seu código, consiga instanciar a sua API e realizar testes (e pode ter certeza que vamos testar).

### Requisitos

- Rotas

  - GET /api/teams - Deverá listar todos os times registrados
  - GET /api/teams/{user} - Busca um time registrado por usuário
  - POST /api/teams - Rota para criação de um time, que recebe um JSON nesse formato [aqui](#exemplo-input)

* As rotas devem retornar erro quando o input contém algum erro (nome de pokémon inválido, etc...). A mensagem de erro fica a seu critério, desde que esteja claro para o usuário onde está o problema.

* Ao registrar o time, você deve guardar além do nome do Pokémon, alguns de seus dados disponíveis na PokeAPI. São eles: o ID do Pokémon (de acordo com a pokédex), sua altura e seu peso. Esses dados devem ser retornados junto do time quando buscado no sistema. Exemplo [aqui]()

### Avaliação

Você será avaliado com base na qualidade do código, na implementação dos requisitos e em sua capacidade de criar uma API funcional e bem documentada. Além disso, sua habilidade em configurar e compartilhar a aplicação em um container Docker será considerada, já que utilizaremos desse Docker para realizar os testes.

### Exemplos

#### Output /api/teams/{user}

```json
{
  "owner": "sleao",
  "pokemons": [
    {
      "id": 9,
      "name": "blastoise",
      "weight": 855,
      "height": 16
    },
    {
      "id": 25,
      "name": "pikachu",
      "weight": 60,
      "height": 4
    }
  ]
}
```

#### Output /api/teams

```json
{
  "1": {
    "owner": "sleao",
    "pokemons": [
      {
        "id": 9,
        "name": "blastoise",
        "weight": 855,
        "height": 16
      },
      {
        "id": 25,
        "name": "pikachu",
        "weight": 60,
        "height": 4
      }
    ]
  },
  "2": {
    "owner": "sleao",
    "pokemons": [
      {
        "id": 9,
        "name": "blastoise",
        "weight": 855,
        "height": 16
      },
      {
        "id": 25,
        "name": "pikachu",
        "weight": 60,
        "height": 4
      },
      {
        "id": 3,
        "name": "venusaur",
        "weight": 1000,
        "height": 20
      },
      {
        "id": 6,
        "name": "charizard",
        "weight": 905,
        "height": 17
      },
      {
        "id": 131,
        "name": "lapras",
        "weight": 2200,
        "height": 25
      },
      {
        "id": 54,
        "name": "psyduck",
        "weight": 196,
        "height": 8
      }
    ]
  }
}
```

#### Input

```json
{
  "user": "sleao",
  "team": [
    "blastoise",
    "pikachu",
    "charizard",
    "venusaur",
    "lapras",
    "dragonite"
  ]
}
```
