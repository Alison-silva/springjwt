# Spring JWT
## Descrição do projeto

API feita para registro e login de usuários, utilizado Spring Security e autenticação JWT.

Como essa aplicação é somente o Backend, ela não renderisa os dados em página html.

# Tecnologias utilizadas

* Spring Boot
* Spring Tool Suite
* Postman
* PostgreSQL
* Spring Security
* Autenticação JWT


# Instruções de como rodar e utilizar o sistema

## Pré-requisito

* Git
* Spring Tool Suite
* PgAdmin
* Postman

## Passos

- Abrir um terminal ou linha de comando(Botão Windows + R, digitar cmd e apertar Enter (no Windows))
- Navegar até onde quiser baixar o repositório
- No terminal, colar essa linha e apertar Enter
  ```
  https://github.com/Alison-silva/springjwt.git
  ```
- Abrir O PgAdmin e criar um banco de dados com o nome springjwt
- Abrir o Spring Tool Suite e clicar em File e depois em Import
- Na nova janela que aparece, escrever no campo do pesquisa maven
- Uma pasta com o nome Maven aparece. Dentro dessa pasta, clicar em Existing Maven Projects e clicar em Next
- Na próxima página, clicar em Browse... e navegar até a pasta springjwt e clicar abrir.
- Clicar em Finish
- Esperar o download das dependências do Maven
- Clique com o botão direito no projeto e escolhe a opção 'Run as' e depois escolher 'Spring Boot App'
- O projeto está agora rodando.

Se ele apresentar um erro, abra o pacote src/main/resources e depois abra o arquivo application.properties.
Coloque o usuário e a senha que você atribuiu ao postgres
 ```
spring.datasource.username= <coloque seu username>
spring.datasource.password= <coloque sua senha>
```
- Após isso entre no PgAdmin, selecione o banco springjwt, e execute esse SQL:
```
INSERT INTO role(id, desc_role) VALUES (1, 'ADMIN');
```
- OBS: com isso um perfil de acesso é criado.


## Usando a aplicação

Abra o POSTMAN na aba Workspaces, selecione uma requisição POST e 
coloque END-POINT **http://localhost:8080/usuario/cadastrar** e 
registre um usuário:

```
{
    "nome":"João da Silva",
    "email":"joaosilva@email.com",
    "login":"joaosilva",
    "senha":"12345678"
}
```
**OBS: A API possui, listar e alterar usuário, possui também um END-POINT Home
que só será exibido após o login do usuário, o END-POINT de cadastro é o único liberado**

Após isso podemos realizar o login na API usando uma requisição POST e
no END-POINT **http://localhost:8080/login**
```
{
    "login":"joaosilva",
    "senha":"12345678"
}
```
Com o login realizado corretamente, o usuário receberá um Bearer token, esse 
token deve ser copiado:

```
"Bearer eyJhbGciOiJIUzUxMiJ9...."
```

** OBS: COPIAR SEM ASPAS  **

Para entrar na Home, use uma requisição GET com o END-POINT **localhost:8080/usuario/home**

**Entre na aba 'AUTHORIZATION', no campo TYPE selecione a opção 'Bearer Token',
e no campo 'token', insira o Bearer Token que foi copiado, após isso clique em send 
```
Hello World
```
**OBS:** A mensagem 'Hello World' deve aparecer, após isso o usuário poderá 
listar e atualizar os dados, usando o END-POINT **/usuario** com as requisições PUT E GET.
