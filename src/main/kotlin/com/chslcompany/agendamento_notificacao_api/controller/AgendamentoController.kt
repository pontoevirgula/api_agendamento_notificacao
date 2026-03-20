package com.chslcompany.agendamento_notificacao_api.controller

import com.chslcompany.agendamento_notificacao_api.business.AgendamentoService
import com.chslcompany.agendamento_notificacao_api.controller.dto.`in`.AgendamentoRecord
import com.chslcompany.agendamento_notificacao_api.controller.dto.out.AgendamentoRecordOut
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/agendamento")
class AgendamentoController(
    private val agendamentoService: AgendamentoService
) {

    @PostMapping
    fun gravarAgendamentos(
        @RequestBody agendamento: AgendamentoRecord
    ): ResponseEntity<AgendamentoRecordOut> {
        return ResponseEntity.ok(agendamentoService.gravarAgendamento(agendamento))
    }

    @GetMapping("/{id}")
    fun buscarAgendamentoPorId(
        @PathVariable("id") id: Long
    ): ResponseEntity<AgendamentoRecordOut> {
        return ResponseEntity.ok(agendamentoService.buscarAgendamentosPorId(id))
    }

    @DeleteMapping("/{id}")
    fun cancelarAgendamento(
        @PathVariable("id") id: Long
    ): ResponseEntity<Void> {
        agendamentoService.cancelarAgendamento(id)
        return ResponseEntity.accepted().build()
    }
}