package com.mkrdeveloper.foodappalpha.models

data class WinePairing(
    val pairedWines: List<String>,
    val pairingText: String,
    val productMatches: List<ProductMatche>
)