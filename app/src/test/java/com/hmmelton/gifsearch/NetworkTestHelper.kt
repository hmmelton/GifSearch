package com.hmmelton.gifsearch

import com.hmmelton.gifsearch.models.FixedWidthImage
import com.hmmelton.gifsearch.models.Gif
import com.hmmelton.gifsearch.models.GifImage
import com.hmmelton.gifsearch.network.GifResponse
import com.hmmelton.gifsearch.network.GifService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkTestHelper {

    private val mockWebServer: MockWebServer = MockWebServer()

    val service: GifService

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("").toString())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        service = retrofit.create(GifService::class.java)
    }

    /**
     * This function sets the response body of the [MockWebServer].
     */
    fun setResponse(code: Int, body: String) {
        mockWebServer.enqueue(MockResponse().setResponseCode(code).setBody(body))
    }

    private val gifArray = """[
        {
            "type": "gif",
            "id": "sRFym5lssgcTFrMSac",
            "slug": "reaction-sRFym5lssgcTFrMSac",
            "url": "https://giphy.com/gifs/reaction-sRFym5lssgcTFrMSac",
            "bitly_gif_url": "https://gph.is/2RMEuxv",
            "bitly_url": "https://gph.is/2RMEuxv",
            "embed_url": "https://giphy.com/embed/sRFym5lssgcTFrMSac",
            "username": "",
            "source": "https://www.reddit.com/r/reactiongifs/comments/aoon0u/mrw_my_wife_takes_the_kids_out_of_town_to_get/",
            "rating": "g",
            "content_url": "",
            "source_tld": "www.reddit.com",
            "source_post_url": "https://www.reddit.com/r/reactiongifs/comments/aoon0u/mrw_my_wife_takes_the_kids_out_of_town_to_get/",
            "is_sticker": 0,
            "import_datetime": "2019-02-09 06:05:56",
            "trending_datetime": "2019-02-12 21:00:03",
            "images": {
                "fixed_height_still": {
                    "url": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/200_s.gif",
                    "width": "356",
                    "height": "200",
                    "size": "35581"
                },
                "original_still": {
                    "url": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/giphy_s.gif",
                    "width": "480",
                    "height": "270",
                    "size": "57661"
                },
                "fixed_width": {
                    "url": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/200w.gif",
                    "width": "200",
                    "height": "113",
                    "size": "219026",
                    "mp4": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/200w.mp4",
                    "mp4_size": "34986",
                    "webp": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/200w.webp",
                    "webp_size": "101670"
                },
                "fixed_height_small_still": {
                    "url": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/100_s.gif",
                    "width": "178",
                    "height": "100",
                    "size": "11727"
                },
                "fixed_height_downsampled": {
                    "url": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/200_d.gif",
                    "width": "356",
                    "height": "200",
                    "size": "189886",
                    "webp": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/200_d.webp",
                    "webp_size": "80884"
                },
                "preview": {
                    "width": "252",
                    "height": "142",
                    "mp4": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/giphy-preview.mp4",
                    "mp4_size": "27949"
                },
                "fixed_height_small": {
                    "url": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/100.gif",
                    "width": "178",
                    "height": "100",
                    "size": "197760",
                    "mp4": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/100.mp4",
                    "mp4_size": "30987",
                    "webp": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/100.webp",
                    "webp_size": "84726"
                },
                "downsized_still": {
                    "url": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/giphy-downsized_s.gif",
                    "width": "480",
                    "height": "270",
                    "size": "57661"
                },
                "downsized": {
                    "url": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/giphy.gif",
                    "width": "480",
                    "height": "270",
                    "size": "926173"
                },
                "downsized_large": {
                    "url": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/giphy.gif",
                    "width": "480",
                    "height": "270",
                    "size": "926173"
                },
                "fixed_width_small_still": {
                    "url": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/100w_s.gif",
                    "width": "100",
                    "height": "57",
                    "size": "5247"
                },
                "preview_webp": {
                    "url": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/giphy-preview.webp",
                    "width": "178",
                    "height": "100",
                    "size": "46618"
                },
                "fixed_width_still": {
                    "url": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/200w_s.gif",
                    "width": "200",
                    "height": "113",
                    "size": "13711"
                },
                "fixed_width_small": {
                    "url": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/100w.gif",
                    "width": "100",
                    "height": "57",
                    "size": "71712",
                    "mp4": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/100w.mp4",
                    "mp4_size": "12937",
                    "webp": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/100w.webp",
                    "webp_size": "37110"
                },
                "downsized_small": {
                    "width": "480",
                    "height": "270",
                    "mp4": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/giphy-downsized-small.mp4",
                    "mp4_size": "178261"
                },
                "fixed_width_downsampled": {
                    "url": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/200w_d.gif",
                    "width": "200",
                    "height": "113",
                    "size": "67186",
                    "webp": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/200w_d.webp",
                    "webp_size": "36302"
                },
                "downsized_medium": {
                    "url": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/giphy.gif",
                    "width": "480",
                    "height": "270",
                    "size": "926173"
                },
                "original": {
                    "url": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/giphy.gif",
                    "width": "480",
                    "height": "270",
                    "size": "926173",
                    "frames": "34",
                    "mp4": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/giphy.mp4",
                    "mp4_size": "124191",
                    "webp": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/giphy.webp",
                    "webp_size": "329990",
                    "hash": "e28a7ab011a1ef646a17d02d16a0841c"
                },
                "fixed_height": {
                    "url": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/200.gif",
                    "width": "356",
                    "height": "200",
                    "size": "658722",
                    "mp4": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/200.mp4",
                    "mp4_size": "81924",
                    "webp": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/200.webp",
                    "webp_size": "224148"
                },
                "hd": {
                    "width": "1920",
                    "height": "1080",
                    "mp4": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/giphy-hd.mp4",
                    "mp4_size": "1235570"
                },
                "looping": {
                    "mp4": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/giphy-loop.mp4",
                    "mp4_size": "2294387"
                },
                "original_mp4": {
                    "width": "480",
                    "height": "270",
                    "mp4": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/giphy.mp4",
                    "mp4_size": "124191"
                },
                "preview_gif": {
                    "url": "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/giphy-preview.gif",
                    "width": "133",
                    "height": "75",
                    "size": "47707"
                },
                "480w_still": {
                    "url": "https://media2.giphy.com/media/sRFym5lssgcTFrMSac/480w_s.jpg",
                    "width": "480",
                    "height": "270"
                }
            },
            "title": "excited homer simpson GIF",
            "_score": 0,
            "analytics": {
                "onload": {
                    "url": "https://giphy_analytics.giphy.com/simple_analytics?response_id=5c63b6ae696d657855fbcd8e&event_type=GIF_TRENDING&gif_id=sRFym5lssgcTFrMSac&action_type=SEEN"
                },
                "onclick": {
                    "url": "https://giphy_analytics.giphy.com/simple_analytics?response_id=5c63b6ae696d657855fbcd8e&event_type=GIF_TRENDING&gif_id=sRFym5lssgcTFrMSac&action_type=CLICK"
                },
                "onsent": {
                    "url": "https://giphy_analytics.giphy.com/simple_analytics?response_id=5c63b6ae696d657855fbcd8e&event_type=GIF_TRENDING&gif_id=sRFym5lssgcTFrMSac&action_type=SENT"
                }
            }
        },
        {
            "type": "gif",
            "id": "7IYE22rKh7dnt4QNBV",
            "slug": "reaction-7IYE22rKh7dnt4QNBV",
            "url": "https://giphy.com/gifs/reaction-7IYE22rKh7dnt4QNBV",
            "bitly_gif_url": "https://gph.is/g/BZknnPa",
            "bitly_url": "https://gph.is/g/BZknnPa",
            "embed_url": "https://giphy.com/embed/7IYE22rKh7dnt4QNBV",
            "username": "",
            "source": "https://www.reddit.com/r/reactiongifs/comments/apovyy/mrw_my_sister_tells_me_to_check_out_our_familys/",
            "rating": "g",
            "content_url": "",
            "source_tld": "www.reddit.com",
            "source_post_url": "https://www.reddit.com/r/reactiongifs/comments/apovyy/mrw_my_sister_tells_me_to_check_out_our_familys/",
            "is_sticker": 0,
            "import_datetime": "2019-02-12 06:06:36",
            "trending_datetime": "2019-02-13 06:00:02",
            "images": {
                "fixed_height_still": {
                    "url": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/200_s.gif",
                    "width": "366",
                    "height": "200",
                    "size": "53937"
                },
                "original_still": {
                    "url": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/giphy_s.gif",
                    "width": "498",
                    "height": "272",
                    "size": "80833"
                },
                "fixed_width": {
                    "url": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/200w.gif",
                    "width": "200",
                    "height": "109",
                    "size": "517017",
                    "mp4": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/200w.mp4",
                    "mp4_size": "53510",
                    "webp": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/200w.webp",
                    "webp_size": "140364"
                },
                "fixed_height_small_still": {
                    "url": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/100_s.gif",
                    "width": "183",
                    "height": "100",
                    "size": "15544"
                },
                "fixed_height_downsampled": {
                    "url": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/200_d.gif",
                    "width": "366",
                    "height": "200",
                    "size": "314961",
                    "webp": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/200_d.webp",
                    "webp_size": "59064"
                },
                "preview": {
                    "width": "369",
                    "height": "202",
                    "mp4": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/giphy-preview.mp4",
                    "mp4_size": "35268"
                },
                "fixed_height_small": {
                    "url": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/100.gif",
                    "width": "183",
                    "height": "100",
                    "size": "443842",
                    "mp4": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/100.mp4",
                    "mp4_size": "49688",
                    "webp": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/100.webp",
                    "webp_size": "126178"
                },
                "downsized_still": {
                    "url": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/giphy-downsized_s.gif",
                    "width": "398",
                    "height": "217",
                    "size": "63330"
                },
                "downsized": {
                    "url": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/giphy-downsized.gif",
                    "width": "398",
                    "height": "217",
                    "size": "1884664"
                },
                "downsized_large": {
                    "url": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/giphy.gif",
                    "width": "498",
                    "height": "272",
                    "size": "2388799"
                },
                "fixed_width_small_still": {
                    "url": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/100w_s.gif",
                    "width": "100",
                    "height": "55",
                    "size": "6099"
                },
                "preview_webp": {
                    "url": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/giphy-preview.webp",
                    "width": "198",
                    "height": "108",
                    "size": "44496"
                },
                "fixed_width_still": {
                    "url": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/200w_s.gif",
                    "width": "200",
                    "height": "109",
                    "size": "17831"
                },
                "fixed_width_small": {
                    "url": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/100w.gif",
                    "width": "100",
                    "height": "55",
                    "size": "162867",
                    "mp4": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/100w.mp4",
                    "mp4_size": "22341",
                    "webp": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/100w.webp",
                    "webp_size": "59662"
                },
                "downsized_small": {
                    "width": "307",
                    "height": "168",
                    "mp4": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/giphy-downsized-small.mp4",
                    "mp4_size": "56053"
                },
                "fixed_width_downsampled": {
                    "url": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/200w_d.gif",
                    "width": "200",
                    "height": "109",
                    "size": "98397",
                    "webp": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/200w_d.webp",
                    "webp_size": "27530"
                },
                "downsized_medium": {
                    "url": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/giphy.gif",
                    "width": "498",
                    "height": "272",
                    "size": "2388799"
                },
                "original": {
                    "url": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/giphy.gif",
                    "width": "498",
                    "height": "272",
                    "size": "2388799",
                    "frames": "31",
                    "mp4": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/giphy.mp4",
                    "mp4_size": "357939",
                    "webp": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/giphy.webp",
                    "webp_size": "540066",
                    "hash": "d2378695155c093c34f8ddf5dfeb71a6"
                },
                "fixed_height": {
                    "url": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/200.gif",
                    "width": "366",
                    "height": "200",
                    "size": "1597356",
                    "mp4": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/200.mp4",
                    "mp4_size": "137733",
                    "webp": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/200.webp",
                    "webp_size": "302026"
                },
                "looping": {
                    "mp4": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/giphy-loop.mp4",
                    "mp4_size": "1161703"
                },
                "original_mp4": {
                    "width": "480",
                    "height": "262",
                    "mp4": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/giphy.mp4",
                    "mp4_size": "357939"
                },
                "preview_gif": {
                    "url": "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/giphy-preview.gif",
                    "width": "95",
                    "height": "52",
                    "size": "48897"
                },
                "480w_still": {
                    "url": "https://media2.giphy.com/media/7IYE22rKh7dnt4QNBV/480w_s.jpg",
                    "width": "480",
                    "height": "262"
                }
            },
            "title": "inspecting jake gyllenhaal GIF",
            "_score": 0,
            "analytics": {
                "onload": {
                    "url": "https://giphy_analytics.giphy.com/simple_analytics?response_id=5c63b6ae696d657855fbcd8e&event_type=GIF_TRENDING&gif_id=7IYE22rKh7dnt4QNBV&action_type=SEEN"
                },
                "onclick": {
                    "url": "https://giphy_analytics.giphy.com/simple_analytics?response_id=5c63b6ae696d657855fbcd8e&event_type=GIF_TRENDING&gif_id=7IYE22rKh7dnt4QNBV&action_type=CLICK"
                },
                "onsent": {
                    "url": "https://giphy_analytics.giphy.com/simple_analytics?response_id=5c63b6ae696d657855fbcd8e&event_type=GIF_TRENDING&gif_id=7IYE22rKh7dnt4QNBV&action_type=SENT"
                }
            }
        }
    ]""".trimIndent()

    private val paginationJson = """{
        "total_count": 2,
        "count": 1,
        "offset": 0
    }""".trimMargin()

    private val metaJson = """{
        "status": 200,
        "msg": "OK",
        "response_id": "123abc456def"
    }""".trimMargin()

    val sampleResponseJson = """{
        "data": $gifArray,
        "pagination": $paginationJson,
        "meta": $metaJson
    }""".trimMargin()

    val sampleResponse = GifResponse(
        gifs = listOf(
            Gif(
                id = "sRFym5lssgcTFrMSac",
                gifImage = GifImage(fixedWidthImage = FixedWidthImage(url = "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/200w.gif")),
                gifUrl = "https://media0.giphy.com/media/sRFym5lssgcTFrMSac/200w.gif"
            ),
            Gif(
                id = "7IYE22rKh7dnt4QNBV",
                gifImage = GifImage(fixedWidthImage = FixedWidthImage(url = "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/200w.gif")),
                gifUrl = "https://media0.giphy.com/media/7IYE22rKh7dnt4QNBV/200w.gif"
            )
        )
    )
}