package ru.dekabrsky.consecutivepractice2025.listWithDetails.data.mock

import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieFullEntity
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieShortEntity
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieType

object MoviesData {
    val moviesShort = listOf(
        MovieShortEntity(
            title = "Blade Runner",
            year = "1982",
            id = "tt0083658",
            type = MovieType.MOVIE,
            posterUrl = "https://m.media-amazon.com/images/M/MV5BOWQ4YTBmNTQtMDYxMC00NGFjLTkwOGQtNzdhNmY1Nzc1MzUxXkEyXkFqcGc@._V1_SX300.jpg"
        ),
        MovieShortEntity(
            title = "Blade Runner 2049",
            year = "2017",
            id = "tt1856101",
            type = MovieType.MOVIE,
            posterUrl = "https://m.media-amazon.com/images/M/MV5BNzA1Njg4NzYxOV5BMl5BanBnXkFtZTgwODk5NjU3MzI@._V1_SX300.jpg"
        ),
        MovieShortEntity(
            title = "The Maze Runner",
            year = "2014",
            id = "tt1790864",
            type = MovieType.MOVIE,
            posterUrl = "https://m.media-amazon.com/images/M/MV5BMjUyNTA3MTAyM15BMl5BanBnXkFtZTgwOTEyMTkyMjE@._V1_SX300.jpg"
        ),
        MovieShortEntity(
            title = "Maze Runner: The Scorch Trials",
            year = "2015",
            id = "tt4046784",
            type = MovieType.MOVIE,
            posterUrl = "https://m.media-amazon.com/images/M/MV5BMjE3MDU2NzQyMl5BMl5BanBnXkFtZTgwMzQxMDQ3NTE@._V1_SX300.jpg"
        ),
        MovieShortEntity(
            title = "Maze Runner: The Death Cure",
            year = "2018",
            id = "tt4500922",
            type = MovieType.MOVIE,
            posterUrl = "https://m.media-amazon.com/images/M/MV5BMTYyNzk3MDc2NF5BMl5BanBnXkFtZTgwMDk3OTM1NDM@._V1_SX300.jpg"
        ),
        MovieShortEntity(
            title = "The Kite Runner",
            year = "2007",
            id = "tt0419887",
            type = MovieType.MOVIE,
            posterUrl = "https://m.media-amazon.com/images/M/MV5BMmRmZmU3YzgtN2ZhYi00ZmUxLWJhYzUtYjFlMmJlZDZmNTBiXkEyXkFqcGc@._V1_SX300.jpg"
        ),
        MovieShortEntity(
            title = "Runner Runner",
            year = "2013",
            id = "tt2364841",
            type = MovieType.MOVIE,
            posterUrl = "https://m.media-amazon.com/images/M/MV5BMTU5OTA0MjI4Ml5BMl5BanBnXkFtZTgwMTgxOTQwMDE@._V1_SX300.jpg"
        ),
        MovieShortEntity(
            title = "The Maze Runner: Finding the Gang",
            year = "2014",
            id = "tt4206510",
            type = MovieType.MOVIE,
            posterUrl = "https://m.media-amazon.com/images/M/MV5BMjBkZDZlNTUtMDYzYy00OWM3LWJiMmItYzM4ZWI0YzJlNWZjXkEyXkFqcGc@._V1_SX300.jpg"
        ),
        MovieShortEntity(
            title = "The Front Runner",
            year = "2018",
            id = "tt7074886",
            type = MovieType.MOVIE,
            posterUrl = "https://m.media-amazon.com/images/M/MV5BMjA3MTMyNzQzNl5BMl5BanBnXkFtZTgwOTA1NDMyNjM@._V1_SX300.jpg"
        ),
        MovieShortEntity(
            title = "The Road Runner Show",
            year = "1966–1973",
            id = "tt0060019",
            type = MovieType.SERIES,
            posterUrl = "https://m.media-amazon.com/images/M/MV5BYTRkMWJiZDgtOTAyOS00YjcyLWI5YjktNDhlMGExMTRjYWY4XkEyXkFqcGc@._V1_SX300.jpg"
        )
    )

