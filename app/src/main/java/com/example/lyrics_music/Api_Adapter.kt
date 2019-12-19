package com.example.lyrics_music

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Api_Adapter (var context: Context,  itemList: ArrayList<Result>, var clickListener:ClickListener) : RecyclerView.Adapter<ViewHolder>() {

    //   lateinit
    var  mContext: Context
    var mItemList: ArrayList<Result>

    init {
        mContext = context
        mItemList = itemList
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v: View = LayoutInflater.from(mContext).inflate(R.layout.aource_adapter,parent,false)
        return  ViewHolder(v,clickListener)
    }

    override fun getItemCount(): Int = mItemList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var itemContentMusic = mItemList.get(position)

        var title = itemContentMusic.title
        var artist= itemContentMusic.artist
        var album = itemContentMusic.album



        holder.txtTituloCancion.text= title
        holder.txtArtistaCancion.text=artist
        holder.txtAlbumCancion.text=album

        //Picasso.get().load(image).fit().centerInside().into(holder.imageView)

    }
}

class ViewHolder(var view: View, var clicListener:ClickListener) : RecyclerView.ViewHolder(view),View.OnClickListener{
    var txtTituloCancion: TextView = itemView.findViewById(R.id.txtTituloCancion)
    var txtAlbumCancion : TextView = itemView.findViewById(R.id.txtAlbumCancion)
    var txtArtistaCancion: TextView= itemView.findViewById(R.id.txtAutorCancion)

    override fun onClick(v:View){
        clicListener.onClick(view,adapterPosition)
    }

    init {
        view.setOnClickListener(this)
    }

}