package com.example.lyrics_music

class Json2(val result: Result2)

class Result2(val copyright: Copyright,
             val artist: Artist,
             val probability: Int,
             val similarity: Double,
             val track: Track)

class Copyright(val artist: String,
                val text: String,
                val notice: String)

class Artist(val name: String)

class Track(val name: String,
            val text: String,
            val lang: Lang)

class Lang(val code: String,
           val name: String)

