# futureStack - ClimaESP

Sistema inteligente de monitoramento climático com base em dados coletados por dispositivos IoT e pesquisas por cidade. Inclui histórico de buscas e favoritos por usuário, com persistência em banco de dados e autenticação via JWT.

## 📋 Descrição da Solução

O ClimaESP é uma solução tecnológica que permite o monitoramento e a consulta de dados climáticos como temperatura, umidade, chuva, vento etc.., com base nas cidades pesquisadas pelos usuários.

Utilizando dispositivos IoT para coleta em tempo real, os dados são armazenados em um banco de dados e apresentados de forma acessível, contribuindo para a conscientização ambiental, planejamento de atividades e prevenção de riscos climáticos como enchentes ou ondas de calor.

Cada cidade pesquisada é registrada em um histórico e pode ser favoritada, facilitando o acompanhamento contínuo por parte de agricultores, moradores e gestores públicos. O ClimaESP combina tecnologia e boas práticas para ajudar pessoas, proteger o meio ambiente e antecipar problemas causados por mudanças climáticas.

## Essa API permite:

- Consulta de dados climáticos por cidade
- Registro de histórico de pesquisas por usuário
- Marcar cidades como favoritas
- Cadastro e login de usuários com autenticação JWT
- Paginação, ordenação e filtros
- Validação de dados com Bean Validation

## Principais Endpoints (CRUD):

- 🌆 Dados Climáticos

Listar todos os dados
GET /dados

```
http://localhost:8080/dados
```

Listar por cidade + salvar no histórico (autenticado)
GET /dados?cidade=Campinas&idUsuario=1&page=0&size=10

```
http://localhost:8080/dados?cidade=Campinas&idUsuario=8
```

- 💾 Histórico de Pesquisas

Listar histórico do usuário
GET /historico/{idUsuario}

```
http://localhost:8080/historico/8
```

- ⭐ Favoritos

Favoritar cidade
POST /favoritos
Body (JSON):

```
{
  "idUsuario": 8,
  "cidade": "São Paulo",
  "latApi": -23.5505,
  "lonApi": -46.6333
}
```

Listar favoritos de um usuário
GET /favoritos/{idUsuario}

```
http://localhost:8080/favoritos/8
```

Remover favorito
DELETE /favoritos/{idFavorito}

```
http://localhost:8080/favoritos/8
```

- 👤 Usuário

Criar novo usuário
POST /users

```
{
  "nomeUser": "LauraSilva",
  "email": "laura@email.com",
  "password": "129056"
}
```

Login com JWT
POST /login

```
{
  "email": "maria@email.com",
  "password": "123456"
}
```

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot 
- Spring Web
- Spring Data JPA
- Spring Security + JWT
- Spring Validation (Bean Validation)
- Lombok
- Swagger
- Oracle

## 🛠️ Como Rodar o Projeto Localmente

1. **Clone o repositório:**

```bash
https://github.com/MaryChriss/gs-java1.git
cd gs-java1
```

2. **.env**
Crie um arquivo .env na raiz do projeto com as informações do banco de dados:

```bash
DB_URL=jdbc:oracle:thin:@oracle.fiap.com.br:PORTA:SID
DB_USER=RM******
DB_PASSWORD=******
```

4. **Importante**
Para rodar localmente, altere o package da classe principal GsApplication para:

```
public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure().load();

        System.setProperty("DB_URL", dotenv.get("DB_URL"));
        System.setProperty("DB_USER", dotenv.get("DB_USER"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

        SpringApplication.run(GsApplication.class, args);
    }
```

## 🧠 Links

-Deploy Nuvem:
```
http://191.232.36.235:8080/
```

- Swagger
```
http://localhost:8080/swagger-ui/index.html#/
```

- Repositorio Front-End:
```
https://github.com/MaryChriss/gs-mobile1
```
  
## 👥 Integrantes

- Mariana Christina RM: 554773
- Gabriela Moguinho RM: 556143
- Henrique Maciel RM: 556480
