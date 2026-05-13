package com.example.gramasanjeevini

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalPharmacy
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                MedicineSearchApp()
            }
        }
    }
}

data class Medicine(
    val name: String = "",
    val shop: String = "",
    val stock: Int = 0
)

@Composable
fun MedicineSearchApp() {

    var query by remember { mutableStateOf("") }

    val medicineList = remember { mutableStateListOf<Medicine>() }

    val db = FirebaseFirestore.getInstance()

    // Fetch data from Firebase
    LaunchedEffect(Unit) {

        db.collection("medicines")
            .get()
            .addOnSuccessListener { result ->

                medicineList.clear()

                for (document in result) {

                    val medicine = Medicine(
                        name = document.getString("name") ?: "",
                        shop = document.getString("shop") ?: "",
                        stock = document.getLong("stock")?.toInt() ?: 0
                    )

                    medicineList.add(medicine)
                }
            }
    }

    // Local sample data if Firebase empty
    if (medicineList.isEmpty()) {

        medicineList.addAll(
            listOf(
                Medicine("Paracetamol", "Ravi Medical", 20),
                Medicine("Dolo 650", "HealthPlus Pharmacy", 15),
                Medicine("Insulin", "City Medical", 5),
                Medicine("Amoxicillin", "Apollo Pharmacy", 12),
                Medicine("Cetirizine", "Care Medicals", 18),
                Medicine("Azithromycin", "Sri Clinic Pharmacy", 7),
                Medicine("Metformin", "Wellness Pharma", 25),
                Medicine("ORS", "Jan Aushadhi", 30),
                Medicine("Crocin", "MedPlus", 22),
                Medicine("Pantoprazole", "LifeCare Pharmacy", 9),
                Medicine("Ibuprofen", "Family Medical", 11),
                Medicine("Vitamin C", "Green Pharmacy", 17),
                Medicine("Aspirin", "Trust Medicals", 14),
                Medicine("Calpol", "People Pharmacy", 8),
                Medicine("Benadryl", "Village Medical", 6),
                Medicine("Cipla Cold", "Healthy Life Pharmacy", 13),
                Medicine("Digene", "Shree Medicals", 16),
                Medicine("Electral", "Care Plus", 21),
                Medicine("Zincovit", "Apollo Pharmacy", 19),
                Medicine("Thyronorm", "City Care Pharmacy", 4)
            )
        )
    }

    val filteredMedicines = medicineList.filter {
        it.name.lowercase().contains(query.lowercase())
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFE3F2FD),
                        Color(0xFFFFFFFF)
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            // App Title
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.LocalPharmacy,
                    contentDescription = "Medicine",
                    tint = Color(0xFF1565C0),
                    modifier = Modifier.size(40.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Grama-Sanjeevini",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0D47A1)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Search Bar
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                label = { Text("Search Medicine") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Available Medicines",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Medicine List
            LazyColumn {

                items(filteredMedicines) { medicine ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = medicine.name,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1565C0)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Pharmacy: ${medicine.shop}",
                                fontSize = 16.sp
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = "Stock Available: ${medicine.stock}",
                                fontSize = 16.sp
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            if (medicine.stock > 10) {

                                Text(
                                    text = "Available",
                                    color = Color(0xFF2E7D32),
                                    fontWeight = FontWeight.Bold
                                )

                            } else {

                                Text(
                                    text = "Low Stock",
                                    color = Color.Red,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}