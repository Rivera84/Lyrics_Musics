package com.example.lyrics_music

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class letra_Cancion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_letra__cancion)

       val objetoIntent:Intent=intent
       val cancion = objetoIntent.getStringExtra("cancion")
       val objetoIntent2 :Intent=intent
       val artista=objetoIntent2.getStringExtra("artista")


        httpVolley_Letra(getLetra(artista!!,cancion!!))


        }

    private fun getLetra(autor:String,cancion: String): String {
        return "https://orion.apiseeds.com/api/music/lyric/$autor/$cancion?apikey=9rYAW2UoM8St0UEJEJTMEnbriHgI7s2zFKxpzKPbVpLkcxLi2A2EtC8WVEK0rXSy"
    }

    private  fun httpVolley_Letra(cancion: String){
        val queue= Volley.newRequestQueue(this)

        val stringLetra= StringRequest(Request.Method.GET,cancion,
            Response.Listener<String> {response ->
                Log.d("HTTP Response",response)

                Toast.makeText(this, "Conexión establecida", Toast.LENGTH_LONG).show()
                jsonToObjectLetra(response)
            }, Response.ErrorListener {

                Log.d("HTTP Respone","Ha ocurrido un error")

                Toast.makeText(this, "¡Ha ocurrido un error en la conexión!", Toast.LENGTH_SHORT).show()

            })

        queue.add(stringLetra)
    }

    private fun jsonToObjectLetra(response:String) {
        val gson = Gson()
        val apiResponse = gson.fromJson(response, Json2::class.java)
        val txtSong: TextView = findViewById(R.id.txtSong)
        val txtLetra: TextView = findViewById(R.id.txtLetra)

        txtSong.text=apiResponse.result.track.name
        txtLetra.text=apiResponse.result.track.text

    }


    }


