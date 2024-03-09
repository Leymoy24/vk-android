package com.example.vkandroid.ui.theme.screens.product

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.vkandroid.ProductUIModel
import com.example.vkandroid.R
import com.example.vkandroid.ui.theme.Green

@Composable
fun ProductScreen(product: ProductUIModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Gray.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(30.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                SubcomposeAsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = product.thumbnail,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    loading = {
                        CircularProgressIndicator()
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .height(36.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = product.title,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Medium
                )
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(120.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.star_icon),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(40.dp)
                    )

                    Text(
                        text = product.rating.toString(),
                        modifier = Modifier.padding(horizontal = 8.dp),
                        fontSize = 30.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .height(30.dp)
                    .width(IntrinsicSize.Min)
                    .padding(horizontal = 16.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(12.dp),
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = product.brand,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .height(44.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "${product.price}$",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "${product.discountPercentage}% OFF",
                    fontSize = 20.sp,
                    color = Green,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.description,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                fontSize = 16.sp
            )

            Text(
                text = "Осталось: ${product.stock} шт.",
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

@Preview
@Composable
fun ProductScreenPreview() {
    val sampleProduct = ProductUIModel(
        id = 1,
        title = "iPhone 9",
        description = "An apple mobile which is nothing like apple",
        price = 549,
        discountPercentage = 12.96f,
        rating = 4.69f,
        stock = 94,
        brand = "Apple",
        category = "smartphones",
        thumbnail = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
        images = listOf(
            "https://cdn.dummyjson.com/product-images/1/1.jpg",
            "https://cdn.dummyjson.com/product-images/1/2.jpg",
            "https://cdn.dummyjson.com/product-images/1/3.jpg",
            "https://cdn.dummyjson.com/product-images/1/4.jpg",
            "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg"
        )
    )

    ProductScreen(product = sampleProduct)
}