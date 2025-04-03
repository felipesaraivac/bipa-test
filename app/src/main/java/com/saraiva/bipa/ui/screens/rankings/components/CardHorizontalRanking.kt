package com.saraiva.bipa.ui.screens.rankings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.saraiva.bipa.R
import com.saraiva.bipa.domain.entity.NodeEntity
import com.saraiva.bipa.ui.screens.util.toBtc
import com.saraiva.bipa.ui.screens.util.toUTC
import com.saraiva.bipa.ui.theme.gain
import com.saraiva.bipa.ui.theme.spacing
import java.util.Locale

@Composable
fun CardHorizontalRanking(
    modifier: Modifier = Modifier,
    node: NodeEntity,
    position: Int
) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier
                .fillMaxWidth(.9f)
                .fillMaxHeight(),
            shape = RoundedCornerShape(MaterialTheme.spacing.small),
            elevation = CardDefaults.cardElevation(
                defaultElevation = MaterialTheme.spacing.extraSmall
            ),
            border = CardDefaults.outlinedCardBorder(enabled = true)
        ) {

            ListItem(
                headlineContent = { Text(node.alias) },
                overlineContent = {
                    Text(
                        stringResource(
                            R.string.last_update,
                            node.updatedAt.toUTC()
                        )
                    )
                },
                trailingContent = {
                    Text(stringResource(R.string.btc_value, node.capacity.toBtc()), color = MaterialTheme.colorScheme.gain)
                },
                supportingContent = {
                    val country = node.country.run {
                        getOrDefault("pt-BR", get("en"))
                    }
                    val city = node.city.run {
                        getOrDefault("pt-BR", get("en"))
                    }
                    when {
                        country != null && city != null -> {
                            Text("$city, $country", style = MaterialTheme.typography.labelSmall)
                        }

                        city != null -> {
                            Text(city, style = MaterialTheme.typography.labelSmall)
                        }

                        country != null -> {
                            Text(country, style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
            )
        }
        Box(modifier = Modifier.height(MaterialTheme.spacing.small))
    }
}

@Composable
fun CharRounded(
    text: String,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .size(40.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape,
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleMedium
        )
    }
}