package com.startechnology.irradiabooks.ui.content

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import com.bumptech.glide.Glide
import com.startechnology.irradiabooks.R
import kotlinx.android.synthetic.main.activity_content.*

class ContentActivity : AppCompatActivity() {

    //para que el seekbar avance conforme a la musica
    lateinit var runnable: Runnable
    private var handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        val bundle = intent.extras
        val nombre = bundle?.getString("nombre").toString()
        val urlImagen = bundle?.getString("urlImagen").toString()
        val urlAudio = bundle?.getString("urlAudio").toString()

        nameMusic.text = nombre
        Glide.with(this).load(urlImagen).into(imgMusic)

        //Objeto MediaPlayer
        val mediaPlayer :MediaPlayer = MediaPlayer.create(this, Uri.parse(urlAudio))

        //Funcionalidad para el SeekBar
        seekBar.progress = 0
        //el size maximo del seekbar será la duracion de la musica
        seekBar.max = mediaPlayer.duration


        //funcion para e boton de Play
        btnPlay.setOnClickListener{
            //Verificando que el mediaPlayer no esté activo
            if(!mediaPlayer.isPlaying){
                mediaPlayer.start()
                //cambiando el icono del boton
                btnPlay.setImageResource(R.drawable.ic_baseline_pause_24)
            }else{ // cuando el mediaPlayer esta activo y se pausa
                mediaPlayer.pause()
                btnPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24)

            }
        }

        //Evento para el seekbar
        //Al cambiar el rpogreso del seekbar se cambaia la posicion
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                //Cuando se mueva la posicion del seekbar la musica debe cambiar a esa posicion
                if (changed){
                    mediaPlayer.seekTo(pos)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

        //para que el seekbar avance conforme a la musica
        runnable = Runnable {
            seekBar.progress = mediaPlayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable,1000)

        //cuando la musica termine el seekbar regrese a 0 y el boton cambie el icono a play
        mediaPlayer.setOnCompletionListener {
            mediaPlayer.pause()
            btnPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            seekBar.progress = 0
        }

    }
}