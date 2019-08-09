package com.rybarstudios.movielistsprint1.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.rybarstudios.movielistsprint1.R
import com.rybarstudios.movielistsprint1.model.Movie
import kotlinx.android.synthetic.main.activity_main.*

class ListActivity : AppCompatActivity() {

    var movieList = mutableListOf<Movie>()

    companion object {
        const val EDIT_MOVIE_REQUEST_CODE = 42
        var counter = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Movie List"

        add_movie_button.setOnClickListener {
            val intent = Intent(this, MovieActivity::class.java)
            startActivityForResult(intent, EDIT_MOVIE_REQUEST_CODE)
        }
    }

    private fun refreshMovieList() {
        scrollview_linearLayout.removeAllViews()
        for ((counter, movie) in movieList.withIndex()) {
            scrollview_linearLayout.addView(createTextView(movie, counter))
        }
    }

    private fun createTextView(movie: Movie, index: Int) : TextView {
        val view = TextView(this)
        view.text = movie.title
        view.textSize = 24f
        view.id =index

        if (movie.watched) {
            view.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }

        view.setOnClickListener {
            val intent = Intent(this, MovieActivity::class.java)
            intent.putExtra("movie", movieList[view.id])
            movieList.removeAt(view.id)
            startActivityForResult(intent, EDIT_MOVIE_REQUEST_CODE)
        }
        return view
    }

    override fun onPostResume() {
        refreshMovieList()
        super.onPostResume()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_MOVIE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val newMovie = data.getSerializableExtra("movie") as Movie
                movieList.add(newMovie)
                counter++
            }
        }
    }
}
