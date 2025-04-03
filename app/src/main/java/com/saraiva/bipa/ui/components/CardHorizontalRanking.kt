package com.saraiva.bipa.ui.components

import android.icu.text.DecimalFormat
import android.icu.text.NumberFormat
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
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.saraiva.bipa.R
import com.saraiva.bipa.core.Constants
import com.saraiva.bipa.domain.entity.NodeEntity
import com.saraiva.bipa.ui.theme.spacing

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
            CardDefaults.outlinedCardColors(),
        ) {

            ListItem(
                headlineContent = { Text(node.alias) },
                overlineContent = { Text(node.capacity.toString()) },
            trailingContent = {
                Text(stringResource(R.string.btc_value, node.capacity.toBtc()))
            },
                leadingContent = {
                    CharRounded(text = position.toString())
                },
            )
        }
        Box(modifier = Modifier.height(MaterialTheme.spacing.small)) {

        }
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
//
//@Preview
//@Composable
//private fun CardHorizontalStockPreview() {
//    TfcTheme {
//        CardHorizontalRanking(user = FakeDataSource.user, position = 1)
//    }
//}



fun Long.toBtc(): String {
    val satsToBtc = toBigDecimal().divide(Constants.BTC_SATS.toBigDecimal())

    if (satsToBtc.compareTo(1.toBigDecimal()) > 0)
        return DecimalFormat("0.00").format(satsToBtc)

    return DecimalFormat("0.00000000").format(satsToBtc)
}