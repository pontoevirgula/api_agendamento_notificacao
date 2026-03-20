package com.chslcompany.agendamento_notificacao_api.business

import com.chslcompany.agendamento_notificacao_api.business.mapper.AgendamentoMapper
import com.chslcompany.agendamento_notificacao_api.controller.dto.`in`.AgendamentoRecord
import com.chslcompany.agendamento_notificacao_api.controller.dto.out.AgendamentoRecordOut
import com.chslcompany.agendamento_notificacao_api.infrastructure.exception.NotFoundException
import com.chslcompany.agendamento_notificacao_api.infrastructure.repositories.AgendamentoRepository
import org.springframework.stereotype.Service


@Service
class AgendamentoService (
   private val  repository : AgendamentoRepository,
   private val  agendamentoMapper: AgendamentoMapper
) {

    fun gravarAgendamento(agendamentoRecord: AgendamentoRecord): AgendamentoRecordOut {
        return agendamentoMapper.paraOut(
            repository.save(agendamentoMapper.paraEntity(agendamentoRecord))
        )
    }

    fun buscarAgendamentosPorId(id: Long): AgendamentoRecordOut {
        return agendamentoMapper.paraOut(
            repository.findById(id)
                .orElseThrow { NotFoundException("Id não encontrado") }
        )
    }

    fun cancelarAgendamento(id: Long) {
        val agendamento = repository.findById(id)
            .orElseThrow { NotFoundException("Id não encontrado") }
        repository.save(
            agendamentoMapper.paraEntityCancelamento(agendamento)
        )
    }

}