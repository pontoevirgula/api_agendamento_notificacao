package com.chslcompany.agendamento_notificacao_api.infrastructure.repositories

import com.chslcompany.agendamento_notificacao_api.infrastructure.entities.Agendamento
import org.springframework.data.jpa.repository.JpaRepository

interface AgendamentoRepository : JpaRepository<Agendamento, Long> {

}