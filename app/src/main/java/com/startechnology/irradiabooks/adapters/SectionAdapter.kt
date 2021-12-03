package com.startechnology.irradiabooks.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.startechnology.irradiabooks.R
import com.startechnology.irradiabooks.models.SectionModel
import kotlinx.android.synthetic.main.card_for_grid.view.*

class SectionAdapter(private val context: Context, private val sectionList:ArrayList<SectionModel>): BaseAdapter() {
    /**
     * CONTAR CUANTO ELEMENTOS TENGO
     */
    override fun getCount(): Int {
        return sectionList.size
    }

    /**
     * OBTIENE LA POSICION DEL ELEMENTO
     */
    override fun getItem(position: Int): Any {
        return sectionList[position]
    }

    /**
     * OBTIENE LA POSICION DEL ELEMENTO Y EL ID
     */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    /**
     * SE ENCARGA DE INFLAR LOS ELEMENTOS
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val sectionItem = this.sectionList[position]
        val inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val sectionView: View = inflator.inflate(R.layout.card_for_grid,null)

        sectionView.cardTitleGrid.text = sectionItem.name
        Glide.with(context).load(sectionItem.urlImagen).into(sectionView.imgCardGrid)

        return sectionView
    }
}