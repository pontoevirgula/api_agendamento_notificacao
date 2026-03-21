package com.chslcompany.agendamento_notificacao_api.business

import com.chslcompany.agendamento_notificacao_api.business.mapper.AgendamentoMapper
import com.chslcompany.agendamento_notificacao_api.controller.dto.`in`.AgendamentoRecord
import com.chslcompany.agendamento_notificacao_api.controller.dto.out.AgendamentoRecordOut
import com.chslcompany.agendamento_notificacao_api.infrastructure.entities.Agendamento
import com.chslcompany.agendamento_notificacao_api.infrastructure.entities.StatusNotificacaoEnum
import com.chslcompany.agendamento_notificacao_api.infrastructure.repositories.AgendamentoRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.LocalDateTime
import kotlin.test.Test


@ExtendWith(MockitoExtension::class)
class AgendamentoServiceTest {

    @InjectMocks
    private lateinit var agendamentoService: AgendamentoService

    @Mock
    private lateinit var agendamentoRepository: AgendamentoRepository

    @Mock
    private lateinit var agendamentoMapper: AgendamentoMapper

    private lateinit var agendamentoRecord: AgendamentoRecord
    private lateinit var agendamentoRecordOut: AgendamentoRecordOut
    private lateinit var agendamentoEntity: Agendamento

    @BeforeEach
    fun setUp() {
        agendamentoEntity = Agendamento(
            id = 1L,
            emailDestinatario = "email@email.com",
            telefoneDestinatario = "55887996578",
            dataHoraEnvio = LocalDateTime.of(2025, 1, 2, 11, 1, 1),
            dataHoraAgendamento = LocalDateTime.now(),
            dataHoraModificacao = null,
            mensagem = "Favor retornar a loja com urgência",
            statusNotificacao = StatusNotificacaoEnum.AGENDADO
        )

        agendamentoRecord = AgendamentoRecord(
            emailDestinatario = "email@email.com",
            telefoneDestinatario = "55887996578",
            mensagem = "Favor retornar a loja com urgência",
            dataHoraEnvio = LocalDateTime.of(2025, 1, 2, 11, 1, 1)
        )

        agendamentoRecordOut = AgendamentoRecordOut(
            id = 1L,
            emailDestinatario = "email@email.com",
            telefoneDestinatario = "55887996578",
            mensagem = "Favor retornar a loja com urgência",
            dataHoraEnvio = LocalDateTime.of(2025, 1, 2, 11, 1, 1),
            statusNotificacao = StatusNotificacaoEnum.AGENDADO
        )
    }

    @Test
    fun `deve gravar agendamento com sucesso`() {
        whenever(agendamentoMapper.paraEntity(agendamentoRecord)).thenReturn(agendamentoEntity)
        whenever(agendamentoRepository.save(agendamentoEntity)).thenReturn(agendamentoEntity)
        whenever(agendamentoMapper.paraOut(agendamentoEntity)).thenReturn(agendamentoRecordOut)

        val out = agendamentoService.gravarAgendamento(agendamentoRecord)

        verify(agendamentoMapper, times(1)).paraEntity(agendamentoRecord)
        verify(agendamentoRepository, times(1)).save(agendamentoEntity)
        verify(agendamentoMapper, times(1)).paraOut(agendamentoEntity)
        assertThat(out).usingRecursiveComparison().isEqualTo(agendamentoRecordOut)
    }
}