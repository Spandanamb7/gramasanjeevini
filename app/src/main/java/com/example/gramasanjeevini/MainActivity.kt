package com.example.gramasanjeevini


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            var isLoggedIn by remember {
                mutableStateOf(false)
            }

            MaterialTheme {

                if (isLoggedIn) {

                    MedicineSearchApp()

                } else {

                    LoginScreen(
                        onLoginSuccess = {
                            isLoggedIn = true
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1565C0),
                        Color(0xFF42A5F5),
                        Color.White
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.medico_logo),
                contentDescription = "Medico Logo",

                modifier = Modifier
                    .size(180.dp),

                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Smart Medical Finder",
                color = Color.White,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(40.dp))

            Card(
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Column(
                    modifier = Modifier.padding(24.dp)
                ) {

                    Text(
                        text = "Login",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1565C0)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = email,

                        onValueChange = {
                            email = it
                        },

                        label = {
                            Text("Email")
                        },

                        leadingIcon = {
                            Icon(Icons.Default.Email, null)
                        },

                        modifier = Modifier.fillMaxWidth(),

                        shape = RoundedCornerShape(16.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = password,

                        onValueChange = {
                            password = it
                        },

                        label = {
                            Text("Password")
                        },

                        leadingIcon = {
                            Icon(Icons.Default.Lock, null)
                        },

                        modifier = Modifier.fillMaxWidth(),

                        shape = RoundedCornerShape(16.dp)
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Button(

                        onClick = {

                            Toast.makeText(
                                context,
                                "Login Successful",
                                Toast.LENGTH_LONG
                            ).show()

                            onLoginSuccess()
                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),

                        shape = RoundedCornerShape(18.dp),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1565C0)
                        )

                    ) {

                        Text(
                            text = "Login",
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MedicineSearchApp() {

    var query by remember {
        mutableStateOf("")
    }

    val medicines = listOf(

        "Paracetamol - Apollo Pharmacy - Bommanahalli, Bangalore - Stock: 24",
        "Dolo 650 - MedPlus - Hongasandra, Bangalore - Stock: 18",
        "Insulin - City Medical - Begur Road, Bangalore - Stock: 6",
        "Crocin - Ravi Medicals - Singasandra, Bangalore - Stock: 14",
        "Azithromycin - New Age Pharma - Kudlu Gate, Bangalore - Stock: 11",
        "Cetirizine - HealthCare Pharmacy - Garvebhavi Palya, Bangalore - Stock: 20",
        "Amoxicillin - Village Medicals - Madiwala, Bangalore - Stock: 8",
        "Ibuprofen - Wellness Pharma - BTM Layout, Bangalore - Stock: 13",
        "ORS - Jan Aushadhi - HSR Layout, Bangalore - Stock: 32",
        "Vitamin C - Green Pharmacy - Electronic City, Bangalore - Stock: 16",

        "Aspirin - Apollo Pharmacy - Koramangala, Bangalore - Stock: 19",
        "Calpol - MedLife Pharmacy - JP Nagar, Bangalore - Stock: 25",
        "Benadryl - Family Medicals - Bannerghatta Road, Bangalore - Stock: 9",
        "Pantoprazole - Urban Medicals - Silk Board, Bangalore - Stock: 12",
        "Metformin - Sri Medicals - Arekere, Bangalore - Stock: 22",
        "Digene - Care Pharmacy - Hulimavu, Bangalore - Stock: 17",
        "Electral - Wellness Medicals - Adugodi, Bangalore - Stock: 28",
        "Thyronorm - City Care Pharmacy - Dairy Circle, Bangalore - Stock: 7",
        "Zincovit - HealthyLife Pharma - Wilson Garden, Bangalore - Stock: 15",
        "Cipla Cold - Apollo Pharmacy - Jayanagar, Bangalore - Stock: 10",

        "Atorvastatin - MedPlus - Basavanagudi, Bangalore - Stock: 13",
        "Levocetirizine - New Care Pharmacy - Tavarekere, Bangalore - Stock: 18",
        "Omeprazole - Ravi Medicals - Suddaguntepalya, Bangalore - Stock: 21",
        "Rantac - HealthFirst Pharmacy - Madivala Market, Bangalore - Stock: 16",
        "Losartan - Family Care Pharma - Bommanahalli Main Road, Bangalore - Stock: 14",
        "Amlodipine - City Medicals - Hongasandra Main Road, Bangalore - Stock: 19",
        "Telma - Apollo Pharmacy - Kudlu, Bangalore - Stock: 12",
        "Augmentin - Village Pharmacy - Roopena Agrahara, Bangalore - Stock: 5",
        "Diclofenac - Wellness Pharma - Begur, Bangalore - Stock: 20",
        "Montek LC - CarePlus Pharmacy - Hosur Road, Bangalore - Stock: 11",

        "Loratadine - Green Medicals - NS Palya, Bangalore - Stock: 17",
        "Sinarest - Urban Pharma - Vijaya Bank Layout, Bangalore - Stock: 15",
        "Meftal Spas - Ravi Pharmacy - Mangammanapalya, Bangalore - Stock: 13",
        "Norflox TZ - HealthPlus Pharmacy - Devarachikkanahalli, Bangalore - Stock: 8",
        "Allegra - City Care Pharmacy - Akshaya Nagar, Bangalore - Stock: 18",
        "Becosules - Apollo Pharmacy - Arakere, Bangalore - Stock: 22",
        "Shelcal - MedLife Pharmacy - Hulimangala, Bangalore - Stock: 26",
        "Limcee - Jan Aushadhi - Konanakunte, Bangalore - Stock: 30",
        "Eno - Family Pharmacy - Gottigere, Bangalore - Stock: 24",
        "Gelusil - Healthy Medicals - Bilekahalli, Bangalore - Stock: 14",

        "Clavam - Wellness Pharmacy - Kalena Agrahara, Bangalore - Stock: 9",
        "Moxikind CV - City Pharmacy - Bommanahalli Signal, Bangalore - Stock: 12",
        "Cetzine - MedPlus - Hongasandra Cross, Bangalore - Stock: 16",
        "Ascoril - Family Pharmacy - Kudlu Main Road, Bangalore - Stock: 10",
        "TusQ - Ravi Medicals - Veer Sandra, Bangalore - Stock: 7",
        "Liv 52 - Apollo Pharmacy - Hosa Road, Bangalore - Stock: 19",
        "Neurobion Forte - Care Medicals - Chikka Begur, Bangalore - Stock: 18",
        "Disprin - Jan Aushadhi - Bommanahalli Circle, Bangalore - Stock: 21",
        "Volini Spray - Green Pharmacy - Hongasandra Layout, Bangalore - Stock: 6",
        "Pudin Hara - Village Medicals - Garvebhavi Palya Main Road, Bangalore - Stock: 25",

        "Azee 500 - Apollo Pharmacy - Mico Layout, Bangalore - Stock: 12",
        "Flexon - MedPlus - Bommanahalli Layout, Bangalore - Stock: 14",
        "Rablet - City Medicals - Kudlu Village, Bangalore - Stock: 11",
        "Asthalin - Ravi Medicals - Hongasandra Circle, Bangalore - Stock: 9",
        "Budecort - Green Pharmacy - AECS Layout, Bangalore - Stock: 17",
        "Febrex Plus - Wellness Pharma - Singasandra Main Road, Bangalore - Stock: 18",
        "Pan D - Care Pharmacy - Bommanahalli Market, Bangalore - Stock: 20",
        "Taxim O - Urban Medicals - Bommanahalli Bus Stop, Bangalore - Stock: 8",
        "Dulcoflex - Jan Aushadhi - Kudlu Gate Signal, Bangalore - Stock: 13",
        "Aciloc - Family Pharmacy - Hongasandra Main Layout, Bangalore - Stock: 16",

        "Looz Syrup - Apollo Pharmacy - Bommanahalli Lake Road, Bangalore - Stock: 15",
        "Celin - MedLife Pharmacy - Singasandra Metro, Bangalore - Stock: 21",
        "Meftal Forte - City Pharmacy - Veer Sandra Layout, Bangalore - Stock: 11",
        "Razo D - Wellness Pharma - Kudlu Cross, Bangalore - Stock: 10",
        "Doxycycline - Green Medicals - HSR Sector 1, Bangalore - Stock: 18",
        "Montair LC - Apollo Pharmacy - HSR Sector 2, Bangalore - Stock: 14",
        "Sinarest AF - MedPlus - HSR Sector 3, Bangalore - Stock: 13",
        "Livogen - Family Medicals - HSR Sector 4, Bangalore - Stock: 17",
        "Bandy Plus - Care Pharmacy - HSR Sector 5, Bangalore - Stock: 8",
        "Relispray - Urban Pharmacy - HSR Sector 6, Bangalore - Stock: 19",

        "Revital H - Apollo Pharmacy - Bommanahalli Extension, Bangalore - Stock: 22",
        "Supradyn - Green Pharmacy - Hongasandra Extension, Bangalore - Stock: 15",
        "Calciquick D3 - City Medicals - Kudlu Extension, Bangalore - Stock: 9",
        "M2 Tone - Family Pharmacy - Begur Main Road, Bangalore - Stock: 11",
        "Iron Folic Acid - Jan Aushadhi - Bommanahalli 1st Cross, Bangalore - Stock: 26",
        "Neurokind Plus - Wellness Pharma - Hongasandra 2nd Stage, Bangalore - Stock: 18",
        "Becosule Z - Care Medicals - Kudlu Village Road, Bangalore - Stock: 12",
        "Disprin Plus - Apollo Pharmacy - Silk Board Junction, Bangalore - Stock: 16",
        "Taxim AZ - MedPlus - Bommanahalli Ring Road, Bangalore - Stock: 20",
        "Omez - Urban Pharmacy - Hongasandra Ring Road, Bangalore - Stock: 14",

        "Rosuvas - Green Medicals - Kudlu Ring Road, Bangalore - Stock: 11",
        "Clopidogrel - Family Pharmacy - Begur Cross, Bangalore - Stock: 13",
        "Azicip 500 - Wellness Pharma - Bommanahalli 2nd Stage, Bangalore - Stock: 17",
        "Mucinac - City Pharmacy - Hongasandra 1st Main, Bangalore - Stock: 15",
        "Dytor - Apollo Pharmacy - Kudlu Main Layout, Bangalore - Stock: 10",
        "Ativan - MedLife Pharmacy - Bommanahalli Main Circle, Bangalore - Stock: 9",
        "Pyrigesic - Ravi Medicals - Hongasandra Bus Stop, Bangalore - Stock: 21",
        "Dolokind Plus - Family Pharmacy - Kudlu Signal, Bangalore - Stock: 19",
        "A to Z Tablets - Care Pharmacy - Begur Layout, Bangalore - Stock: 23",
        "Zerodol SP - Green Medicals - Bommanahalli Main Layout, Bangalore - Stock: 14",

        "Glycomet - Apollo Pharmacy - Hongasandra Main Layout, Bangalore - Stock: 12",
        "Moxclav - MedPlus - Kudlu 2nd Stage, Bangalore - Stock: 16",
        "Dabur Honitus - Urban Pharmacy - Bommanahalli East, Bangalore - Stock: 20",
        "Corex DX - Wellness Pharma - Hongasandra West, Bangalore - Stock: 11",
        "Neurobion - Family Medicals - Kudlu East, Bangalore - Stock: 17",
        "Shelcal HD - Green Pharmacy - Bommanahalli West, Bangalore - Stock: 8",
        "Pantocid - Care Pharmacy - Hongasandra North, Bangalore - Stock: 15",
        "Meftagesic - Apollo Pharmacy - Kudlu North, Bangalore - Stock: 10",
        "Cofsils - MedPlus - Bommanahalli South, Bangalore - Stock: 13",
        "Volini Gel - City Pharmacy - Hongasandra South, Bangalore - Stock: 18"
    )

    val filtered = medicines.filter {
        it.lowercase().contains(query.lowercase())
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1565C0),
                        Color(0xFF42A5F5),
                        Color.White
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "🏥 Grama-Sanjeevini",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "Find medicines in your village",
                color = Color.White,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(25.dp))

            Card(
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {

                OutlinedTextField(
                    value = query,

                    onValueChange = {
                        query = it
                    },

                    label = {
                        Text("Search Medicine")
                    },

                    leadingIcon = {
                        Icon(Icons.Default.Search, null)
                    },

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Available Medicines",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn {

                items(filtered) { medicine ->

                    MedicineCard(medicine)
                }
            }
        }
    }
}

@Composable
fun MedicineCard(
    medicine: String
) {

    val parts = medicine.split(" - ")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),

        shape = RoundedCornerShape(20.dp),

        elevation = CardDefaults.cardElevation(10.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = parts[0],
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1565C0)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "🏪 ${parts[1]}",
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "📦 ${parts[2]}",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2E7D32)
            )
        }
    }
}