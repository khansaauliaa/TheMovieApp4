package com.khansafzh.themovieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpClient.log
import com.loopj.android.http.AsyncHttpResponseHandler
import androidx.lifecycle.ViewModel
import com.khansafzh.themovieapp.model.Model
import okhttp3.internal.http2.Header
import org.json.JSONObject
import retrofit2.http.Headers
import java.lang.Exception

class Content : ViewModel() {
    companion object {
        private const val API_KEY = "cae17553f337184ece174b9b703a0745"
    }

    private val listContent = MutableLiveData<ArrayList<Model>>()

    internal fun setCatalogueContentMovie(category: String = "movie") {
        val client = AsyncHttpClient()
        val listItem = ArrayList<Model>()
        val BASE_URL = "https://api.themoviedb.org/3/movie/550?api_key=cae17553f337184ece174b9b703a0745"

        client.get(BASE_URL, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out cz.msebera.android.httpclient.Header>,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val list = responseObject.getJSONArray("results")

                    for (i in 0 until list.length()) {
                        val movies = list.getJSONObject(i)
                        val moviesItem = Model()
                        moviesItem.id = movies.getInt("id")
                        if (movies.has("title")) {
                            moviesItem.title = movies.getString("title")
                        } else {
                            moviesItem.title = movies.getString("name")
                        }
                        moviesItem.synopsis = movies.getString("overview")
                        if (movies.has("first_launching")) {
                            moviesItem.date = movies.getString("first_launching")
                        } else {
                            moviesItem.date = movies.getString("release_date")
                        }
                        if (movies.has("banner")){
                            moviesItem.poster = movies.getString("main_banner")
                        } else {
                            moviesItem.thumb = movies.getString("poster_path")
                            listItem.add(moviesItem)
                        }
                    }
                    listContent.postValue(listItem)
                } catch (e: Exception) {
                    print(msg = e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out cz.msebera.android.httpclient.Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                print(msg = error.message.toString())
            }
        })
    }

    internal fun getCatalogueContent(): LiveData<ArrayList<Model>> {
        return listContent
    }

    private fun print(msg: String, tag: String? = "tagCatalogueContent") {
        log.e(tag, msg)
    }

}


