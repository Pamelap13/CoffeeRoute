package com.example.coffeeroute

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder

@Composable
fun CoffeeListScreen(
    navController: NavController,
    itemCoffeeViewModel: ItemCoffeeViewModel

) {

    val itemsCoffee by itemCoffeeViewModel.itemsCoffee.collectAsState()
    itemCoffeeViewModel.fecthItemsCoffee()
    LazyColumn(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(itemsCoffee) { cafeteria ->
            ItemCoffee(
                cafeteria = cafeteria,
                navController = navController
            )
        }
    }

}

@Composable
fun ItemCoffee(
    modifier: Modifier = Modifier, cafeteria: ItemCoffee, navController: NavController
) {
    val jsonItemCoffee = Json.encodeToString(cafeteria)
    val encodedItemCoffee = URLEncoder.encode(jsonItemCoffee, "UTF-8")
    Surface(
        modifier = Modifier.clickable {
            navController.navigate("coffeedetail/$encodedItemCoffee")
            Log.i("click", cafeteria.id.toString())
        },
        shape = MaterialTheme.shapes.medium,
        color = Color.DarkGray
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(0.3f)
                    .fillMaxHeight()
            ) {
                Image(
                    painterResource(R.drawable.tacita), contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop,

                    )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.7f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = cafeteria.name,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp),
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = cafeteria.address,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp),
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = cafeteria.instagram,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

    }
}

@Composable
fun CoffeeDetail(cafeteria: ItemCoffee) {


    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {


        Box(
            modifier = Modifier

                .fillMaxWidth()
                .height(400.dp)
        ) {
            Image(
                painterResource(R.drawable.klipartz), contentDescription = "",
                modifier = Modifier.fillMaxSize()
            )
            Image(
                painterResource(R.drawable.esencia_magica),
                contentDescription = "",
                modifier = Modifier
                    .size(200.dp)
                    //.aspectRatio(1f)
                    .clip(CircleShape)
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop
            )
            Text(
                text = cafeteria.name,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(20.dp),
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color(0xFF736e6c)

            )
        }
        ElevatedCard(
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = Color(0xFFF4cd5b),
                contentColor = Color.Blue,
            )
        ) {

            Text(
                text = "Somos Esencia Magica, una cafeteria de especialidad con uno de los mejores cafes de Peru, de Villa Rica" +
                        " y exquisitas salteñas. Negocio familiar que ofrece tradicion y calidad",
                textAlign = TextAlign.Justify,
                fontFamily = FontFamily.SansSerif,
                color = Color(0xFF736e6c),
                modifier = Modifier.padding(10.dp)
            )
            Text(
                text = cafeteria.instagram,
                textAlign = TextAlign.Justify,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(top = 30.dp),
                color = Color(0xFF736e6c)
            )
            Text(
                text = "Lunes a Sabado 9:00 am a 1:00 pm",
                textAlign = TextAlign.Justify,
                fontFamily = FontFamily.SansSerif,
                color = Color(0xFF736e6c),
                modifier = Modifier
                    .paddingFromBaseline(20.dp)
                    .padding(horizontal = 10.dp)
            )
            Text(
                text = "+51999999999",
                textAlign = TextAlign.Justify,
                fontFamily = FontFamily.SansSerif,
                color = Color(0xFF736e6c),
                modifier = Modifier
                    .paddingFromBaseline(20.dp)
                    .padding(horizontal = 10.dp)
            )
            Text(
                text = cafeteria.address,
                textAlign = TextAlign.Justify,
                fontFamily = FontFamily.SansSerif,
                color = Color(0xFF736e6c),
                modifier = Modifier
                    .paddingFromBaseline(30.dp)
                    .align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Medium
            )
            ElevatedButton(
                elevation = ButtonDefaults.buttonElevation(8.dp),
                onClick = { SeeLocation(context) },
                modifier = Modifier
                    .width(250.dp)
                    .align(Alignment.CenterHorizontally)
                    .paddingFromBaseline(60.dp),
                colors = ButtonDefaults.elevatedButtonColors(contentColor = Color.White)

            ) {
                Text(text = "Ver ubicacion")
            }
            //}
        }

    }
}


@Composable
fun MyApp() {
    val itemCoffeeViewModel: ItemCoffeeViewModel = viewModel(
    factory = ItemCoffeeViewModelFactory(
        CoffeeRepository()
    )
    )

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "coffeeroute") {
        composable("coffeeroute") {
            CoffeeRoute(onStartClicked = { navController.navigate("coffelistscreen") })
        }
        composable("coffelistscreen") {
            CoffeeListScreen(navController,itemCoffeeViewModel)
        }
        composable("coffeedetail/{itemCoffee}",
            arguments = listOf(navArgument("itemCoffee") { type = NavType.StringType })
        ) { backStackEntry ->

            val itemCoffee = itemCoffeeViewModel.decodeJson( backStackEntry.arguments?.getString("itemCoffee"))


            itemCoffee?.let { coffee ->
                CoffeeDetail(coffee)
            }
        }
    }

}



@Preview
@Composable
fun CoffeeRoutepreview() {

    CoffeeRoute(onStartClicked = {})
}

@Composable
fun CoffeeRoute(modifier: Modifier = Modifier, onStartClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4cd5b)),
        contentAlignment = Alignment.BottomCenter
    ) {

        Image(
            painterResource(R.drawable.pngwingco),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            alpha = 0.3F,
            contentScale = ContentScale.Crop
        )
        Image(
            painterResource(R.drawable.klipartz),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 100.dp),
            alignment = Alignment.TopCenter
        )
        Button(
            onClick = onStartClicked,
            modifier = Modifier
                .padding(vertical = 40.dp)
                .width(300.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White, contentColor = Color(0xFF362902)
            )
        ) {
            Text(
                text = "START",
                fontFamily = FontFamily.SansSerif,
                letterSpacing = 7.sp,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 25.sp,
                style = TextStyle(textDecoration = TextDecoration.None)
            )
        }
    }
}


fun SeeLocation(context: Context) {

    val latitud = -12.139427723377743
    val longitud = -77.02251976778626
    val label = "Ubicación"
    val zoomLevel = 10
    val uri = "geo:$latitud,$longitud?q=$latitud,$longitud($label)&z=$zoomLevel"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
    intent.setPackage("com.google.android.apps.maps")
    context.startActivity(intent)
}