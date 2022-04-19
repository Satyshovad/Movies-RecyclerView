package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var recyclerView: RecyclerView
    var listMovie = moviesList()
    val adapter = MovieAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.swipeRef.setOnRefreshListener {
            binding.swipeRef.isRefreshing = true
            Handler().postDelayed(
                {
                    initial()
                }, 5000
            )
        }

        binding.recyclerviewMovie.adapter = adapter

        initial()

        adapter.onClickDelete = object : MovieAdapter.OnClickDelete {

            override fun onDelete(item: Movie) {
                delete(item)
            }
        }
    }

    private fun initial() {
        (binding.recyclerviewMovie.adapter as MovieAdapter).submitList(listMovie)
        binding.swipeRef.isRefreshing = false
        binding.btnAdd.setOnClickListener {
            addCity()
        }
    }

    fun moviesList(): ArrayList<Movie> {
        val movieList = ArrayList<Movie>()

        val movie = Movie("Dune", "The Atreides arrive on a planet where no one is happy for them. Denis Villeneuve's fantastic epic with six Oscars",
            R.drawable.movie1 )
        movieList.add(movie)

        val movie2 = Movie("Spider man no way home", "With Spider-Man's identity now revealed, Peter asks Doctor Strange for help. When a spell goes wrong, dangerous foes from other worlds start to appear, forcing Peter to discover what it truly means to be Spider-Man.",
            R.drawable.movie2)
        movieList.add(movie2)

//        val movie3 = Movie("The Eternal", "The Eternals are an ancient alien race that arrived on Earth more than 7 thousand years ago to protect humanity from galactic monsters called Deviants.",
//            R.drawable.movie3)
//        movieList.add(movie3)


        return movieList
    }

    private fun delete(item: Movie) {
        listMovie.remove(item)
        adapter.submitList(listMovie)
        binding.recyclerviewMovie.adapter=adapter

    }
    fun addCity(){
        listMovie.add(Movie("The Eternal", "The Eternals are an ancient alien race that arrived on Earth more than 7 thousand years ago to protect humanity from galactic monsters called Deviants.", R.drawable.movie3))
//        count++
        adapter.submitList(listMovie)
        binding.recyclerviewMovie.adapter=adapter
    }

//    companion object {
//        var count = 1
//        var movie = "Movie $count"
//        var desc = "Movie description $count"
//        var photo = "https://kartinkin.net/uploads/posts/2021-07/1626118174_19-kartinkin-com-p-personazhi-iz-anime-tvoe-imya-anime-krasiv-27.jpg"
//    }
}