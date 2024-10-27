package com.paulina.latestkpopnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paulina.latestkpopnews.data.Datasource
import com.paulina.latestkpopnews.model.LatestKpopNews
import com.paulina.latestkpopnews.model.KpopGroup
import com.paulina.latestkpopnews.ui.theme.LatestkpopnewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LatestkpopnewsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val tabs = listOf("K-Pop News", "K-Pop Groups")
    var selectedTabIndex by remember { mutableStateOf(0) } // Menggunakan mutableStateOf untuk state yang dapat diubah

    Column {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index }
                )
            }
        }
        when (selectedTabIndex) {
            0 -> LatestKpopNewsApp()
            1 -> KpopGroupsApp()
        }
    }
}

@Composable
fun LatestKpopNewsApp() {
    val latestKpopNewsList = Datasource().loadLatestKpopNews()
    LatestKpopNewsList(latestKpopNewsList = latestKpopNewsList)
}

@Composable
fun LatestKpopNewsList(latestKpopNewsList: List<LatestKpopNews>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(latestKpopNewsList) { news ->
            LatestKpopNewsCard(
                news = news,
                modifier = Modifier.padding(8.dp),
                onClick = { /* Handle click here */ }
            )
        }
    }
}

@Composable
fun LatestKpopNewsCard(news: LatestKpopNews, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Column {
            Image(
                painter = painterResource(news.imageResourceId),
                contentDescription = stringResource(news.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = LocalContext.current.getString(news.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Composable
fun KpopGroupsApp() {
    val kpopGroups = Datasource().loadKpopGroups()

    LazyColumn {
        items(kpopGroups) { group ->
            KpopGroupItem(group = group)
        }
    }
}

@Composable
fun KpopGroupItem(
    group: KpopGroup,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_small))
    ) {
        Image(
            painter = painterResource(group.imageResourceId),
            contentDescription = stringResource(group.nameResourceId),
            modifier = Modifier
                .size(64.dp)
                .padding(end = 8.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = LocalContext.current.getString(group.nameResourceId),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.alignByBaseline()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LatestkpopnewsTheme {
        MainScreen()
    }
}