
# Agendador de notificações

Esse projeto foi desenvolvido pra estudo de backend e posteriormente as APIs serem consumidas por um app Android

## Rodando localmente

Clone o projeto

```bash
  git clone https://github.com/pontoevirgula/api_agendamento_notificacao
```
Entre no diretório do projeto

```bash
  docker-compose up --build
```



## Referência da API

#### Salve um agendamento

```http
  POST /agendamento
```

| Body | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `emailDestinatario` | `string` | **Obrigatório**. email do destinatario da notificacao |
| `telefoneDestinatario` | `string` | **Obrigatório** telefone do destinatario da notificacao
| `mensagem` | `string` | **Obrigatório** Mensagem da notificacao
| `dataHoraEnvio` | `LocalDateTime` | **Obrigatório**. data e hora do envio no formato yyyy-HH-ss




#### Busca item

```http
  GET /agendamento/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Obrigatório**. Id do agendamento que você quer buscar|


#### Cancela item

```http
  DELETE /agendamento/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Obrigatório**. Id do agendamento que você quer cancelar|






## Rodando testes

Para rodar os testes, rode o seguinte comando

```bash
  mvn test
```

