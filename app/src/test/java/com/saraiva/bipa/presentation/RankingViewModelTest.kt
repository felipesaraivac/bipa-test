package com.saraiva.bipa.presentation

import com.saraiva.bipa.CoroutinesRule
import com.saraiva.bipa.core.utils.State
import com.saraiva.bipa.data.RepositoryImpl
import com.saraiva.bipa.domain.entity.NodeEntity
import com.saraiva.bipa.ui.screens.rankings.RankingViewModel
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class RankingViewModelTest {

    @get:Rule
    val coroutineRule = CoroutinesRule()

    private val repository = mockk<RepositoryImpl>(relaxed = true)

    lateinit var viewModel: RankingViewModel

    @Test
    fun `GIVEN an valid result RETURN State success WHEN viewModel is created`() =
        runTest {
            // GIVEN
            val success = State.success(listOf(NodeEntity(
                alias = "alias",
                capacity = 1,
                channels = 1,
                firstSeen = 1,
                updatedAt = 1,
                city = emptyMap(),
                country = emptyMap(),
                publicKey = "publicKey"
            )))

            coEvery {
                repository.getNodes()
            } returns success.data
            // WHEN
            // THEN
            viewModel = RankingViewModel(repository)
            viewModel.nodes.value shouldBe State.Loading
            viewModel.getTopRankings().join()
            viewModel.nodes.value shouldBe success
        }

    @Test
    fun `GIVEN an exception result RETURN State fail WHEN viewModel is created`() =
        runTest {
            // GIVEN

            coEvery {
                repository.getNodes()
            } throws Exception("ERROR")
            // WHEN
            // THEN
            viewModel = RankingViewModel(repository)
            viewModel.nodes.value shouldBe State.Loading
            viewModel.getTopRankings().join()
            viewModel.nodes.value shouldBe State.failed("ERROR")
        }
}