package com.chslcompany.agendamento_notificacao_api.infrastructure.entities

import jakarta.persistence.*
import lombok.*
import java.time.LocalDateTime

@Table(name = "agendamento")
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
data class Agendamento (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    var emailDestinatario: String? = null,
    var telefoneDestinatario: String? = null,
    var dataHoraEnvio: LocalDateTime? = null,
    var dataHoraAgendamento: LocalDateTime? = null,
    var dataHoraModificacao: LocalDateTime? = null,
    var mensagem: String? = null,
    var statusNotificacao: StatusNotificacaoEnum? = null
){
    @PrePersist
    private fun prePersist() {
        dataHoraAgendamento = LocalDateTime.now()
        statusNotificacao = StatusNotificacaoEnum.AGENDADO
    }
}