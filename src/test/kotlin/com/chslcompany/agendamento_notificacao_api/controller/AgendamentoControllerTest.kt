package com.chslcompany.agendamento_notificacao_api.controller

import com.chslcompany.agendamento_notificacao_api.business.AgendamentoService
import com.chslcompany.agendamento_notificacao_api.controller.dto.`in`.AgendamentoRecord
import com.chslcompany.agendamento_notificacao_api.controller.dto.out.AgendamentoRecordOut
import com.chslcompany.agendamento_notificacao_api.infrastructure.entities.StatusNotificacaoEnum
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.time.LocalDateTime
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import kotlin.test.Test

@ExtendWith(MockitoExtension::class)
class AgendamentoControllerTest {

    @Mock
    private lateinit var agendamentoService: AgendamentoService

    @InjectMocks
    private lateinit var agendamentoController: AgendamentoController

    private lateinit var mockMvc: MockMvc
    private val objectMapper = ObjectMapper()
    private lateinit var agendamentoRecord: AgendamentoRecord
    private lateinit var agendamentoRecordOut: AgendamentoRecordOut

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(agendamentoController).build()
        objectMapper.registerModule(JavaTimeModule())

        agendamentoRecord = AgendamentoRecord(
            emailDestinatario = "email@email.com",
            telefoneDestinatario = "55887996578",
            mensagem = "Favor retornar a loja com urgência",
            dataHoraEnvio = LocalDateTime.of(2026, 1, 2, 11, 1, 1)
        )

        agendamentoRecordOut = AgendamentoRecordOut(
            id = 1L,
            emailDestinatario = "email@email.com",
            telefoneDestinatario = "55887996578",
            mensagem = "Favor retornar a loja com urgência",
            dataHoraEnvio = LocalDateTime.of(2026, 1, 2, 11, 1, 1),
            statusNotificacao = StatusNotificacaoEnum.AGENDADO
        )
    }

    @Test
    fun `deve criar agendamento com sucesso`() {
        whenever(agendamentoService.gravarAgendamento(agendamentoRecord)).thenReturn(agendamentoRecordOut)

        mockMvc.perform(
            post("/agendamento")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(agendamentoRecord))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.emailDestinatario").value("email@email.com"))
            .andExpect(jsonPath("$.telefoneDestinatario").value(agendamentoRecordOut.telefoneDestinatario))
            .andExpect(jsonPath("$.mensagem").value(agendamentoRecordOut.mensagem))
            .andExpect(jsonPath("$.dataHoraEnvio").value("2026-01-02 11:01:01"))
            .andExpect(jsonPath("$.statusNotificacao").value("AGENDADO"))

        verify(agendamentoService, times(1)).gravarAgendamento(agendamentoRecord)
    }
}