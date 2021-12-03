package com.startechnology.irradiabooks.repository

import android.content.Context
import com.startechnology.irradiabooks.models.ChapterModel
import com.startechnology.irradiabooks.models.SectionModel
import com.startechnology.irradiabooks.utils.readJSON
import org.json.JSONObject

class SectionRepository{

    fun getAllSection(context: Context): ArrayList<SectionModel>{
        val sectionModelObject = JSONObject(readJSON(context,"section.json"))
        return parseSectionJsonToResult(sectionModelObject)
    }

    private fun parseSectionJsonToResult(sectionModelObject: JSONObject): ArrayList<SectionModel> {
        val sectionModelJsonArray = sectionModelObject.getJSONArray("sections")
        val responseSection:ArrayList<SectionModel> = ArrayList()

        for (index in 0 until sectionModelJsonArray.length()){

            val sectionObject = sectionModelJsonArray[index] as JSONObject

            val id:String = sectionObject.getString("id")
            val name:String = sectionObject.getString("nombre")
            val urlImagen:String = sectionObject.getString("urlImagen")

            val sectionM = SectionModel(id, name, urlImagen)
            responseSection.add(sectionM)
        }

        return responseSection
    }

    fun getChapterById(context: Context, idSection:String): ArrayList<ChapterModel>{
        val sectionModelObject = JSONObject(readJSON(context,"chapter.json"))
        return parseChapterJsonToResult(sectionModelObject, idSection)
    }

    private fun parseChapterJsonToResult(sectionModelObject: JSONObject, idSection:String): ArrayList<ChapterModel> {
        val chapterModelJsonArray = sectionModelObject.getJSONArray("chapters")
        val responseChapters:ArrayList<ChapterModel> = ArrayList()

        for (index in 0 until chapterModelJsonArray.length()){

            val chapterObject = chapterModelJsonArray[index] as JSONObject

            if (chapterObject.getString("idSeccion").equals(idSection)){
                val id:String = chapterObject.getString("id")
                val name:String = chapterObject.getString("nombre")
                val description:String = chapterObject.getString("descripcion")
                val idSection:String = chapterObject.getString("idSeccion")
                val urlImagen:String = chapterObject.getString("urlImagen")
                val urlAudio:String = chapterObject.getString("urlAudio")

                val chapterM = ChapterModel(id, name, description, idSection, urlImagen, urlAudio)
                responseChapters.add(chapterM)
            }
        }

        return responseChapters
    }
}