    val moviesFull = listOf(
        MovieFullEntity(
            title = "Blade Runner",
            year = "1982",
            rated = "R",
            released = "25 Jun 1982",
            runtime = "117 min",
            genre = "Action, Drama, Sci-Fi",
            director = "Ridley Scott",
            writer = "Hampton Fancher, David Webb Peoples, Philip K. Dick",
            actors = "Harrison Ford, Rutger Hauer, Sean Young",
            plot = "A blade runner must pursue and terminate four replicants who stole a ship in space and have returned to Earth to find their creator.",
            language = "English, German, Cantonese, Japanese, Hungarian, Arabic, Korean",
            country = "United States, United Kingdom",
            awards = "Nominated for 2 Oscars. 13 wins & 22 nominations total",
            poster = "https://m.media-amazon.com/images/M/MV5BOWQ4YTBmNTQtMDYxMC00NGFjLTkwOGQtNzdhNmY1Nzc1MzUxXkEyXkFqcGc@._V1_SX300.jpg",
            ratings = listOf(
                MovieFullEntity.Rating(
                    source = "Internet Movie Database",
                    value = "8.1/10"
                ),
                MovieFullEntity.Rating(
                    source = "Rotten Tomatoes",
                    value = "89%"
                ),
                MovieFullEntity.Rating(
                    source = "Metacritic",
                    value = "84/100"
                )
            ),
            metaScore = "84",
            imdbRating = "8.1",
            imdbVotes = "842,743",
            imdbID = "tt0083658",
            type = MovieType.MOVIE,
            dvd = "N/A",
            boxOffice = "$32,914,489",
            production = "N/A",
            website = "N/A",
            response = "True"
        ),
        MovieFullEntity(
            title = "Blade Runner 2049",
            year = "2017",
            rated = "R",
            released = "06 Oct 2017",
            runtime = "164 min",
            genre = "Action, Drama, Mystery",
            director = "Denis Villeneuve",
            writer = "Hampton Fancher, Michael Green, Philip K. Dick",
            actors = "Harrison Ford, Ryan Gosling, Ana de Armas",
            plot = "Young Blade Runner K's discovery of a long-buried secret leads him to track down former Blade Runner Rick Deckard, who's been missing for thirty years.",
            language = "English",
            country = "United States, United Kingdom, Canada, Spain",
            awards = "Won 2 Oscars. 100 wins & 163 nominations total",
            poster = "https://m.media-amazon.com/images/M/MV5BNzA1Njg4NzYxOV5BMl5BanBnXkFtZTgwODk5NjU3MzI@._V1_SX300.jpg",
            ratings = listOf(
                MovieFullEntity.Rating(
                    source = "Internet Movie Database",
                    value = "8.0/10"
                ),
                MovieFullEntity.Rating(
                    source = "Rotten Tomatoes",
                    value = "88%"
                ),
                MovieFullEntity.Rating(
                    source = "Metacritic",
                    value = "81/100"
                )
            ),
            metaScore = "81",
            imdbRating = "8.0",
            imdbVotes = "695,699",
            imdbID = "tt1856101",
            type = MovieType.MOVIE,
            dvd = "N/A",
            boxOffice = "$92,071,675",
            production = "N/A",
            website = "N/A",
            response = "True"
        ),
        MovieFullEntity(
            title = "The Maze Runner",
            year = "2014",
            rated = "PG-13",
            released = "19 Sep 2014",
            runtime = "113 min",
            genre = "Mystery, Sci-Fi, Thriller",
            director = "Wes Ball",
            writer = "Noah Oppenheim, Grant Pierce Myers, T.S. Nowlin",
            actors = "Dylan O'Brien, Kaya Scodelario, Will Poulter",
            plot = "Thomas is deposited in a community of boys after his memory is erased, soon learning they're all trapped in a maze that will require him to join forces with fellow \"runners\" for a shot at escape.",
            language = "English",
            country = "United States, United Kingdom",
            awards = "4 wins & 12 nominations total",
            poster = "https://m.media-amazon.com/images/M/MV5BMjUyNTA3MTAyM15BMl5BanBnXkFtZTgwOTEyMTkyMjE@._V1_SX300.jpg",
            ratings = listOf(
                MovieFullEntity.Rating(
                    source = "Internet Movie Database",
                    value = "6.8/10"
                ),
                MovieFullEntity.Rating(
                    source = "Rotten Tomatoes",
                    value = "66%"
                ),
                MovieFullEntity.Rating(
                    source = "Metacritic",
                    value = "57/100"
                )
            ),
            metaScore = "57",
            imdbRating = "6.8",
            imdbVotes = "527,124",
            imdbID = "tt1790864",
            type = MovieType.MOVIE,
            dvd = "N/A",
            boxOffice = "$102,427,862",
            production = "N/A",
            website = "N/A",
            response = "True"
        )
    )

}