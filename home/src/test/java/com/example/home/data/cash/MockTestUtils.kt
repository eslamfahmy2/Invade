package com.example.home.data.cash


import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONObject


class MockTestUtil {



    companion object {


        fun getCurrenciesMockResponse(): Response {

            val jsonResponse = JSONObject(
                "{[{\n" +
                        "        \"alpha_two_code\": \"EG\",\n" +
                        "        \"name\": \"Arab Academy for Science & Technology\",\n" +
                        "        \"country\": \"Egypt\",\n" +
                        "        \"domains\": [\"aast.edu\"],\n" +
                        "        \"web_pages\": [\"http://www.aast.edu/\"],\n" +
                        "        \"state-province\": null\n" +
                        "    }, {\n" +
                        "        \"alpha_two_code\": \"EG\",\n" +
                        "        \"name\": \"Akhbar El Yom Academy\",\n" +
                        "        \"country\": \"Egypt\",\n" +
                        "        \"domains\": [\"akhbaracademy.edu.eg\"],\n" +
                        "        \"web_pages\": [\"http://www.akhbaracademy.edu.eg/\"],\n" +
                        "        \"state-province\": null\n" +
                        "    }\n" +
                        "]"
            ).toString()


            val responseBody =
                ResponseBody.create(
                    "application/json".toMediaTypeOrNull(),
                    jsonResponse
                )

            val request = Request.Builder()
                .url("http://localhost:8080/")
                .build()

            return Response
                .Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(responseBody)
                .build()
        }

        fun getCurrencyRatesMockResponse(): Response {
            val jsonResponse = JSONObject(
                "[{\n" +
                        "        \"alpha_two_code\": \"EG\",\n" +
                        "        \"name\": \"Arab Academy for Science & Technology\",\n" +
                        "        \"country\": \"Egypt\",\n" +
                        "        \"domains\": [\"aast.edu\"],\n" +
                        "        \"web_pages\": [\"http://www.aast.edu/\"],\n" +
                        "        \"state-province\": null\n" +
                        "    }, {\n" +
                        "        \"alpha_two_code\": \"EG\",\n" +
                        "        \"name\": \"Akhbar El Yom Academy\",\n" +
                        "        \"country\": \"Egypt\",\n" +
                        "        \"domains\": [\"akhbaracademy.edu.eg\"],\n" +
                        "        \"web_pages\": [\"http://www.akhbaracademy.edu.eg/\"],\n" +
                        "        \"state-province\": null\n" +
                        "    }\n" +
                        "]"
            ).toString()
            val final = JSONObject("{ \"rates\": ${jsonResponse}}").toString()
            val responseBody =
                ResponseBody.create(
                    "application/json".toMediaTypeOrNull(),
                    final
                )

            val request = Request.Builder()
                .url("http://localhost:8080/")
                .build()

            return Response
                .Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(responseBody)
                .build()
        }
    }


}

class MockInterceptor : Interceptor {

    private val responseMap: MutableMap<String, Response> = mutableMapOf()

    fun mockResponse(tag: String, response: Response) {
        responseMap[tag] = response
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()
        responseMap.forEach { item ->
            if (url.contains(item.key)) {
                return item.value
            }
        }
        throw IllegalArgumentException("No mocked response found for $url")
    }
}


