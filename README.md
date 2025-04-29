# Conversor de Moedas 💰

Um simples conversor de moedas desenvolvido em Java como desafio do programa ONE (Oracle Next Education) da Oracle. Converte valores entre diferentes moedas internacionais usando taxas de câmbio atualizadas em tempo real.

## Funcionalidades ✨

- Conversão entre moedas pré-definidas
- Atualização automática das taxas de câmbio via API
- Interface de linha de comando (CLI) simples e intuitiva
- Opções de conversão disponíveis:
    - USD para ARS, BRL, BOB
    - ARS, BRL, BOB para USD

## Tecnologias Utilizadas 🛠️

- Java 24
- ExchangeRate API ([https://www.exchangerate-api.com](https://www.exchangerate-api.com))
- HttpClient (para consumo de API)
- Gson (para manipulação de JSON)

## Como Funciona 🔧

1. Obtém as taxas de câmbio atualizadas da ExchangeRate API
2. Converte o JSON recebido em objetos Java usando Gson
3. Realiza cálculos de conversão com base nas taxas obtidas
4. Exibe os resultados formatados para o usuário

## Contribuições 🤝

Sugestões e melhorias são bem-vindas:
- Reporte bugs através de issues
- Envie propostas de novas funcionalidades
- Faça fork e envie pull requests

---

Projeto desenvolvido como parte do [Oracle Next Education](https://www.oracle.com/br/education/oracle-next-education/)