package com.chslcompany.agendamento_notificacao_api.controller.dto.out

import com.chslcompany.agendamento_notificacao_api.infrastructure.entities.StatusNotificacaoEnum
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class AgendamentoRecordOut(
    val id: Long,
    val mensagem: String,
    val emailDestinatario: String,
    val telefoneDestinatario: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val dataHoraEnvio: LocalDateTime,
    val statusNotificacao: StatusNotificacaoEnum
)