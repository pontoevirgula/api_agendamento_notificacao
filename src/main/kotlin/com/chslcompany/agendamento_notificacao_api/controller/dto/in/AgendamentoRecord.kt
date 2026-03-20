package com.chslcompany.agendamento_notificacao_api.controller.dto.`in`

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class AgendamentoRecord(
    val emailDestinatario: String,
    val telefoneDestinatario: String,
    val mensagem: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    val dataHoraEnvio: LocalDateTime
)