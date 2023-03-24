package com.francis.custommultiselect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.francis.custommultiselect.ui.theme.CustomMultiselectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomMultiselectTheme {
                var items by remember {
                    mutableStateOf(
                        (1..20).map{
                            ListItem(
                                title = "Item $it",
                                isSelected = false
                            )
                        }
                    )
                }
                // get the selected items in the screen
                items.filter { it.isSelected }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    items(items.size) { index ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit){
                                    detectTapGestures(
                                        onLongPress = {
                                            items = items.mapIndexed { j, item ->
                                                if (index == j ) {
                                                    item.copy(isSelected = !item.isSelected)
                                                }else item
                                            }
                                        }
                                    )
                                }
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = items[index].title)
                            if(items[index].isSelected) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Selected",
                                    tint = Color.Green,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}