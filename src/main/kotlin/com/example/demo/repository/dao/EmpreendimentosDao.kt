package com.example.demo.repository.dao

import com.example.demo.repository.client.Empreendimento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface EmpreendimentosDao : JpaRepository<Empreendimento, Long> {

    fun findTop5ByCreatedAtBetweenOrderByMdaPotenciaOutorgadaKwDesc(
        inicio: LocalDateTime,
        fim: LocalDateTime
    ): List<Empreendimento>

}