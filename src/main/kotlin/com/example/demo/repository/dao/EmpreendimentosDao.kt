package com.example.demo.repository.dao

import com.example.demo.repository.client.Empreendimento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.data.domain.Pageable

@Repository
interface EmpreendimentosDao : JpaRepository<Empreendimento, Long> {
    fun findAllByOrderByMdaPotenciaOutorgadaKwDesc(pageable: Pageable): List<Empreendimento>
}