package com.example.demo.repository.client

import com.example.demo.utils.BigDecimalBRDeserializer
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import jakarta.persistence.*

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "empreendimentos")
data class Empreendimento(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @JsonProperty("DatGeracaoConjuntoDados")
    val datGeracaoConjuntoDados: LocalDate,

    @JsonProperty("DatRalie")
    val datRalie: LocalDate,

    @JsonProperty("IdeNucleoCEG")
    @Column(length = 255)
    var ideNucleoCEG: String,

    @JsonProperty("CodCEG")
    @Column(length = 255)
    var codCEG: String,

    @JsonProperty("SigUFPrincipal")
    @Column(length = 255)
    var sigUFPrincipal: String,

    @JsonProperty("DscOrigemCombustivel")
    @Column(length = 255)
    var dscOrigemCombustivel: String,

    @JsonProperty("SigTipoGeracao")
    @Column(length = 255)
    var sigTipoGeracao: String,

    @JsonProperty("NomEmpreendimento")
    @Column(length = 255)
    var nomEmpreendimento: String,

    @JsonDeserialize(using = BigDecimalBRDeserializer::class)
    @JsonProperty("MdaPotenciaOutorgadaKw")
    val mdaPotenciaOutorgadaKw: BigDecimal,

    @JsonProperty("DscPropriRegimePariticipacao")
    @Column(length = 255)
    var dscPropriRegimePariticipacao: String,

    @JsonProperty("DscTipoConexao")
    @Column(length = 255)
    var dscTipoConexao: String,

    @JsonProperty("NomConexao")
    @Column(length = 255)
    var nomConexao: String,

    @JsonDeserialize(using = BigDecimalBRDeserializer::class)
    @JsonProperty("MdaTensaoConexao")
    val mdaTensaoConexao: BigDecimal,

    @JsonProperty("NomEmpresaConexao")
    @Column(length = 255)
    var nomEmpresaConexao: String,

    @JsonProperty("NumCnpjEmpresaConexao")
    @Column(length = 255)
    var numCnpjEmpresaConexao: String,

    @JsonProperty("DscViabilidade")
    @Column(length = 255)
    var dscViabilidade: String,

    @JsonProperty("DscSituacaoObra")
    @Column(length = 255)
    var dscSituacaoObra: String,
) {

    @PrePersist
    @PreUpdate
    fun truncateFields() {
        fun truncate(str: String, max: Int = 255): String = str.take(max)

        ideNucleoCEG = truncate(ideNucleoCEG)
        codCEG = truncate(codCEG)
        sigUFPrincipal = truncate(sigUFPrincipal)
        dscOrigemCombustivel = truncate(dscOrigemCombustivel)
        sigTipoGeracao = truncate(sigTipoGeracao)
        nomEmpreendimento = truncate(nomEmpreendimento)
        dscPropriRegimePariticipacao = truncate(dscPropriRegimePariticipacao)
        dscTipoConexao = truncate(dscTipoConexao)
        nomConexao = truncate(nomConexao)
        nomEmpresaConexao = truncate(nomEmpresaConexao)
        numCnpjEmpresaConexao = truncate(numCnpjEmpresaConexao)
        dscViabilidade = truncate(dscViabilidade)
        dscSituacaoObra = truncate(dscSituacaoObra)
    }
}
