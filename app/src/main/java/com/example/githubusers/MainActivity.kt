package com.example.githubusers

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.githubusers.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var list2:ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list2= ArrayList()

        requestQueue = Volley.newRequestQueue(this)
        loadArray("https://api.github.com/users")
    }

    fun loadArray(url: String) {
        val jsonArrayRequest =
            JsonArrayRequest(Request.Method.GET, url, null, object : Response.Listener<JSONArray> {
                override fun onResponse(response: JSONArray?) {
                    var str = response.toString()
                    val type = object : TypeToken<ArrayList<Model>>(){}.type
                    val list = Gson().fromJson<ArrayList<Model>>(str,type)
                    list.forEach {
                        list2.add(User(it.login,it.avatar_url))
//                        list2.add(User(it.login,fetchImageLoad(it.avatar_url)))
                    }
                    val adapter = AdapterOne(list2)
                    binding.rv.adapter = adapter
                }
            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {

                }
            })
        requestQueue.add(jsonArrayRequest)

    }

}