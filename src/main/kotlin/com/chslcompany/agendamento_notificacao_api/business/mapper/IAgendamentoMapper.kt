package com.chslcompany.agendamento_notificacao_api.business.mapper

import com.chslcompany.agendamento_notificacao_api.controller.dto.`in`.AgendamentoRecord
import com.chslcompany.agendamento_notificacao_api.controller.dto.out.AgendamentoRecordOut
import com.chslcompany.agendamento_notificacao_api.infrastructure.entities.Agendamento
import com.chslcompany.agendamento_notificacao_api.infrastructure.entities.StatusNotificacaoEnum
import org.springframework.stereotype.Component
import java.time.LocalDateTime


@Component
class AgendamentoMapper {

    fun paraEntity(dto: AgendamentoRecord): Agendamento {
        return Agendamento(
            id = 0L,
            emailDestinatario = dto.emailDestinatario,
            telefoneDestinatario = dto.telefoneDestinatario,
            mensagem = dto.mensagem,
            dataHoraEnvio = dto.dataHoraEnvio
        )
    }

    fun paraOut(entity: Agendamento): AgendamentoRecordOut {
        return AgendamentoRecordOut(
            id = entity.id,
            mensagem = entity.mensagem ?: "",
            emailDestinatario = entity.emailDestinatario ?: "",
            telefoneDestinatario = entity.telefoneDestinatario ?: "",
            dataHoraEnvio = entity.dataHoraEnvio ?: LocalDateTime.now(),
            statusNotificacao = entity.statusNotificacao ?: StatusNotificacaoEnum.AGENDADO
        )
    }

    fun paraEntityCancelamento(entity: Agendamento): Agendamento {
        return entity.copy(
            dataHoraModificacao = LocalDateTime.now(),
            statusNotificacao = StatusNotificacaoEnum.CANCELADO
        )
    }}