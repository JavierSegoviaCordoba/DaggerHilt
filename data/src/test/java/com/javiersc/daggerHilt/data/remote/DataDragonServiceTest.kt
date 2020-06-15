package com.javiersc.daggerHilt.data.remote

import com.javiersc.daggerHilt.data.remote.extensions.mockResponseWith
import com.javiersc.daggerHilt.data.remote.extensions.readResource
import com.javiersc.daggerHilt.data.remote.models.ChampionsDTO
import com.javiersc.daggerHilt.data.remote.services.DataDragonService
import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.StatusCode
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test
import retrofit2.create

class DataDragonServiceTest {

    private val mockWebServer = MockWebServer()
    private val service: DataDragonService = DataDragonServiceFactory.buildRetrofit(mockWebServer.url("/")).create()

    @Test
    fun `get champions success`() = runBlocking {
        mockWebServer.enqueue("champions_200.json" mockResponseWith StatusCode.OK_200)

        val actual = service.getChampions("10.10.1", "en_US") as NetworkResponse.Success
        val expected = Json.parse(ChampionsDTO.serializer(), readResource("champions_200.json"))

        actual.data shouldBe expected
    }

    @After
    fun `close mockWebServer`() {
        mockWebServer.shutdown()
    }
}
