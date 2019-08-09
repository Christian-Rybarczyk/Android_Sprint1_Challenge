package com.rybarstudios.movielistsprint1.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rybarstudios.movielistsprint1.R
import com.rybarstudios.movielistsprint1.model.Movie
import kotlinx.android.synthetic.main.activity_movie.*

class MovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        var bundle: Bundle? = intent.extras
        if (bundle != null) {
            loadMovie(bundle!!.getSerializable("movie") as Movie)
        }

        add_movie_to_list_button.setOnClickListener {
            var savedMovieIntent = Intent()
            savedMovieIntent.putExtra("movie", createMovie())
            setResult(Activity.RESULT_OK, savedMovieIntent)
            Toast.makeText(this, editText_movie_title.text.toString() + " added to list", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    fun loadMovie(movie: Movie) {
        editText_movie_title.setText(movie.title)
    }

    fun createMovie() : Movie {
        var newMovie = Movie(editText_movie_title.text.toString(), watched_switch.isChecked)
        return newMovie
    }

    fun deleteMovie() {

    }
}
