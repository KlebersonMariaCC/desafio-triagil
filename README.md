# Descrição, pré requisitos e afins (e um mini tutorial de docker)

O projeto é uma api rest, feita com spring-boot para guardar times de pokemons (veja a especificação do problema no final).

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

Você também pode apenas rodar o codigo a partir do main da classe DesafioTriagilApplication.java na sua IDE preferida.

### Para rodar usando containers:

 Na raiz do projeto use o comando:

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
  * database, usuário e senha : o mesmo do .env na raiz do projeto.


Assim ele deve reconhecer o postgresql do container e dai vc consegue visualizar por lá.

### Apenas o container da aplicação

Você também pode criar apenas a imagem da api. Na raiz do projeto (onde está o Dockerfile):


```
docker build -t desafiotriagil .

```
Ou outro nome que preferir depois do -t. Para executar a imagem (na primeira vez):
```
docker run --name desfio-triagil-api --network=host desafiotriagil:latest -p 8080:8080

```
O --network é para o localhost do container apontar para o da máquina local, assim a api consegue interagir com o postgres instalado localmente na maquina (sem ser outro container) sem maiores confugrações.  o -p faz o mapeamento da porta  local do container com a da rede local da máquina (de fato não seria necessário com o --network junto ,mas caso queira usar um container separado do postgres sem usar o atributo é necessário para a api ficar visivel na porta 8080 da máquina). a flag --name será o nome do caontainer e fica a seu critério.

Quando vc desligar o sistema, o container continuará lá, mas apenas desativado. Se usarmos comando run de novo será feito outro container. Para usar o mesmo fazemos:

```
docker start -ai desfio-triagil-api

```
 -ai é para visualizarmos no terminal e não em segundo plano.

## Configuração

Na  pasta ./src/main/ressouces  é onde fica guardado o arquivo de configuração do springboot. Lá temos dois modelos: um para rodar localmente (e com um container da aplicação, com os comandos mais abaixo) e outro cpara rodar usando docker-compose. O arquivo que o spring boot ira ler ao iniciar/ser compilado é o application.properties. Dentro dele possui um atributo spring.config.import onde vc poderá importar qualquer uma das configurações dependendo de como irá rodar o sistema. Lembrando que como a aplicação é rodada atravś de um jar ,toda alteração no application.properties implica em fazer um rebuild caso use containers. Voce pode fazer isso com 

```
docker-compose down

```

que desativará e excluirá os containers usados pelo docker-compose, e dai podemos fazer de duas formas:

```
docker image remove desafiotriagil

```
removendo a imagem da api e então: 

```
docker builder prune

```
que vai apagar o cache do construtor de imagens, fazendo assim que a  próxima imagem gerada  seja feita com o código atualizado.


Caso queira uma opção mais agressiva ,temos

```
docker image prune

```
removendo as imagens não usadas.  dai seguimos com

```
docker builder prune

```
que vai apagar o cache do construtor de imagens, fazendo assim que a imagem seja feita com o código atualizado.


Se quiser apagar todos os dados do docker:

```
docker system prune

```
Isso apga todos os containers, redes, imagens e cache não usados por ao menos um container ativo, podendo afetar outros projetos feitos em docker na sua máquina, use com cuidado!!!



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

# Coisas  ainda a serem colocadas neste readme

 * Documentação swagger
 * O que foi usado no start.spring.io para incializar o  projeto
 * Outras coisas que posso ter errado/esquecido.

# Dúvidas 

kleberson.maria@ccc.ufcg.edu.br


