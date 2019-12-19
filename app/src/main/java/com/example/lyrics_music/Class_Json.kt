package com.example.lyrics_music

class Json(val result: List<Result>,
           val success: Boolean,
           val length: Int)

class Result(//val cover: String,
             //val artist_api: String,
             val artist: String,
             val album: String,
             //val track_id: String,
             //val album_api: String,
             //val album_id: String,
             val title: String)
             //val artist_id: String)

