package com.example.unitravel

data class Destino(
    var id: String = "",
    val nome: String = "",
    val categoria: String = "",
    val localizacao: String = "",
    val horario: String = "",
    val custo: String = "",
    val contato: String = "",
    val cidade: String = "",
    val estado: String = "",
    val tipoCozinha: String = "Não informado",
    val avaliacao: String = "Não avaliado",
    val servicos: String = "Não informado"
)

