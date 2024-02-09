# 🚀 Certification app
Um aplicativo desenvolvido em spring boot para gerar certificados com base em uma tecnologia enviada no corpo da requisição

## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://portfolio-leo-santam.vercel.app/)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/leonardolopessantana/)

## 🛠 Stack utilizada

![Spring boot](https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=Spring&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Postgres](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Git](https://img.shields.io/badge/git-100000?style=for-the-badge&logo=git&logoColor=white)
![Github](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)

## 🔗 Rotas
### QuestionController
- GET /questions/technology/{technology}
### StudentController
- POST /students/verify-if-has-certification
```json
{
	"email": "leonardolivelopes2@gmail.com",
	"technology": "JAVA"
}
```
- POST /students/certification/answer
```json
{
  "email": "leonardolivelopes2@gmail.com",
  "technology": "JAVA",
  "questionsAnswers": [
    {
      "questionID": "c5f02721-6dc3-4fa6-b46d-6f2e8dca9c66",
      "alternativeID": "bafdf631-6edf-482a-bda9-7dce1efb1890"
    },
    {
      "questionID": "b0ec9e6b-721c-43c7-9432-4d0b6eb15b01",
      "alternativeID": "f8e6e9b3-199b-4f0d-97ce-7e5bdc080da9"
    },
    {
      "questionID": "f85e9434-1711-4e02-9f9e-7831aa5c743a",
      "alternativeID": "d3e51a56-9b97-4bb8-9827-8bcf89f4b276"
    }
  ]
} 
```
### RankController
- GET /ranking/top-10

## Funcionalidades

- listar questões por uma tecnologia informada
- verificar se o usuário já tem um certificado
- receber a resposta do usuário, verificando se é a alternativa certa e o total de acertos
- listagem dos top 10 certificados, ordenados por maiores acertos

## Aprendizados
- Revisado conceitos base do spring como (service, controller, entity, repository)
- relaciomentos com JPA
- Primeira parte o que tenho na minha classe, para classe filha
  - ClasseAtualToClasseFilha (exemplo -> um estudante para muitas certificações -> OneToMany)
    - OneToOne
    - OneToMany
    - ManyToOne
    - ManyToMany
- Padrão service useCase
- criar um seed em java, sem depender do spring boot, apenas rodando um script sql
- criar um container em docker