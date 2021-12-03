package com.startechnology.irradiabooks.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.startechnology.irradiabooks.R
import com.startechnology.irradiabooks.models.ChapterModel
import kotlinx.android.synthetic.main.card_for_grid.view.*
import kotlinx.android.synthetic.main.card_for_grid.view.cardTitleGrid
import kotlinx.android.synthetic.main.card_for_list.view.*

class ChapterAdapter(private val context: Context, private val chapterList:ArrayList<ChapterModel>): BaseAdapter() {
    override fun getCount(): Int {
       return chapterList.size
    }

    override fun getItem(position: Int): Any {
        return chapterList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val chapterItem = this.chapterList[position]
        val inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val chapterView: View = inflator.inflate(R.layout.card_for_list,null)

        chapterView.cardTitleList.text = chapterItem.name
        chapterView.cardSinopsisList.text = chapterItem.description

        Glide.with(context).load(chapterItem.urlImagen).into(chapterView.imgCardList)

        return chapterView
    }
}