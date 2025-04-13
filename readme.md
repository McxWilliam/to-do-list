# To-Do List App

Este é um simples aplicativo de **To-Do List** construído com **Spring Boot** e **Java**, permitindo aos usuários adicionar, listar, editar, excluir e marcar tarefas como concluídas.

A aplicação também possui um **menu de ajuda** que utiliza a integração com o **ChatGPT** para responder perguntas dos usuários sobre o uso da aplicação.

---

## 🚀 Funcionalidades

- **Adicionar Tarefas**: Crie novas tarefas para organizar suas atividades.
- **Listar Tarefas**: Veja todas as tarefas que você adicionou.
- **Editar Tarefas**: Modifique uma tarefa existente.
- **Excluir Tarefas**: Remova uma tarefa que não é mais necessária.
- **Marcar Tarefas como Concluídas**: Marque suas tarefas como concluídas, permitindo um melhor acompanhamento.
- **Menu de Ajuda**: Pergunte ao ChatGPT sobre o uso da aplicação e obtenha respostas automáticas.

---

## 🛠️ Tecnologias Usadas

- **Spring Boot** - Framework Java para facilitar o desenvolvimento de aplicações web.
- **Spring WebClient** - Biblioteca para comunicação reativa via HTTP com a API do ChatGPT.
- **Java 21** - Versão utilizada para garantir o suporte mais recente.
- **OpenAI API** - Integração com o ChatGPT para fornecer ajuda interativa na aplicação.

---

## 🔧 Como Rodar o Projeto

### Pré-requisitos

Antes de rodar o projeto, certifique-se de que você tem o seguinte instalado:

- **JDK 21** (ou superior)
- **Maven** (para compilar o projeto)
- **API Key da OpenAI** (para integração com o ChatGPT)

### Passos

1. Clone o repositório:

    ```bash
    git clone https://github.com/seu-usuario/to-do-list-app.git
    cd to-do-list-app
    ```

2. Adicione sua chave da API da OpenAI no `application.properties`:

    ```properties
    openai.api.key=sk-xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    ```

   > Caso não tenha uma chave, você pode obter uma em [OpenAI Platform](https://platform.openai.com).

3. Compile o projeto com Maven:

    ```bash
    mvn clean install
    ```

4. Rode o projeto:

    ```bash
    mvn spring-boot:run
    ```

5. Acesse a aplicação no terminal e utilize o menu interativo para gerenciar suas tarefas.

---

## ⚙️ Como Funciona a Integração com o ChatGPT

No menu de ajuda, o usuário pode digitar uma pergunta e a aplicação envia essa pergunta para a API da OpenAI usando o **Spring WebClient**. O ChatGPT responde com uma explicação sobre como usar a aplicação, com base nas perguntas feitas.

```java
@Autowired
private ChatGPTService chatGPTService;

case 5 -> {
            try{
                 menu.invocaAjuda();
                 String pergunta = scanner.nextLine();
                 String resposta = chatGPTService.perguntar(pergunta);
                 System.out.println(resposta);
            } catch (Exception e){
                 System.out.println("Erro ao consultar a IA.");
                 }
          }
