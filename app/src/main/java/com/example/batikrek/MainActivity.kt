@file:OptIn(ExperimentalFoundationApi::class)
package com.example.batikrek

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.batikrek.ui.theme.BatikrekTheme
import com.example.batikrek.data.Batik
import com.example.batikrek.data.Batik.Companion.batikData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BatikrekTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var selectedEvent by remember { mutableStateOf("") }
    var recommendation by remember { mutableStateOf("") }
    val events = listOf("Pernikahan", "Acara Formal", "Pesta", "Casual", "Acara Keluarga")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Sistem Pendukung Keputusan Pakaian Batik",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Temukan pakaian batik terbaik untuk acara yang akan Anda hadiri.",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Menggunakan batikData dengan d kecil
        BatikCatalog(batikData)

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Pilih Jenis Acara", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))

        var expanded by remember { mutableStateOf(false) }

        Box {
            OutlinedButton(onClick = { expanded = !expanded }) {
                Text(text = if (selectedEvent.isNotEmpty()) selectedEvent else "Pilih Acara")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                events.forEach { event ->
                    DropdownMenuItem(
                        text = { Text(text = event) },
                        onClick = {
                            selectedEvent = event
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                recommendation = getRecommendationForEvent(selectedEvent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Dapatkan Rekomendasi")
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (recommendation.isNotEmpty()) {
            Text(
                text = "Rekomendasi Pakaian Batik untuk $selectedEvent:",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = recommendation, fontSize = 16.sp)
        }
    }
}

@Composable
fun BatikCatalog(batikList: List<Batik>) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Katalog Batik",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(batikList) { batik ->
                BatikItem(batik = batik)
            }
        }
    }
}

@Composable
fun BatikItem(batik: Batik) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = batik.imageRes),
                contentDescription = batik.name,
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 16.dp)
            )
            Column {
                Text(
                    text = batik.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = batik.motif,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = "Cocok untuk: ${batik.suitableFor}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    color = Color(0xFF8B4513)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { /* Tambahkan aksi untuk melihat detail */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF8B4513)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Lihat Detail", fontSize = 12.sp)
                }
            }
        }
    }
}

// Implementasi fungsi rekomendasi
fun getRecommendationForEvent(event: String): String {
    return when (event) {
        "Pernikahan" -> "Untuk acara pernikahan, kami merekomendasikan batik dengan motif klasik seperti Sidoasih atau Sidomukti yang melambangkan kebahagiaan dan kemakmuran."
        "Acara Formal" -> "Untuk acara formal, batik dengan motif geometris atau Kawung sangat cocok. Pilih warna yang tidak terlalu mencolok."
        "Pesta" -> "Untuk pesta, Anda bisa memilih batik dengan motif modern dan warna-warna cerah. Motif Mega Mendung atau Parang bisa menjadi pilihan yang bagus."
        "Casual" -> "Untuk acara casual, batik kontemporer dengan motif sederhana dan warna-warna natural sangat cocok. Motif Truntum atau Sekar Jagad bisa menjadi pilihan."
        "Acara Keluarga" -> "Untuk acara keluarga, batik dengan motif tradisional seperti Sogan atau Ceplok sangat pas. Pilih warna yang hangat dan nyaman."
        else -> "Silakan pilih jenis acara terlebih dahulu untuk mendapatkan rekomendasi."
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun PreviewMainActivity() {
    BatikrekTheme {
        MainScreen()
    }
}
