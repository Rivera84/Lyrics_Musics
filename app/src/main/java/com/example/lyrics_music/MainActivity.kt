package com.example.lyrics_music

import android.content.Context
import android.content.Intent
import android.inputmethodservice.Keyboard
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var itemList= ArrayList<Result>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cancion = findViewById<EditText>(R.id.eTxtCancion)




        btnBuscar.setOnClickListener{

        httpVoley_Cancion(getCancion(cancion.text.toString()))



        }

    }

    private fun httpVoley_Cancion(cancion: String){
       val queue= Volley.newRequestQueue(this)

        val stringCancion = StringRequest(Request.Method.GET,cancion,
            Response.Listener<String>{response ->
                Log.d("HTTP Response",response)

                Toast.makeText(this, "Conexión establecida", Toast.LENGTH_LONG).show()
                jsonToObjectCancion(response)
            }, Response.ErrorListener {
                Log.d("HTTP Respone","Ha ocurrido un erro")

                Toast.makeText(this, "¡Ha ocurrido un error en la conexión!", Toast.LENGTH_SHORT).show()
            })
        queue.add(stringCancion)
    }

    private fun getCancion(Cancion: String):String{
        return "https://orion.apiseeds.com/api/music/search/?q=$Cancion&apikey=9rYAW2UoM8St0UEJEJTMEnbriHgI7s2zFKxpzKPbVpLkcxLi2A2EtC8WVEK0rXSy"
    }

    fun jsonToObjectCancion(response: String){
    val gson= Gson()
    val apiResponse=gson.fromJson(response, Json ::class.java)


        for (num in 0..itemList.size) {
            itemList.clear()
        }




        itemList.add(Result(album = apiResponse.result[0].album,artist = apiResponse.result[0].artist,title = apiResponse.result[0].title))
        itemList.add(Result(album = apiResponse.result[1].album,artist = apiResponse.result[1].artist,title = apiResponse.result[1].title))
        itemList.add(Result(album = apiResponse.result[2].album,artist = apiResponse.result[2].artist,title = apiResponse.result[2].title))
        itemList.add(Result(album = apiResponse.result[3].album,artist = apiResponse.result[3].artist,title = apiResponse.result[3].title))
        itemList.add(Result(album = apiResponse.result[4].album,artist = apiResponse.result[4].artist,title = apiResponse.result[4].title))
        itemList.add(Result(album = apiResponse.result[5].album,artist = apiResponse.result[5].artist,title = apiResponse.result[5].title))
        itemList.add(Result(album = apiResponse.result[6].album,artist = apiResponse.result[6].artist,title = apiResponse.result[6].title))
        itemList.add(Result(album = apiResponse.result[7].album,artist = apiResponse.result[7].artist,title = apiResponse.result[7].title))
        itemList.add(Result(album = apiResponse.result[8].album,artist = apiResponse.result[8].artist,title = apiResponse.result[8].title))
        itemList.add(Result(album = apiResponse.result[9].album,artist = apiResponse.result[9].artist,title = apiResponse.result[9].title))

        val recycleView: RecyclerView= findViewById(R.id.recycleView)
        recycleView.setHasFixedSize(true)
        recycleView.layoutManager= LinearLayoutManager(this)
        val adapter= Api_Adapter(this,itemList,object : ClickListener{
            override fun onClick(view: View, index: Int) {
               val artista= apiResponse.result[index].artist
                val cancion=apiResponse.result[index].title

               val intent= Intent(this@MainActivity,letra_Cancion::class.java)
                intent.putExtra("cancion",cancion)
                intent.putExtra("artista",artista)
                startActivity(intent)


            }

        })

        recycleView.adapter=adapter
        adapter.notifyDataSetChanged()
    }


}
