package com.rujirakongsomran.parallaxeffect

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.view.WindowCompat
import com.rujirakongsomran.parallaxeffect.ui.theme.ParallaxEffectTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
            ParallaxEffectTheme {
                // A surface container using the 'background' color from the theme
                ImageParallaxScroll()
            }
        }
    }
}

val robotoFamily = FontFamily(
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold)
)

@Composable
fun CreateParallax() {
    ConstraintLayout {
        val (image, text1, text2) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.hulk),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(image) {
                    top.linkTo(parent.top)
                },
            contentScale = ContentScale.Crop
        )

        Text(
            text = "Hulk",
            fontSize = 22.sp,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .constrainAs(text1) {
                    top.linkTo(image.bottom, margin = 8.dp)
                }
                .padding(horizontal = 10.dp)

        )
        Text(
            text = stringResource(id = R.string.content_paragraph_1),
            fontSize = 18.sp,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Light,
            lineHeight = 2.em,
            modifier = Modifier
                .constrainAs(text2) {
                    top.linkTo(text1.bottom, margin = 8.dp)
                }
                .padding(horizontal = 10.dp)
        )
    }
}

@Composable
fun ImageParallaxScroll() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .graphicsLayer {
                    alpha = 1f - ((scrollState.value.toFloat() / scrollState.maxValue) * 1.5f)
                    translationY = 0.5f * scrollState.value
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.hulk),
                contentDescription = "Image Parallax",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            text = "Hulk",
            fontSize = 24.sp,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(all = 16.dp)

        )
        Text(
            text = stringResource(id = R.string.content_paragraph_1),
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Light,
            lineHeight = 1.2.em,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
        )

        Text(
            text = stringResource(id = R.string.content_paragraph_2),
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Light,
            lineHeight = 1.2.em,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(all = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ParallaxEffectTheme {
        ImageParallaxScroll()
    }
}