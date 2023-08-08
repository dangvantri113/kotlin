package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpace()
                }
            }
        }
    }
}

@Composable
fun ArtSpace(modifier: Modifier = Modifier) {
    val imageIds = arrayOf(
        R.drawable.art1,
        R.drawable.art2,
        R.drawable.art3
    )
    val descriptionIds = arrayOf(
        R.string.description_1,
        R.string.description_2,
        R.string.description_3
    )
    var index by remember { mutableStateOf(0) }

    val max = 2;
    val imageId = imageIds[index]
    val descriptionId = descriptionIds[index]

    Column(
        Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtWall(imageId)
        ArtDescriptor(descriptionId)
        ArtNavigator(moveToPrevious = {
            if (index > 0) {
                index--
            } else {
                index = max
            }
        }, moveToNext = {
            if (index < max) {
                index++
            } else {
                index = 0
            }
        })
    }
}

@Composable
fun ArtWall(@DrawableRes imageId: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = imageId),
        contentDescription = null,
        modifier = Modifier.size(300.dp, 400.dp)
    )
}

@Composable
fun ArtDescriptor(@StringRes descriptionId: Int, modifier: Modifier = Modifier) {
    Text(text = stringResource(id = descriptionId), modifier = Modifier.padding(16.dp))
}

@Composable
fun ArtNavigator(
    moveToPrevious: () -> Unit,
    moveToNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(horizontalArrangement = Arrangement.Center) {
        Button(onClick = moveToPrevious, modifier = Modifier.width(120.dp)) {
            Text(text = "Previous")
        }

        Spacer(modifier = Modifier.width(20.dp))

        Button(onClick = moveToNext, modifier = Modifier.width(120.dp)) {
            Text(text = "Next")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpace()
    }
}