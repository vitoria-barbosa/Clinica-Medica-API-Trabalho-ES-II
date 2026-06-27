<div align="center">

# 🏥 Clínica Médica API

### API REST para gerenciamento de pacientes, profissionais de saúde, agendamentos e consultas

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://docs.oracle.com/en/java/javase/21/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.15-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://docs.spring.io/spring-boot/index.html)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/docs/)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/guides/)
[![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)](https://swagger.io/docs/)

</div>

> API desenvolvida para o Trabalho de Engenharia de Software II do IFPI. Permite cadastrar pacientes, profissionais de
> saúde e recepcionistas, organizar a grade de horários dos profissionais e gerenciar agendamentos e consultas de uma
> clínica médica.

---

## 📋 Sobre o domínio

A API modela uma clínica médica com as seguintes regras principais:

- **Usuario** é uma entidade abstrata (herança `JOINED`) que generaliza `Paciente`, `ProfissionalSaude` e
  `Recepcionista`, todos com CPF, nome e telefone.
- Cada **ProfissionalSaude** pertence a uma **Especialidade** e possui uma **grade de horários** (combinações de `Dia` x
  `Turno`).
- **Agendamento** é a entidade base (herança `JOINED`) que relaciona profissional, paciente e recepcionista em uma
  data/hora.
- **Consulta** estende `Agendamento`, adicionando os dados clínicos (sintomas, diagnóstico, prescrição, exames e valor
  total).

---

## 👥 Atores e Histórias de Usuário

O sistema foi pensado a partir do seguinte levantamento de atores e histórias de usuário:

| Ator                  | Papel                                                                    |
|-----------------------|--------------------------------------------------------------------------|
| Paciente              | Recebe atendimento                                                       |
| Recepcionista         | Cadastra pacientes e profissionais e gerencia os agendamentos            |
| Profissional da Saúde | Realiza consultas, visualiza sua agenda e gerencia sua grade de horários |

| ID    | História de Usuário                                                                                                                                          |
|-------|--------------------------------------------------------------------------------------------------------------------------------------------------------------|
| HU01  | Como **recepcionista**, eu quero cadastrar profissionais da saúde, para que eles possam realizar consultas na clínica                                        |
| HU02  | Como **recepcionista**, eu quero cadastrar pacientes, para possibilitar o agendamento de consultas                                                           |
| HU03  | Como **recepcionista**, eu quero agendar consultas para pacientes, para definir data, horário e especialidade da consulta solicitada pelo paciente           |
| HU04  | Como **profissional da saúde**, eu quero visualizar minha agenda de atendimentos, para organizar minha rotina de trabalho                                    |
| HU05  | Como **recepcionista**, eu quero visualizar os agendamentos da clínica, para acompanhar as consultas marcadas                                                |
| HU06  | Como **profissional da saúde**, eu quero registrar a consulta realizada, para guardar informações importantes referente àquele atendimento                   |
| HU07  | Como **profissional da saúde**, eu quero definir minha grade de horários, para definir os dias e turnos que vou estar realizando os atendimentos na clínica  |

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia        | Versão | Uso                                   |
|-------------------|--------|---------------------------------------|
| Java              | 21     | Linguagem principal                   |
| Spring Boot       | 3.5.15 | Framework da aplicação                |
| Spring Data JPA   | —      | Persistência e repositórios           |
| Spring Validation | —      | Validação dos DTOs de entrada         |
| springdoc-openapi | 2.8.16 | Documentação Swagger/OpenAPI          |
| PostgreSQL        | —      | Banco de dados relacional             |
| Lombok            | —      | Redução de boilerplate (`@Data`)      |
| Maven (wrapper)   | —      | Build e gerenciamento de dependências |

---

## 📁 Estrutura de Pastas

```bash
clinica/
├── src/main/java/br/edu/ifpi/clinica/
│   ├── model/          # Entidades JPA (Usuario, Paciente, ProfissionalSaude, Recepcionista,
│   │                   #   Agendamento, Consulta, Especialidade, Dia, Turno, DiaTurno)
│   ├── dto/             # Records de entrada (RequestDTO) e saída (DTO) de cada entidade
│   ├── repository/      # Interfaces Spring Data JPA
│   ├── service/         # Regras de negócio e orquestração entre repositórios
│   ├── controller/      # Endpoints REST de cada recurso
│   ├── exception/       # Exceções customizadas + GlobalExceptionHandler
│   └── ClinicaApplication.java
│
├── src/main/resources/
│   └── application.properties   # Configurações do Spring e da conexão com o PostgreSQL
│
├── pom.xml
├── mvnw / mvnw.cmd      # Maven Wrapper (Linux/Mac e Windows)
└── .gitignore
```

---

## 📄 Responsabilidade de cada camada

### `model/`

Entidades JPA do domínio. `Usuario` e `Agendamento` usam herança `JOINED` para compartilhar campos comuns entre
`Paciente`/`ProfissionalSaude`/`Recepcionista` e entre `Agendamento`/`Consulta`, respectivamente.

### `dto/`

Implementados como **Java Records**. Os `RequestDTO` recebem e validam a entrada (`@NotBlank`, `@Positive`, `@Future`,
etc.) e expõem um método `toEntity()` para converter em entidade. Os `DTO` de saída expõem apenas os dados relevantes da
entidade (evitando, por exemplo, expor objetos relacionados completos).

### `repository/`

Interfaces `JpaRepository` responsáveis pelo acesso ao banco. Alguns repositórios expõem consultas customizadas (ex.:
busca de profissional já trazendo a grade de horários).

### `service/`

Camada com as regras de negócio: validação de relacionamentos (ex.: especialidade existente antes de cadastrar um
profissional), orquestração entre múltiplos repositórios e lançamento de exceções de domínio.

### `controller/`

Expõe os recursos REST. Cada controller é responsável por um único agregado (Paciente, ProfissionalSaude, Recepcionista,
Especialidade, Dia, Turno, Agendamento, Consulta).

### `exception/`

`GlobalExceptionHandler` centraliza o tratamento de erros (`RecursoNaoEncontradoException`, `DatabaseException`,
`DadoInvalidoException` e erros de validação do Bean Validation), padronizando as respostas de erro da API.

---

## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:

- Você tem o **Java 21** (JDK) instalado.
- Você tem uma instância do **PostgreSQL** rodando localmente (ou acessível remotamente).
- Você não precisa instalar o Maven manualmente — o projeto já inclui o **Maven Wrapper** (`mvnw` / `mvnw.cmd`).
- Compatível com `Windows`, `Linux` e `macOS`.

---

## ⚙️ Configuração do banco de dados

As configurações de conexão ficam em `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/clinica_bd
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha_aqui
```

---

## 🚀 Instalando e executando o projeto

Linux e macOS:

```bash
git clone https://github.com/matheusydev/Clinica-Medica-API-Trabalho-ES-II.git
cd Clinica-Medica-API-Trabalho-ES-II
./mvnw spring-boot:run
```

Windows:

```bash
git clone https://github.com/matheusydev/Clinica-Medica-API-Trabalho-ES-II.git
cd Clinica-Medica-API-Trabalho-ES-II
mvnw.cmd spring-boot:run
```

A API estará disponível em `http://localhost:8080/`

---

## ☕ Usando a API

### Documentação Swagger

```
http://localhost:8080/swagger-ui.html
```

### Exemplo — cadastrar um paciente

`POST /pacientes`

```json
{
  "cpf": "123.456.789-00",
  "nome": "Maria Silva",
  "telefone": "(86) 99999-0000",
  "idade": 32,
  "planoDeSaude": "Unimed",
  "historicoMedico": "Hipertensão controlada"
}
```

### Exemplo — criar um agendamento

`POST /agendamentos`

```json
{
  "dataHora": "2026-07-10T14:30:00",
  "profissionalId": 1,
  "pacienteId": 2,
  "recepcionistaId": 1
}
```

---

## 🗺️ Endpoints da API

### Pacientes — `/pacientes`

| Método   | Endpoint          | Descrição                 |
|----------|-------------------|---------------------------|
| `POST`   | `/pacientes`      | Cadastra um novo paciente |
| `GET`    | `/pacientes`      | Lista todos os pacientes  |
| `GET`    | `/pacientes/{id}` | Detalha um paciente       |
| `PUT`    | `/pacientes/{id}` | Atualiza um paciente      |
| `DELETE` | `/pacientes/{id}` | Remove um paciente        |

### Profissionais de Saúde — `/profissionais-da-saude`

| Método   | Endpoint                                                       | Descrição                                       |
|----------|----------------------------------------------------------------|-------------------------------------------------|
| `POST`   | `/profissionais-da-saude`                                      | Cadastra um profissional                        |
| `GET`    | `/profissionais-da-saude`                                      | Lista todos os profissionais                    |
| `GET`    | `/profissionais-da-saude/{id}`                                 | Detalha um profissional                         |
| `PUT`    | `/profissionais-da-saude/{id}`                                 | Atualiza um profissional                        |
| `DELETE` | `/profissionais-da-saude/{id}`                                 | Remove um profissional                          |
| `POST`   | `/profissionais-da-saude/{idProfissional}/grade-horarios`      | Adiciona um Dia x Turno à grade do profissional |
| `DELETE` | `/profissionais-da-saude/{idProfissional}/grade-horarios/{id}` | Remove um item da grade de horários             |

### Recepcionistas — `/recepcionistas`

| Método   | Endpoint               | Descrição                     |
|----------|------------------------|-------------------------------|
| `POST`   | `/recepcionistas`      | Cadastra uma recepcionista    |
| `GET`    | `/recepcionistas`      | Lista todas as recepcionistas |
| `GET`    | `/recepcionistas/{id}` | Detalha uma recepcionista     |
| `PUT`    | `/recepcionistas/{id}` | Atualiza uma recepcionista    |
| `DELETE` | `/recepcionistas/{id}` | Remove uma recepcionista      |

### Especialidades — `/especialidades`

| Método   | Endpoint                             | Descrição                                    |
|----------|--------------------------------------|----------------------------------------------|
| `POST`   | `/especialidades`                    | Cadastra uma especialidade                   |
| `GET`    | `/especialidades`                    | Lista todas as especialidades                |
| `GET`    | `/especialidades/{id}`               | Detalha uma especialidade                    |
| `PUT`    | `/especialidades/{id}`               | Atualiza uma especialidade                   |
| `DELETE` | `/especialidades/{id}`               | Remove uma especialidade                     |
| `GET`    | `/especialidades/{id}/profissionais` | Lista os profissionais daquela especialidade |

### Dias e Turnos — `/dias` e `/turnos`

| Método                            | Endpoint            | Descrição                                     |
|-----------------------------------|---------------------|-----------------------------------------------|
| `POST` / `GET` / `PUT` / `DELETE` | `/dias` (`/{id}`)   | CRUD completo de dias da semana               |
| `POST` / `GET` / `PUT` / `DELETE` | `/turnos` (`/{id}`) | CRUD completo de turnos (manhã, tarde, noite) |

### Agendamentos — `/agendamentos`

| Método   | Endpoint                                      | Descrição                             |
|----------|-----------------------------------------------|---------------------------------------|
| `POST`   | `/agendamentos`                               | Cria um novo agendamento              |
| `GET`    | `/agendamentos`                               | Lista todos os agendamentos           |
| `GET`    | `/agendamentos/{id}`                          | Detalha um agendamento                |
| `PUT`    | `/agendamentos/{id}`                          | Atualiza um agendamento               |
| `DELETE` | `/agendamentos/{id}`                          | Remove um agendamento                 |
| `GET`    | `/agendamentos/paciente/{pacienteId}`         | Lista agendamentos de um paciente     |
| `GET`    | `/agendamentos/profissional/{profissionalId}` | Lista agendamentos de um profissional |

### Consultas — `/consultas`

| Método   | Endpoint          | Descrição                  |
|----------|-------------------|----------------------------|
| `POST`   | `/consultas`      | Registra uma nova consulta |
| `GET`    | `/consultas`      | Lista todas as consultas   |
| `GET`    | `/consultas/{id}` | Detalha uma consulta       |
| `PUT`    | `/consultas/{id}` | Atualiza uma consulta      |
| `DELETE` | `/consultas/{id}` | Remove uma consulta        |

---

## 📌 Regras de negócio do Agendamento

O `AgendamentoService` concentra a validação das regras de negócio mais relevantes do sistema, aplicadas tanto na
criação (`POST`) quanto na atualização (`PUT`) de um agendamento:

| Regra                             | Validação aplicada                                                                                                    |
|-----------------------------------|-----------------------------------------------------------------------------------------------------------------------|
| Horário comercial                 | O agendamento só pode ser marcado entre **08:00 e 18:00**                                                             |
| Sem conflito para o profissional  | Um profissional não pode ter dois agendamentos na mesma data e horário                                                |
| Sem conflito para o paciente      | Um paciente não pode ter dois agendamentos na mesma data e horário                                                    |
| Limite por turno                  | Cada profissional pode ter no máximo **15 agendamentos** por turno (manhã: 08h–11h59, tarde: 14h–17h59)               |
| Grade de horários do profissional | O agendamento só é permitido no dia da semana e turno em que o profissional está cadastrado para atender (`DiaTurno`) |

Quando alguma regra é violada, o serviço lança `DadoInvalidoException`, que é capturada pelo `GlobalExceptionHandler` e
retornada como `400 Bad Request` com uma mensagem explicativa.

---

## 🧠 Decisões Técnicas

### DTOs como Java Records

Os DTOs de entrada e saída foram implementados como `record`, garantindo imutabilidade e reduzindo boilerplate. Os
`RequestDTO` concentram as validações via Bean Validation (`@NotBlank`, `@Positive`, `@Future`) e o método `toEntity()`
para conversão explícita em entidade.

### Herança `JOINED` em `Usuario` e `Agendamento`

`Usuario` (base de `Paciente`, `ProfissionalSaude` e `Recepcionista`) e `Agendamento` (base de `Consulta`) usam a
estratégia `InheritanceType.JOINED`, evitando duplicação de colunas comuns e mantendo uma tabela própria para os campos
específicos de cada subtipo.

### Camada de exceções centralizada

O `GlobalExceptionHandler` com `@ControllerAdvice` padroniza as respostas de erro (404 para recurso não encontrado, 400
para dados inválidos ou violação de regras de banco), evitando tratamento de erro repetido em cada controller.

### springdoc-openapi para documentação

Gera a documentação Swagger/OpenAPI automaticamente a partir do código, sem necessidade de manutenção manual de um
arquivo de especificação separado.

### Maven Wrapper

O uso do `mvnw`/`mvnw.cmd` garante que qualquer pessoa consiga compilar e rodar o projeto com a versão correta do Maven,
sem precisar instalá-lo globalmente.

---

## 🎓 Aprendizados Adquiridos

- ✅ Modelagem de herança JPA com `InheritanceType.JOINED`
- ✅ DTOs com Java Records e Bean Validation
- ✅ Arquitetura em camadas (Controller → Service → Repository → Model), separando apresentação, regra de negócio e
  persistência
- ✅ Tratamento global de exceções com `@ControllerAdvice`
- ✅ Documentação automática de API com springdoc-openapi/Swagger
- ✅ Relacionamentos `@ManyToOne`/`@OneToMany` entre profissionais, especialidades, dias e turnos
- ✅ Uso do Maven Wrapper para builds reprodutíveis
- ✅ Boas práticas REST (recursos no plural, sub-rotas para relacionamentos)
- ✅ Padrão de projeto **Repository**, isolando o acesso a dados da camada de regras de negócio
- ✅ Padrão de projeto **DTO**, separando os dados trafegados pela API das entidades de domínio
- ✅ Princípio SOLID **SRP** (Responsabilidade Única) aplicado na separação entre controllers, services, repositories e
  DTOs
- ✅ Estudo dos demais princípios SOLID (OCP, LSP, ISP, DIP) e reflexão sobre onde poderiam ser aplicados em evoluções
  futuras do projeto

---

## 🤝 Colaboradores

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/vitoria-barbosa" title="Perfil da Vitória no GitHub">
        <img src="https://github.com/vitoria-barbosa.png" width="100px;" alt="Foto de Vitória no GitHub"/><br>
        <sub>
          <b>Vitória Barbosa</b>
        </sub><br>
        <sub>Product Owner / Dev Team</sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/matheusydev" title="Perfil de Matheus no GitHub">
        <img src="https://github.com/matheusydev.png" width="100px;" alt="Foto de Matheus no GitHub"/><br>
        <sub>
          <b>Matheus Ylan</b>
        </sub><br>
        <sub>Dev Team</sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/kamilansc" title="Perfil da Kamila no GitHub">
        <img src="https://github.com/kamilansc.png" width="100px;" alt="Foto de Kamila no GitHub"/><br>
        <sub>
          <b>Kamila Rocha</b>
        </sub><br>
        <sub>Scrum Master</sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/MylenaDuartee" title="Perfil da Mylena no GitHub">
        <img src="https://github.com/MylenaDuartee.png" width="100px;" alt="Foto da Mylena no GitHub"/><br>
        <sub>
          <b>Mylena</b>
        </sub><br>
        <sub>Scrum Master</sub>
      </a>
    </td>
  </tr>
</table>