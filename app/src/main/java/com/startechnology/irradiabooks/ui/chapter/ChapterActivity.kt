package com.startechnology.irradiabooks.ui.chapter

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.startechnology.irradiabooks.MainActivity
import com.startechnology.irradiabooks.R
import com.startechnology.irradiabooks.adapters.ChapterAdapter
import com.startechnology.irradiabooks.adapters.SectionAdapter
import com.startechnology.irradiabooks.repository.SectionRepository
import com.startechnology.irradiabooks.ui.content.ContentActivity
import kotlinx.android.synthetic.main.grid_content.*
import kotlinx.android.synthetic.main.list_content.*

class ChapterActivity : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle
    var adapterChapter: ChapterAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)

        /*Reciviendo datos del Intent*/
        val bundle = intent.extras
        val section = bundle?.getString("section").toString()
        val idSection = bundle?.getString("idSeccion").toString()

        /*Array del JSON de Seciones*/
        var dataSectionRepository = SectionRepository()
        var sectionArray = dataSectionRepository.getAllSection(this)

        var chapterArray = dataSectionRepository.getChapterById(this,idSection)
        adapterChapter = ChapterAdapter(this, chapterArray)
        chapterList.adapter = adapterChapter

        chapterList.setOnItemClickListener { parent, view, index, id ->
            val intent = Intent(this, ContentActivity::class.java)
            intent.putExtra("nombre", chapterArray[index].name)
            intent.putExtra("urlImagen", chapterArray[index].urlImagen)
            intent.putExtra("urlAudio", chapterArray[index].urlAudio)
            startActivity(intent)
        }


        /*NavigationDrawer*/
        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if ((!TextUtils.isEmpty(section)) && (section != null)){
            supportActionBar?.title = section.toString()
        }

        navView.setNavigationItemSelectedListener {

            when(it.itemId){
                R.id.nav_home -> changeActivity(MainActivity())
                R.id.nav_responsabilidad -> {
                    val intent = Intent(this, ChapterActivity::class.java)
                    intent.putExtra("idSeccion", sectionArray.get(0).id)
                    intent.putExtra("section", sectionArray.get(0).name)
                    startActivity(intent)
                }
                R.id.nav_paciencia -> {
                    val intent = Intent(this, ChapterActivity::class.java)
                    intent.putExtra("idSeccion", sectionArray.get(1).id)
                    intent.putExtra("section", sectionArray.get(1).name)
                    startActivity(intent)
                }
                R.id.nav_respeto -> {
                    val intent = Intent(this, ChapterActivity::class.java)
                    intent.putExtra("idSeccion", sectionArray.get(2).id)
                    intent.putExtra("section", sectionArray.get(2).name)
                    startActivity(intent)
                }
                R.id.nav_amistad -> {
                    val intent = Intent(this, ChapterActivity::class.java)
                    intent.putExtra("idSeccion", sectionArray.get(3).id)
                    intent.putExtra("section", sectionArray.get(3).name)
                    startActivity(intent)
                }
                R.id.nav_cooperacion -> {
                    val intent = Intent(this, ChapterActivity::class.java)
                    intent.putExtra("idSeccion", sectionArray.get(4).id)
                    intent.putExtra("section", sectionArray.get(4).name)
                    startActivity(intent)
                }
                R.id.nav_orden -> {
                    val intent = Intent(this, ChapterActivity::class.java)
                    intent.putExtra("idSeccion", sectionArray.get(5).id)
                    intent.putExtra("section", sectionArray.get(5).name)
                    startActivity(intent)
                }
                R.id.nav_otros -> Toast.makeText(applicationContext, "Esta sección por el momento se encuentra vacía.", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun changeActivity(activity: Activity){
        startActivity(Intent(this, activity::class.java))
    }
}