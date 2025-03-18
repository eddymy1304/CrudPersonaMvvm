package com.eddymy1304.crudpersonamvvm.feature.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eddymy1304.crudpersonamvvm.R
import com.eddymy1304.crudpersonamvvm.feature.detail.DetailAction.*
import com.eddymy1304.crudpersonamvvm.feature.detail.DetailEvent.*
import com.eddymy1304.crudpersonamvvm.ui.theme.CrudPersonaMvvmTheme

@Composable
fun DetailRoot(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    DetailScreen(
        modifier = modifier,
        state = state,
        onAction = viewModel::onAction
    )

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                NavigateToHome -> onNavigateToHome()
            }
        }
    }

}

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    state: DetailState,
    onAction: (DetailAction) -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box {

            Image(
                modifier = Modifier
                    .padding(16.dp)
                    .height(200.dp)
                    .width(120.dp),
                imageVector = Icons.Default.Person,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            IconButton(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopEnd),
                onClick = { onAction(OnChangeImage) }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.Green
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
                .fillMaxWidth()
        ) {

            TextField(
                modifier = Modifier
                    .widthIn(min = 240.dp),
                value = state.person.numberDocument,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.placeholder_number_document)
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            onAction(OnChangeNumberDocument(""))
                        }
                    )
                },
                singleLine = true,
                onValueChange = { onAction(OnChangeNumberDocument(it)) },
            )

            Button(
                onClick = { onAction(GetPersonByDni) },
                modifier = Modifier
                    .padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.Send,
                    contentDescription = null
                )
            }

        }
        TextField(
            placeholder = {
                Text(
                    text = stringResource(R.string.placeholder_name)
                )
            },
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
                .fillMaxWidth(),
            value = state.person.name,
            singleLine = true,
            onValueChange = { onAction(OnChangeName(it)) },
        )

        TextField(
            placeholder = {
                Text(
                    text = stringResource(R.string.placeholder_last_name)
                )
            },
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
                .fillMaxWidth(),
            value = state.person.lastName,
            singleLine = true,
            onValueChange = { onAction(OnChangeName(it)) },
        )

        TextField(
            placeholder = {
                Text(
                    text = stringResource(R.string.placeholder_age)
                )
            },
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
                .fillMaxWidth(),
            value = if (state.person.age == 0) "" else state.person.age.toString(),
            singleLine = true,
            onValueChange = { onAction(OnChangeAge(it)) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
        )

        Button(
            modifier = Modifier.padding(16.dp),
            onClick = { onAction(SavePerson) }
        ) {
            Text(stringResource(R.string.btn_save))
        }

    }

}

@Preview(device = "id:API 26")
@Composable
private fun Preview() {
    CrudPersonaMvvmTheme {
        DetailScreen(
            state = DetailState(),
            onAction = {}
        )
    }
}