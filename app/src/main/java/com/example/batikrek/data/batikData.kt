package com.example.batikrek.data

import com.example.batikrek.R

data class Batik(
    val name: String,
    val motif: String,
    val suitableFor: String,
    val imageRes: Int
) {
    companion object {
        val batikData = listOf(
            Batik(
                name = "Batik Parang",
                motif = "Motif Parang Rusak",
                suitableFor = "Acara Formal, Pernikahan",
                imageRes = R.drawable.batik_parang
            ),
            Batik(
                name = "Batik Mega Mendung",
                motif = "Motif Mega Mendung",
                suitableFor = "Pesta, Acara Keluarga",
                imageRes = R.drawable.batik_mega_mendung
            ),
            Batik(
                name = "Batik Kawung",
                motif = "Motif Kawung",
                suitableFor = "Acara Formal, Casual",
                imageRes = R.drawable.batik_kawung
            )
        )
    }
}