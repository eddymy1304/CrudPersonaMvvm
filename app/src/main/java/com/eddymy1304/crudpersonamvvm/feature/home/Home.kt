package com.eddymy1304.crudpersonamvvm.feature.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eddymy1304.crudpersonamvvm.domain.model.PersonModel
import com.eddymy1304.crudpersonamvvm.domain.model.TypeDocument
import com.eddymy1304.crudpersonamvvm.feature.home.HomeAction.*
import com.eddymy1304.crudpersonamvvm.ui.theme.CrudPersonaMvvmTheme

@Composable
fun HomeRoot(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToDetail: (numberDocument: String) -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        modifier = modifier,
        uiState = uiState,
        onAction = viewModel::onAction
    )

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is HomeEvent.NavigateToDetail -> onNavigateToDetail(uiEvent.person.numberDocument)
            }
        }
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeState,
    onAction: (HomeAction) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(8.dp)
        ) {

            items(uiState.persons, key = { it.numberDocument }) { item ->
                PersonItem(
                    person = item,
                    onClickDelete = { onAction(DeletePerson(item)) },
                    onClickItem = { onAction(ViewPerson(item)) }
                )
            }

        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            onClick = { onAction(AddPerson) }
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }

        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
fun PersonItem(
    modifier: Modifier = Modifier,
    person: PersonModel,
    onClickDelete: () -> Unit,
    onClickItem: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = modifier
                .clickable(onClick = onClickItem),
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = 8.dp),
                text = person.name,
                maxLines = 1,
                fontSize = 16.sp
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                text = person.lastName,
                maxLines = 1,
                fontSize = 16.sp
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 8.dp),
                text = person.numberDocument,
                maxLines = 1,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.primary
            )

            HorizontalDivider()
        }

        IconButton(
            modifier = Modifier
                .align(Alignment.BottomEnd),
            onClick = onClickDelete
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
        }
    }

}

@Preview
@Composable
fun PersonItemPreview() {
    CrudPersonaMvvmTheme {
        PersonItem(
            person = PersonModel(
                numberDocument = "12345678",
                name = "Eddy David",
                lastName = "Mendoza Yamunaque",
                age = 20,
                typeDocument = TypeDocument.DNI
            ),
            onClickDelete = {},
            onClickItem = {}
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    CrudPersonaMvvmTheme {
        HomeScreen(
            uiState = HomeState(
                persons = listOf(
                    PersonModel(
                        numberDocument = "12345678",
                        name = "Eddy David",
                        lastName = "Mendoza Yamunaque",
                        age = 20,
                        typeDocument = TypeDocument.DNI
                    )
                )
            ),
            onAction = {}
        )
    }
}