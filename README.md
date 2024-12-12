# News Telegram Chat Bot

## Descrição

Este projeto é um sistema de entrega de notícias, que utiliza de técnicas de webscraping para solicitar notícias de um site e entregá-los ao usuário através do Telegram chat bot.

## Tecnologias Utilizadas

- **Java**
- **Spring Boot**
- **Jsoup**: Biblioteca utilizada para fazer o webscraping.
- **Lombok**: Biblioteca utilizada para reduzir o código boilerplate da aplicação.
- **Conceitos**: Clean Code e Clean Architecture.
  
## Funcionalidades

Quando enviado qualquer tipo de mensagem, o chat bot envia as notícias da região de São José dos Campos e Jacareí.

## Estrutura do Projeto

A estrutura do projeto segue os princípios de Clean Architecture, garantindo que o código seja modular, fácil de manter e escalável.

## Como Usar

Para utilizar este projeto, vá até o Telegram, pesquise por Bot Father, obtenha um novo bot com o comando /newbot, e troque as propriedades bot.name e bot.token no application.properties com os valores obtidos.
